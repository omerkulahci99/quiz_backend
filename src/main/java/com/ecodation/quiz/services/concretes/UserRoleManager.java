package com.ecodation.quiz.services.concretes;

import com.ecodation.quiz.entities.UserRole;
import com.ecodation.quiz.repositorys.UserRoleRepository;
import com.ecodation.quiz.services.abstracts.UserRoleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserRoleManager implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    public UserRoleManager(UserRoleRepository userRoleRepository){
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole find(int userRoleId) {
        return userRoleRepository.findById(userRoleId);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole delete(int userRoleId) {
       UserRole userRoleDeleted = userRoleRepository.findById(userRoleId);
       userRoleRepository.delete(userRoleDeleted);
       return userRoleDeleted;
    }
}
