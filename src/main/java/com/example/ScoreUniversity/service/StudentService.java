package com.example.ScoreUniversity.service;

import com.example.ScoreUniversity.entity.Student;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface StudentService {
    List<Student> listStudent();
    Student getStudentByID(String id);
    UserRepresentation createStudentWithRole(UserRepresentation userRepresentation, String roleName);
    UserRepresentation updateStudent (String id, UserRepresentation userRepresentation);
    void deleteStudent (String id);
}
