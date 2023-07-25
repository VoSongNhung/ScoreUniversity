package com.example.ScoreUniversity.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
        @Id
//        @GeneratedValue(strategy= GenerationType.IDENTITY)
//        private Long id;
        private String id;

        private String firstName;
        private String lastName;
        private  String username;
        private String email;
        private String roles;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getRoles() {
                return roles;
        }

        public void setRoles(String roles) {
                this.roles = roles;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }
//        @OneToOne(mappedBy ="user")
//        @JsonIgnore
//        private Wallet wallet;
}
