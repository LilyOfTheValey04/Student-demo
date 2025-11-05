package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// This interface provides CRUD operations for the Student entity.
// JpaRepository already includes most common methods like save(), findById(), deleteById(), etc.
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Finds a single student by their email (if it exists)
    Optional<Student> findByEmail(String email);

    // Returns all students sorted by their ID in ascending order
    List<Student> findAllByOrderByIdAsc();

    @Modifying
    @Query("UPDATE Student s SET s.club = NULL WHERE s.club.id = :clubId")
    int detachStudentsFromClub(@Param("clubId") Long clubId);
}
