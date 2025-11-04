package com.example.demo.student;

import com.example.demo.club.ClubDTO;

import java.time.LocalDate;

public record StudentDTO(Long id, String name, String email, LocalDate birthDate, ClubDTO club) {
}
