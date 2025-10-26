package com.example.demo.club;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;



    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs(){
        return new  ResponseEntity<>(clubService.getClubs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubId(@PathVariable Long id){
        Optional <Club> fetchClub = clubService.getClubById(id);
        if(fetchClub.isPresent()){
            return new ResponseEntity<>(fetchClub.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Club> saveClub(@RequestBody Club club){
        return new ResponseEntity<>(clubService.saveClub(club), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClubById (@PathVariable Long id){
       try{
           clubService.deleteClubById(id);
           return new ResponseEntity<>(HttpStatus.OK);

       }catch(Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

}
