package com.example.demo.club;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final StudentRepository studentRepository;


    public List<Club> getClubs() {
     return clubRepository.findAllByOrderByIdAsc();
    }

    public Optional<Club> getClubById(Long id) {
        return clubRepository.findById(id);
    }
    @Transactional
    public void deleteClubById(Long id) {
        if(!clubRepository.existsById(id)){
            throw new NoSuchElementException("Club with id"+id+"does not exists");
        }
        // 1) премахваме FK от студентите в single UPDATE
        studentRepository.detachStudentsFromClub(id);

        // 2) изтриваме клуба
        clubRepository.deleteById(id);
    }

    public Club getClubOrThrow(Long id){
        return clubRepository.findById(id).orElseThrow(() -> new NoSuchElementException("club with id " +id+ " does not exists"));
    }



    public Club getClubByName(String clubName) {
        return clubRepository.findClubsByClubName(clubName)
                .orElseThrow(() -> new NoSuchElementException("This club " +clubName+ " does not exists" ));
    }

    public Club saveClub(Club club) {
        Optional<Club> existingClub = clubRepository.findClubsByClubName(club.getClubName());
        if(existingClub.isPresent()){
            throw new IllegalStateException("this club already "+club.getClubName()+" exists");
        }
        return clubRepository.save(club);
    }

    public Club getOrSave(String clubName){
       return clubRepository.findClubsByClubName(clubName).orElseGet(() -> clubRepository.save( Club.builder().clubName(clubName).build()));
    }
}
