package com.ecodation.quiz.controller;

import com.ecodation.quiz.entities.User;
import com.ecodation.quiz.services.abstracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/users") // Controller için genel bir URL yolu belirliyoruz
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create") // Yeni bir kullanıcı oluşturmak için POST isteği
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}") // Belirli bir kullanıcıyı getirmek için GET isteği
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        User user = userService.find(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}") // Belirli bir e-posta adresine sahip kullanıcıyı getirmek için GET isteği
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/username/{username}") // Belirli bir kullanıcı adına sahip kullanıcıyı getirmek için GET isteği
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{userId}") // Belirli bir kullanıcıyı silmek için DELETE isteği
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        User existingUser = userService.find(userId);
        if (existingUser != null) {
            userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

