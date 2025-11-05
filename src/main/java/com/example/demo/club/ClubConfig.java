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
            Club programmingClub =  Club.builder()
                    .clubName("programming club")
                    .build();

            Club mathClub = Club.builder()
                    .clubName("math club")
                    .build();

            clubRepository.saveAll(List.of(programmingClub,mathClub));

         };
    }

}
