package com.example.demo.club;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends CrudRepository<Club, Long> {

    Optional<Club> findClubsByClubName(String name);
   // Optional<Club> findClubsById(Long id);
   @Query("SELECT c FROM Club c LEFT JOIN FETCH c.students WHERE c.id = :id")
   Optional<Club> findByIdWithStudents(@Param("id") Long id);

    List<Club> findAllByOrderByIdAsc();
}
