package com.ecodation.quiz.repositorys;

import com.ecodation.quiz.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer > {
    User findById(int userId);
    User findByEmail(String email);
    User findByUsername(String userName);

}
