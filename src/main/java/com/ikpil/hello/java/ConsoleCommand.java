package com.ikpil.hello.java;

import java.util.ArrayList;

public class ConsoleCommand {
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
