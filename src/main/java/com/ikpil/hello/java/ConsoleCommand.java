package com.ikpil.hello.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ConsoleCommand {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleCommand.class);

    public static void main(String[] args) {
        ArrayList<Example> examples = new ArrayList<>();
        examples.add(new CollectionExample());
        examples.add(new Log4J2Example());
        examples.add(new RedissonExample());

        for (Example example : examples) {
            example.run();
        }
    }
}
