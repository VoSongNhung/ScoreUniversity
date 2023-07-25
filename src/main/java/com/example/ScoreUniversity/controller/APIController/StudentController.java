package com.example.ScoreUniversity.controller.APIController;

import com.example.ScoreUniversity.entity.Student;
import com.example.ScoreUniversity.repository.StudentRepo;
import com.example.ScoreUniversity.service.StudentService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    StudentRepo studentRepo;
    @GetMapping
//    @RolesAllowed({"TEACHER","STUDENT"})
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.listStudent();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @RolesAllowed({"TEACHER","STUDENT"})
    public ResponseEntity<Student> getUserById(@PathVariable String id) {
        Student user = studentService.getStudentByID(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
//    @RolesAllowed({"TEACHER","STUDENT"})
    public ResponseEntity<UserRepresentation> createUserWithRole(@RequestBody UserRepresentation userRepresentation, String roleName) {
        UserRepresentation userrep = studentService.createStudentWithRole(userRepresentation,"STUDENT");
        Student student = new Student();
        student.setId(userrep.getId());
        student.setFirstName(userRepresentation.getFirstName());
        student.setLastName(userRepresentation.getLastName());
        student.setUsername(userRepresentation.getUsername());
        student.setEmail(userRepresentation.getEmail());
        student.setRoles("STUDENT");
        studentRepo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(userrep);
    }
    @PutMapping("/{id}")
    @RolesAllowed({"TEACHER","STUDENT"})
    public ResponseEntity<UserRepresentation> updateUser(@PathVariable String id, @RequestBody UserRepresentation userRepresentation) {
        UserRepresentation updatedUser = studentService.updateStudent(id, userRepresentation);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @RolesAllowed({"TEACHER","STUDENT"})
    public void deleteUser(@PathVariable String id) {
        studentService.deleteStudent(id);
    }
}
