package com.ecodation.quiz.services.abstracts;

import com.ecodation.quiz.entities.User;

public interface UserService {

    User saveUser(User user);
    User find(int userId);
    User findByEmail(String email);
    User findByUsername(String username);
    User delete(int userId);


}
