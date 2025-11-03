package com.example.demo.club;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "club")
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    @Column(name = "id")
    private Long id;

    @Column(name = "club_name")
    private String clubName;

    public Club (String clubName){
        this.clubName = clubName;
    }
    @JsonIgnore

    @OneToMany(mappedBy = "club",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonProperty
    private Set<Student> students;
}
