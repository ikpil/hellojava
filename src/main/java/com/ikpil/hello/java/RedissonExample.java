package com.ikpil.hello.java;

import org.redisson.PubSubMessageListener;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.redisson.pubsub.CountDownLatchPubSub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * redis-server
 * windows : https://github.com/microsoftarchive/redis/releases
 * <p>
 * redis-client
 * redisson : https://github.com/redisson/redisson
 */
public class RedissonExample implements Example {
    private static final Logger logger = LoggerFactory.getLogger(RedissonExample.class);
    private static final String mainHost = "redis://127.0.0.1:6379";

    public void run() {
        try {
            consume(this::connectAndShutdown);
            consume(this::getOrSet);
            consume(this::publishOrSubscribe);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private RedissonClient create(String host) {
        // Programmatic configuration
        Config config = new Config();
        config.setNettyThreads(64);

        if (OSValidator.isUnix()) {
            config.setTransportMode(TransportMode.EPOLL);
        } else {
            config.setTransportMode(TransportMode.NIO);
        }

        // use "rediss://" for SSL connection
        config.useSingleServer()
                .setAddress(host);

        return Redisson.create(config);
    }

    private void consume(Consumer<RedissonClient> consumer) throws RedisConnectionException {
        RedissonClient client = null;
        try {
            client = create(mainHost);
            if (null == client) {
                return;
            }

            consumer.accept(client);

        } finally {
            if (null == client) {
                logger.error("redisson creation failed");
            } else {
                client.shutdown();
            }
        }
    }

    // 케넥션 테스트, 할 일이 없다
    private void connectAndShutdown(RedissonClient client) {
        // ....
    }

    // get set 예제
    private void getOrSet(RedissonClient client) {
        String key = "getOrSet";
        long setValue = 1234L;
        client.getBucket(key).set(setValue, 10, TimeUnit.SECONDS);
        logger.info("redis - set getOrSet {}", setValue);

        long getValue = (Long) client.getBucket(key).get();
        logger.info("redis - get getOrSet {}", getValue);
    }


    /**
     * 테스트 케이스
     * - localhost, Windows10 1809 64bit, i5-2500, DDR3 24G
     * -  1 thread, 4초간 13141회 수신, 초당 약 3200회 수신 가능
     * -  8 thread, 4초간 17021회 수신, 초당 약 4200회 수신 가능
     * - 10 thread, 4초간 15209회 수신, 초당 약 3800회 수신 가능
     * <p>
     * 테스트 케이스 2
     * <p>
     * <p>
     * 테스트 결과
     * <p>
     * publish -> publishAsync 가 10% ~ 20% 더 빠른 결과
     * 하나의 클라이언트가 수신 토픽 갯수를 늘린다고, 수신 반응이 좋아지지 않음
     * 하나의 클라이언트가 송신 토픽 갯수를 늘린다고, 송신 반응이 좋아지지 않음
     * <p>
     * 복수의 클라이언트로 쓰레드의 갯수를 늘리면 반응은 좋아지지만,
     * 일반적으로 사용할 수 없는 구조(송신 패킷 순서가 의도와 달라질 수 있음)
     * <p>
     * <p>
     * 결론
     * <p>
     * 효율 측면에서
     * 하나의 클라이언트로 처리하는게 최고 효율
     * <p>
     * 성능 측면에서
     * 복수의 클라이언트가 서로 다른 토픽으로 분리해서 받는다면, 최고 성능
     */
    // publish/subscribe 예제
    private void publishOrSubscribe(RedissonClient client) {
        String topicName = "publishOrSubscribe";

        // 카운트 다운 등록
        final int countdown = 100000;
        AtomicLong listenCnt = new AtomicLong();
        AtomicBoolean runnable = new AtomicBoolean(true);

        // 리스너 등록
        int threadCnt = 1;
        for (int i = 0; i < threadCnt; ++i) {
            client.getTopic(topicName + i).addListener(
                    String.class,
                    (channel, msg) -> listenCnt.incrementAndGet()
            );
        }
        logger.info("subscribe - key({}) count({})", topicName, countdown);


        // 쓰레드 돌림
        ArrayList<Thread> threads = new ArrayList<>();
        for (int threadIdx = 0; threadIdx < threadCnt; ++threadIdx) {
            Thread t = new Thread(() -> publisher(
                    runnable, topicName, threadCnt, countdown
            ));
            threads.add(t);
        }

        try {
            for (Thread t : threads) {
                t.start();
            }

            Thread.sleep(4000L);
            runnable.compareAndSet(true, false);

        } catch (InterruptedException e) {
            logger.error("", e);
        }

        logger.info("published - key({}) send({}) recv({})", topicName, countdown, listenCnt.get());
    }

    // topic publisher
    private void publisher(AtomicBoolean runnable, String topicName, int threadCnt, int countdown) {
        // 퍼블리쉬 시작
        RedissonClient publishClient = create(mainHost);

        ArrayList<RTopic> topics = new ArrayList<>();
        for (int i = 0; i < threadCnt; ++i) {
            RTopic topic = publishClient.getTopic(topicName + i);
            topics.add(topic);
        }

        for (int i = 0; runnable.get() && i < countdown / threadCnt + 1; ++i) {
            topics.get(i % topics.size()).publishAsync("z");
            //publishTopic.publish("z");

            try {
                // 10번당 한번씩 쉰다
                if (0 == (i % 10)) {
                    Thread.sleep(0L, 1);
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }

        publishClient.shutdown();
    }
}
