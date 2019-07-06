package com.ikpil.hello.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class CollectionExample implements Example {
    private static final Logger logger = LoggerFactory.getLogger(CollectionExample.class);

    public void run() {
        HashMap<String, Integer> hm = new HashMap<>();
        TreeMap<String, Integer> tm = new TreeMap<>();
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();

        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
        ConcurrentSkipListMap<String, Integer> cslm = new ConcurrentSkipListMap<>();

        String key1 = "가";
        String key2 = "나";
        String key3 = "다";

        hm.put(key1, 1);
        hm.put(key2, 2);
        hm.put(key3, 3);
        logger.info("hash map - {}", hm);

        tm.put(key1, 1);
        tm.put(key2, 2);
        tm.put(key3, 3);
        logger.info("tree map - {}", tm);

        lhm.put(key1, 1);
        lhm.put(key2, 2);
        lhm.put(key3, 3);
        logger.info("linked hash map - {}", lhm);

        chm.put(key1, 1);
        chm.put(key2, 2);
        chm.put(key3, 3);
        logger.info("thread safe hash map - {}", chm);

        cslm.put(key1, 1);
        cslm.put(key2, 2);
        cslm.put(key3, 3);
        logger.info("thread safe skip list map - {}", cslm);
    }
}
