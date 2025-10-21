package com.example.demo.club;

import com.example.demo.student.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends CrudRepository<Club, Long> {
    Optional<Club> findClubsByClubName(String name);
    List<Club> findAllByOrderByIdAsc();
}
