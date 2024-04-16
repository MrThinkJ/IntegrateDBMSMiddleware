package com.mrthinkj.integratemiddlewareapplication;

import com.mrthinkj.integratemiddlewareapplication.model.SqlEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class IntegrateMiddlewareApplication{

    @Autowired
    private JdbcTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(IntegrateMiddlewareApplication.class, args);
    }


//    @Override
//    public void run(String... args) throws Exception {
//        String sql = "select * from Personal";
//        List<SqlEmployee> employees = template.query(sql, BeanPropertyRowMapper.newInstance(SqlEmployee.class));
//        System.out.println("Employee");
//        employees.forEach(System.out::println);
//    }
}
