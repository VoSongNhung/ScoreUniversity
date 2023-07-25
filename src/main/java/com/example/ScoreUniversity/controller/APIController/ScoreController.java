package com.example.ScoreUniversity.controller.APIController;

import com.example.ScoreUniversity.dto.ScoreDTO;
import com.example.ScoreUniversity.entity.Score;
import com.example.ScoreUniversity.entity.Score;
import com.example.ScoreUniversity.repository.ScoreRepo;
import com.example.ScoreUniversity.repository.ScoreRepo;
import com.example.ScoreUniversity.service.ScoreService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/v1/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @GetMapping
//    @RolesAllowed({"TEACHER","STUDENT"})
    public ResponseEntity<List<Score>> getAllScore() {
        List<Score> Scores = scoreService.getAllScore();
        return new ResponseEntity<>(Scores, HttpStatus.OK);
    }
    @GetMapping("/{id}")
//    @RolesAllowed({"TEACHER","STUDENT"})
    public ResponseEntity<Score> getUserById(@PathVariable String name) {
        Score score = scoreService.getScoreByCourseName(name);
        if (score != null) {
            return ResponseEntity.ok(score);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
//    @RolesAllowed({"TEACHER","STUDENT"})
    public ResponseEntity<Score> createUserWithRole(@RequestBody ScoreDTO scoreDTO) {
//        scoreService.addScore(score);
        return ResponseEntity.status(HttpStatus.CREATED).body(scoreService.addScore(scoreDTO));
    }
    @PutMapping("/{id}")
    @RolesAllowed({"TEACHER","STUDENT"})
    public ResponseEntity<Score> updateUser(@RequestBody Score score,@PathVariable String name) {
        Score updatedScore = scoreService.updateScoreByCourseName(score,name);
        if (updatedScore != null) {
            return ResponseEntity.ok(updatedScore);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
//    @RolesAllowed({"TEACHER","STUDENT"})
    public void deleteScore(@PathVariable String name) {
        scoreService.deleteScore(name);
    }
}
