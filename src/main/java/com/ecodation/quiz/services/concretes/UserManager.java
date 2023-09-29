package com.ecodation.quiz.services.concretes;
import com.ecodation.quiz.entities.User;
import com.ecodation.quiz.repositorys.UserRepository;
import com.ecodation.quiz.services.abstracts.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User find(int userId) { return userRepository.findById(userId); }

    @Override
    public User findByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public User findByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    public User delete(int userId) {
        User userDelete = userRepository.findById(userId);
        userRepository.delete(userDelete);
        return userDelete;

    }
}
