package com.employee.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EmployeeProjectApplication {

    /**
     * Main class to start the Employee Project
     * @param args
     */
    public static void main(String[] args) {

        SpringApplication.run(EmployeeProjectApplication.class, args);
    }

}

