package com.example.demo.club;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

   /* public void deleteClubById(Long id) {
        boolean exists = clubRepository.existsById(id);
        if(exists){
            clubRepository.deleteById(id);
        }
        else{
            throw new NoSuchElementException("No such club with id: " + id);
        }
    }*/

    public void deleteClubById(Long id){
        Club club = clubRepository.findClubsById(id)
                .orElseThrow(()-> new NoSuchElementException("Club with id "+id+" does not exists"));

        for(Student student : club.getStudents()){
            student.setClub(null);
        }

        studentRepository.saveAll(club.getStudents());

        clubRepository.delete(club);
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
       return clubRepository.findClubsByClubName(clubName).orElseGet(() -> clubRepository.save(new Club(clubName)));
    }
}
