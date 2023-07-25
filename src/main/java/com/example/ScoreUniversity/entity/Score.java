package com.example.ScoreUniversity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ordinalNumbers;
    private String courseName;
    private int year;
    private int semester;
    private int credit;
    private long score;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_student")
    private Student student;
}
