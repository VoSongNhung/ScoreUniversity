package com.example.ScoreUniversity.controller.WebController;

import com.example.ScoreUniversity.entity.Score;
import com.example.ScoreUniversity.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/v1/web/score")
public class ScoreControllerWeb {
    @Autowired
    ScoreService scoreService;
    @GetMapping("/getAll")
    public String showScore(Model model) {
        // Tạo một đối tượng Score
        List<Score> score = scoreService.getAllScore();

        // Truyền đối tượng Score vào template Thymeleaf thông qua Model
        model.addAttribute("score", score);

        // Trả về tên của template Thymeleaf (ví dụ: scoreform.html)
        return "scoreform";
    }
}

