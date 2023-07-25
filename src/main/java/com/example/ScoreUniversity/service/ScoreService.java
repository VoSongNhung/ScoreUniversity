package com.example.ScoreUniversity.service;

import com.example.ScoreUniversity.dto.ScoreDTO;
import com.example.ScoreUniversity.entity.Score;

import java.util.List;

public interface ScoreService {
    List<Score> getAllScore();
    Score getScoreByCourseName(String name);
    Score addScore(ScoreDTO scoreDTO);
    Score updateScoreByCourseName(Score score,String name);
    boolean deleteScore(String name);
}
