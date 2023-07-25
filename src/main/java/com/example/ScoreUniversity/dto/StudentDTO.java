package com.example.ScoreUniversity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    public String id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    private String roles;
}
