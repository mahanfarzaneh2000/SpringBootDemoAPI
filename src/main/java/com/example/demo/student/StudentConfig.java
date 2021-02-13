package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args-> {
            Student ali = new Student("ALi"
                            ,"ali@gmail.com"
                            ,LocalDate.of(2000, JANUARY,1));
            Student john = new Student("John"
                    ,"john@gmail.com"
                    ,LocalDate.of(2008, FEBRUARY,25));

            repository.saveAll(List.of(ali,john));
        };
    }
}
