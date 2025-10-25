package com.example.demo.club;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.List;

public class ClubConfig {
    @Bean
    @Order(1)
    CommandLineRunner clubcommandLineRunner (ClubRepository clubRepository){
        return args-> {
            Club programmingClub = new Club();
            programmingClub.setClubName("programing club");

            Club mathClub = new Club();
            mathClub.setClubName("math club");

            clubRepository.saveAll(List.of(programmingClub,mathClub));
        };
    }

}
