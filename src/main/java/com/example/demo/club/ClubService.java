package com.example.demo.club;

import com.example.demo.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;


    public List<Club> getClubs() {
     return clubRepository.findAllByOrderByIdAsc();
    }

    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    public Optional<Club> getClubById(Long id) {
        return clubRepository.findById(id);
    }

    public void deleteClubById(Long id) {
        boolean exists = clubRepository.existsById(id);
        if(exists){
            clubRepository.deleteById(id);
        }
        else{
            throw new NoSuchElementException("No such club with id: " + id);
        }
    }

    public Club getClubOrThrow(Long id){
        return clubRepository.findById(id).orElseThrow(() -> new NoSuchElementException("club with id " +id+ " does not exists"));
    }



    public Club getClubByName(String clubName) {
    }

    public Club saveClub(Club club) {
    }
}
