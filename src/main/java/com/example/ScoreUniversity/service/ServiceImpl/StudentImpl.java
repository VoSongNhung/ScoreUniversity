package com.example.ScoreUniversity.service.ServiceImpl;

import com.example.ScoreUniversity.constants.KeyCloakConstant;
import com.example.ScoreUniversity.entity.Student;
import com.example.ScoreUniversity.mapper.StudentMapper;
import com.example.ScoreUniversity.service.StudentService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentImpl implements StudentService {
    private final Keycloak keycloak = getKeycloak();
    private final RealmResource realmResource = keycloak.realm(realm);
    private final UsersResource usersResource = realmResource.users();

    private static final String authServerUrl = KeyCloakConstant.url;
    private static final String realm = KeyCloakConstant.realm;
    private static final String clientId = KeyCloakConstant.resource;
    private static final String clientSecret = KeyCloakConstant.credentialssecret;
    private static final String userName = KeyCloakConstant.username;
    private static final String passWord = KeyCloakConstant.password;
    private Keycloak getKeycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(userName)
                .password(passWord)
                .build();
    }
    @Override
    public List<Student> listStudent() {
        //lấy danh sách các đại diện người dùng
        List<UserRepresentation> users = usersResource.list();
        List<Student> listStudent = new ArrayList<>();
        for (UserRepresentation user : users) {
            Student students = StudentMapper.MAPPER.userRepresentaitionToStudent(user);
            //lấy danh sách các đại diện vai trò (RoleRepresentation) của người dùng hiện tại
            List<RoleRepresentation> roleRepresentations = usersResource.get(user.getId()).roles().realmLevel().listAll();
            List<String> roles = new ArrayList<>();
            for (RoleRepresentation roleRepresentation : roleRepresentations) {
                roles.add(roleRepresentation.getName());
            }
            students.setRoles(roles.toString());
//            students.setFirstname(user.getFirstName().toString());
            listStudent.add(students);

        }
        return listStudent;
    }

    @Override
    public Student getStudentByID(String id) {
        UserRepresentation user = usersResource.get(id).toRepresentation();
        Student student = StudentMapper.MAPPER.userRepresentaitionToStudent(user);
        return student;
    }

    @Override
    public UserRepresentation createStudentWithRole(UserRepresentation userRepresentation, String roleName) {
        userRepresentation.setEnabled(true);
        // gọi mehthod create để tạo người dùng mới
        Response response = usersResource.create(userRepresentation);
        //method create trả về đối tượng response
        URI uri = response.getLocation();
        String userId = uri.getPath().substring(uri.getPath().lastIndexOf('/') + 1);
        RoleRepresentation role =
                realmResource.roles().get(roleName).toRepresentation();
        List<RoleRepresentation> roles = new ArrayList<>();
        roles.add(role);
        realmResource.users().get(userId).roles().realmLevel().add(roles);
        return usersResource.get(userId).toRepresentation();
    }


    @Override
    public UserRepresentation updateStudent(String id, UserRepresentation userRepresentation) {
        usersResource.get(id).update(userRepresentation);
        return usersResource.get(id).toRepresentation();
    }

    @Override
    public void deleteStudent(String id) {
        usersResource.get(id).remove();
    }
}
