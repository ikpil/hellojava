package com.ikpil.hello.java.spring.layering;

import com.ikpil.hello.java.spring.layering.domain.Customer;
import com.ikpil.hello.java.spring.layering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class App implements CommandLineRunner {
    @Autowired
    CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        var created = repository.save(new Customer(null, "밀란", "쿤데라"));
        System.out.println(created + " is created!");

        repository.findAll().forEach(System.out::println);
    }
}
