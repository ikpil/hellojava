package com.ikpil.hello.java;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
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
        final String redissServerHost = "redis://127.0.0.1:6379";
        RedissonClient client = null;
        try {
            client = create(redissServerHost);
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

    private void connectAndShutdown(RedissonClient client) {
        // ....
    }

    private void getOrSet(RedissonClient client) {
        String key = "getOrSet";
        long setValue = 1234L;
        client.getBucket(key).set(setValue, 10, TimeUnit.SECONDS);
        logger.info("redis - set getOrSet {}", setValue);

        long getValue = (Long)client.getBucket(key).get();
        logger.info("redis - get getOrSet {}", getValue);
    }

    private void publishOrSubscribe(RedissonClient client) {

    }
}
