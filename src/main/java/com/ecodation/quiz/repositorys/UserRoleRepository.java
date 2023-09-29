package com.ecodation.quiz.repositorys;
import com.ecodation.quiz.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole,Integer > {
    UserRole findById(int userRoleId);

}