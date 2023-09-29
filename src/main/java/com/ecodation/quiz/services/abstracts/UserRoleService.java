package com.ecodation.quiz.services.abstracts;

import com.ecodation.quiz.entities.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole saveUserRole(UserRole userRole);

    UserRole find(int userRoleId);

    List<UserRole> findAll();

    UserRole delete(int userRoleId);


}
