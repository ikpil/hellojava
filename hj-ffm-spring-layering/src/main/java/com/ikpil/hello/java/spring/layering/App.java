package com.ikpil.hello.java.spring.layering;

import com.ikpil.hello.java.spring.layering.domain.Customer;
import com.ikpil.hello.java.spring.layering.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootApplication
@ComponentScan
public class App implements CommandLineRunner {
    @Autowired
    CustomerService customerService;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public void run_step1(String... strings) throws Exception {
        customerService.save(new Customer(1, "Nobita", "Nobi"));
        customerService.save(new Customer(2, "Takeshi", "Goda"));
        customerService.save(new Customer(3, "Suneo", "Honekawa"));

        customerService.findAll().forEach(System.out::println);
    }

    public void run_step2(String... strings) throws Exception {
        String sql = "SELECT :a + :b";
        var param = new MapSqlParameterSource()
                .addValue("a", 100)
                .addValue("b", 200);

        Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);

        System.out.println("result = " + result);
    }

    @Override
    public void run(String... strings) throws Exception {
        String sql = "SELECT * FROM `customers` WHERE `id` = :id";
        var param = new MapSqlParameterSource()
                .addValue("id", 1);

        var result = jdbcTemplate.queryForObject(sql, param, (rs, num) -> new Customer(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name")
        ));

        System.out.println("result = " + result);
    }
}
