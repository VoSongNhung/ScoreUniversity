package com.example.ScoreUniversity.repository;

import com.example.ScoreUniversity.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepo extends JpaRepository<Score,Long> {
    @Query("SELECT s FROM Score s WHERE s.courseName = ?1")
    Score findByName(String name);
    @Query("DELETE FROM Score s WHERE s.courseName = ?1")
    boolean deleteByName(String name);
}
