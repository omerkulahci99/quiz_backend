package com.ecodation.quiz.repositorys;

import com.ecodation.quiz.entities.Quiz;
import com.ecodation.quiz.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("quizRepository")
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    Quiz findById(int quizId);
    List<Quiz> findByUsers(User user);


}
