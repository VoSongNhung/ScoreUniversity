package com.example.ScoreUniversity.service.ServiceImpl;

import com.example.ScoreUniversity.dto.ScoreDTO;
import com.example.ScoreUniversity.entity.Score;
import com.example.ScoreUniversity.entity.Student;
import com.example.ScoreUniversity.mapper.ScoreMapper;
import com.example.ScoreUniversity.repository.ScoreRepo;
import com.example.ScoreUniversity.service.ScoreService;
import com.example.ScoreUniversity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreImpl implements ScoreService{
    @Autowired
    ScoreRepo scoreRepo;
    @Autowired
    StudentService studentService;
    @Override
    public List<Score> getAllScore() {
        return scoreRepo.findAll();
    }

    @Override
    public Score getScoreByCourseName(String name) {
        return scoreRepo.findByName(name);
    }

    @Override
    public Score addScore(ScoreDTO scoreDTO) {
        Student student = studentService.getStudentByID(scoreDTO.getIdstudent());
        Score score = ScoreMapper.MAPPER.scoreDTOToScore(scoreDTO);
        score.setStudent(student);
        return scoreRepo.save(score);
    }

    @Override
    public Score updateScoreByCourseName(Score score, String name) {
        Score score1 = scoreRepo.findByName(name);
        if(score1!= null){
            score.setOrdinalNumbers(score1.getOrdinalNumbers());
        }
        else {
            throw new NullPointerException("Coure is null");
        }
        return scoreRepo.save(score);
    }

    @Override
    public boolean deleteScore(String name) {
        return scoreRepo.deleteByName(name);
    }
}
