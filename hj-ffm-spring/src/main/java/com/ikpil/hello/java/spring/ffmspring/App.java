package com.ikpil.hello.java.spring.ffmspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(AppConfig.class)
public class App {
    public static void main(String[] args) {
        try (var context = SpringApplication.run(App.class, args)) {
            var frontend = context.getBean(Frontend.class);
            frontend.run();
        }
    }
}
