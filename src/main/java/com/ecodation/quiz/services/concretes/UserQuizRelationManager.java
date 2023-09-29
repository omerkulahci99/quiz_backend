package com.ecodation.quiz.services.concretes;

import com.ecodation.quiz.entities.Quiz;
import com.ecodation.quiz.entities.User;
import com.ecodation.quiz.entities.UserQuizRelation;
import com.ecodation.quiz.repositorys.QuizRepository;
import com.ecodation.quiz.repositorys.UserQuizRelationRepository;
import com.ecodation.quiz.services.abstracts.QuizService;
import com.ecodation.quiz.services.abstracts.UserQuizRelationService;
import com.ecodation.quiz.services.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQuizRelationManager implements UserQuizRelationService
{

    private final UserQuizRelationRepository userQuizRelationRepository;
    private final UserService userService;
    private final QuizRepository quizRepository;

    public UserQuizRelationManager(UserQuizRelationRepository userQuizRelationRepository, UserService userService, QuizRepository quizRepository) {
        this.userQuizRelationRepository = userQuizRelationRepository;
        this.userService = userService;
        this.quizRepository = quizRepository;
    }

    @Override
    public UserQuizRelation saveUserQuizRelation(UserQuizRelation userQuizRelation) {
        return userQuizRelationRepository.save(userQuizRelation);
    }

    @Override
    public UserQuizRelation takeTheQuiz(int userId, Quiz quiz){
        User user = userService.find(userId);
        UserQuizRelation takeTheQuiz = userQuizRelationRepository.findByUserAndQuiz(user, quiz);
        takeTheQuiz.setCompleted(true);
        userQuizRelationRepository.save(takeTheQuiz);
        return takeTheQuiz;
    }
    @Override
    public List<UserQuizRelation> findByUser(int userId) {
        User user = userService.find(userId);
        return userQuizRelationRepository.findByUser(user);
    }

    @Override
    public UserQuizRelation update(UserQuizRelation userQuizRelation) {
        UserQuizRelation userQuizRelationUpdated = userQuizRelationRepository.findById(userQuizRelation.getId()).get();
        userQuizRelationUpdated.setQuizScore(userQuizRelation.getQuizScore());
        userQuizRelationUpdated.setCompleted(userQuizRelation.isCompleted());
        userQuizRelationRepository.save(userQuizRelationUpdated);
        return userQuizRelationUpdated;
    }

    @Override
    public List<UserQuizRelation> findByQuiz(Quiz quiz) {
        return userQuizRelationRepository.findByQuiz(quiz);
    }

    @Override
    public UserQuizRelation delete(int userQuizRelationId) {
        UserQuizRelation userQuizRelationDeleted = userQuizRelationRepository.findById(userQuizRelationId).get();
        userQuizRelationRepository.delete(userQuizRelationDeleted);
        return userQuizRelationDeleted;
    }

    @Override
    public UserQuizRelation findByUserAndQuiz(int userId, int quizId) {
        User user = userService.find(userId);
        Quiz quiz = quizRepository.findById(quizId);
        return userQuizRelationRepository.findByUserAndQuiz(user, quiz);
    }

    @Override
    public UserQuizRelation findById(int userQuizId) {
        UserQuizRelation userQuizRelation = userQuizRelationRepository.findById(userQuizId).get();
        return userQuizRelation;
    }
}
