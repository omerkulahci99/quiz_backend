package com.ecodation.quiz.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "userRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "roleName")
    private String roleName;


}
