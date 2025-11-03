package com.example.demo.student;

import com.example.demo.club.Club;
import com.example.demo.club.ClubConfig;
import com.example.demo.club.ClubRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static java.time.Month.*;

@Configuration
// This configuration class initializes some test data when the application starts
public class StudentConfig {
    // CommandLineRunner runs automatically after the Spring Boot app starts.
    // It’s often used to insert sample data or perform startup logic.
    @Bean

    @Order(2)

    CommandLineRunner commandLineRunner(StudentRepository studentRepository, ClubRepository clubRepository) {
        return args -> {
            // Create sample students
            ///  add club
            // Проверяваме дали клубовете съществуват, и ако не — ги създаваме
            Club programmingClub = clubRepository.findClubsByClubName("programming club")
                    .orElseGet(() -> clubRepository.save(new Club("programming club")));

            Club mathClub = clubRepository.findClubsByClubName("math club")
                    .orElseGet(() -> clubRepository.save(new Club("math club")));

            Student lily = new Student(

                    "lily",
                    "lily.com",
                    LocalDate.of(2002, APRIL, 6),
                    programmingClub
            );

            Student georgi = new Student(

                    "georgi",
                    "georgi.com",
                    LocalDate.of(2001, JUNE, 6),
                    mathClub
            );

            studentRepository.saveAll(List.of(lily, georgi));
        };

    }
}
