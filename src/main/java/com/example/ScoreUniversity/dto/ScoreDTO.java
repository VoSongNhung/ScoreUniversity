package com.example.ScoreUniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    private String courseName;
    private int year;
    private int semester;
    private int credit;
    private long score;
    private String idstudent;
}
