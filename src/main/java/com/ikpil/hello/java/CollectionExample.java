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

        hm.put("A", 1);
        hm.put("B", 2);
        hm.put("C", 3);

        tm.put("A", 1);
        tm.put("B", 2);
        tm.put("C", 3);

        lhm.put("A", 1);
        lhm.put("B", 2);
        lhm.put("C", 3);

        chm.put("A", 1);
        chm.put("B", 2);
        chm.put("C", 3);

        cslm.put("A", 1);
        cslm.put("B", 2);
        cslm.put("C", 3);

        logger.info("hash map - {}", hm);
        logger.info("tree map - {}", tm);
        logger.info("linked hash map - {}", lhm);
        logger.info("thread safe hash map - {}", chm);
        logger.info("thread safe skip list map - {}", cslm);
    }
}
