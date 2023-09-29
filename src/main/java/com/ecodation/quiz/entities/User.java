package com.ecodation.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @NotEmpty(message = "Lütfen kullanıcı adınızı giriniz")
    @Column(name = "userName", unique = true)
    private String username;
    @NotEmpty(message = "Lütfen adınızı giriniz")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Lütfen soyadınızı giriniz")
    @Column(name = "surname")
    private String surname;
    @NotEmpty(message = "Lütfen Email adresinizi giriniz")
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToOne
    private UserRole userRole;

    @JsonIgnore
    @ManyToMany
    private List<Quiz> quiz;





}
