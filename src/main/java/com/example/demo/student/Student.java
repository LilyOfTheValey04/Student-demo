package com.example.demo.student;

import com.example.demo.club.Club;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")



// Represents a Student entity mapped to a database table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Long id;

    private String name;
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    // many students can belong to one club
    @ManyToOne
    @JoinColumn(name = "club_id", nullable = true)
    @JsonProperty("club")
    //@JsonBackReference
    private Club club;

    @Transient
    private Integer age;

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

   /* @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + getAge() +
                ", club=" + (club != null ? club.getClubName() : "none") +
                '}';
    }*/
}