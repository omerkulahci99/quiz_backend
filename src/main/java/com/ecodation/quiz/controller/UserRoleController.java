package com.ecodation.quiz.controller;

import com.ecodation.quiz.entities.UserRole;
import com.ecodation.quiz.services.abstracts.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/user-roles") // Controller için genel bir URL yolu belirliyoruz
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/create") // Yeni bir kullanıcı rolü oluşturmak için POST isteği
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole) {
        UserRole savedUserRole = userRoleService.saveUserRole(userRole);
        return new ResponseEntity<>(savedUserRole, HttpStatus.CREATED);
    }

    @GetMapping("/{userRoleId}") // Belirli bir kullanıcı rolünü getirmek için GET isteği
    public ResponseEntity<UserRole> getUserRole(@PathVariable int userRoleId) {
        UserRole userRole = userRoleService.find(userRoleId);
        if (userRole != null) {
            return new ResponseEntity<>(userRole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all") // Tüm kullanıcı rollerini getirmek için GET isteği
    public ResponseEntity<List<UserRole>> getAllUserRoles() {
        List<UserRole> userRoles = userRoleService.findAll();
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userRoleId}") // Belirli bir kullanıcı rolünü silmek için DELETE isteği
    public ResponseEntity<Void> deleteUserRole(@PathVariable int userRoleId) {
        UserRole existingUserRole = userRoleService.find(userRoleId);
        if (existingUserRole != null) {
            userRoleService.delete(userRoleId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

