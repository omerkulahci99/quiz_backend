package com.ecodation.quiz.services.abstracts;

import com.ecodation.quiz.entities.Quiz;
import com.ecodation.quiz.entities.User;
import com.ecodation.quiz.entities.UserQuizRelation;

import java.util.List;

public interface UserQuizRelationService {
    UserQuizRelation saveUserQuizRelation(UserQuizRelation userQuizRelation);
    List<UserQuizRelation> findByUser(int userId);

    UserQuizRelation update(UserQuizRelation userQuizRelation);

    UserQuizRelation takeTheQuiz(int userId, Quiz quiz);

    List<UserQuizRelation> findByQuiz(Quiz quiz);

    UserQuizRelation delete(int userQuizRelationId);

    UserQuizRelation findById(int userQuizId);

    UserQuizRelation findByUserAndQuiz(int userId, int quizId);

}
