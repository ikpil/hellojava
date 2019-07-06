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

        hm.put("슈", 1);
        hm.put("퍼", 2);
        hm.put("캣", 3);

        tm.put("슈", 1);
        tm.put("퍼", 2);
        tm.put("캣", 3);

        lhm.put("슈", 1);
        lhm.put("퍼", 2);
        lhm.put("캣", 3);

        chm.put("슈", 1);
        chm.put("퍼", 2);
        chm.put("캣", 3);

        cslm.put("슈", 1);
        cslm.put("퍼", 2);
        cslm.put("캣", 3);

        logger.info("hash map - {}", hm);
        logger.info("tree map - {}", tm);
        logger.info("linked hash map - {}", lhm);
        logger.info("thread safe hash map - {}", chm);
        logger.info("thread safe skip list map - {}", cslm);
    }
}
