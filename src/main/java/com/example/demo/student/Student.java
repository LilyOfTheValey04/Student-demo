package com.example.demo.student;

import com.example.demo.club.Club;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "student")

// Represents a Student entity mapped to a database tabl
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    // old verision
    /*@SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize =  1
    )

    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator =  "student_sequence"
    )*/

    private Long id;

    private String name;
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    // many students can belong to one club
    @ManyToOne
    @JoinColumn(name = "club_id", nullable = true)
    @JsonBackReference
    private Club club;

    @Transient
    private Integer age;

    public Student() {}

    public Student(String name, String email, LocalDate birthDate, Club club) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.club = club;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDate getBirthDate() { return birthDate; }
    public Club getClub() { return club; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setClub(Club club) { this.club = club; }

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + getAge() +
                ", club=" + (club != null ? club.getClubName() : "none") +
                '}';
    }
}