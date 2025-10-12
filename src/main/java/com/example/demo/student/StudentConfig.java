package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student lily =  new Student(

                "lily",
                "lily.com",
                LocalDate.of(2002, APRIL,6)
                );

            Student georgi =  new Student(

                    "georgi",
                    "georgi.com",
                    LocalDate.of(2001, JUNE,6)
                    );

            studentRepository.saveAll( List.of(lily,georgi));
        };

    }
}
