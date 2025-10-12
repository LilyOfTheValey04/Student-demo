package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
// This configuration class initializes some test data when the application starts
public class StudentConfig {
    // CommandLineRunner runs automatically after the Spring Boot app starts.
    // It’s often used to insert sample data or perform startup logic.
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            // Create sample students
            Student lily = new Student(

                    "lily",
                    "lily.com",
                    LocalDate.of(2002, APRIL, 6)
            );

            Student georgi = new Student(

                    "georgi",
                    "georgi.com",
                    LocalDate.of(2001, JUNE, 6)
            );

            studentRepository.saveAll(List.of(lily, georgi));
        };

    }
}
