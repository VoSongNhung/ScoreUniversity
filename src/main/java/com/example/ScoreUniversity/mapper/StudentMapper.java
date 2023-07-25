package com.example.ScoreUniversity.mapper;

import com.example.ScoreUniversity.entity.Student;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);
    Student userRepresentaitionToStudent (UserRepresentation userRepresentation);
    UserRepresentation studentToUserRepresentaition(Student student);
    List<Student> listUserRepresentaitionToStudent (List<UserRepresentation> userRepresentationList);
}
