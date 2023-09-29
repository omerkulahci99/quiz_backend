package com.ecodation.quiz.repositorys;

import com.ecodation.quiz.entities.Quiz;
import com.ecodation.quiz.entities.User;
import com.ecodation.quiz.entities.UserQuizRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userQuizRelationRepository")
public interface UserQuizRelationRepository extends JpaRepository<UserQuizRelation,Integer>{

    List<UserQuizRelation> findByUser(User user);
    List<UserQuizRelation> findByQuiz(Quiz quiz);
    UserQuizRelation findByUserAndQuiz(User user, Quiz quiz);


}
