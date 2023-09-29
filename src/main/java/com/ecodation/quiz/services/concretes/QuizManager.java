package com.ecodation.quiz.services.concretes;

import com.ecodation.quiz.entities.Quiz;
import com.ecodation.quiz.entities.User;
import com.ecodation.quiz.entities.UserQuizRelation;
import com.ecodation.quiz.repositorys.QuizRepository;
import com.ecodation.quiz.repositorys.UserQuizRelationRepository;
import com.ecodation.quiz.services.abstracts.QuizService;
import com.ecodation.quiz.services.abstracts.UserQuizRelationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizManager implements QuizService {

    private final QuizRepository quizRepository;
    private final UserQuizRelationService userQuizRelationService;
    public QuizManager(QuizRepository quizRepository, UserQuizRelationService userQuizRelationService){
        this.userQuizRelationService = userQuizRelationService;
        this.quizRepository = quizRepository;}

    @Override
    public Quiz saveQuiz(Quiz quiz) { Quiz newQuiz = quizRepository.save(quiz);
        for (User user : quiz.getUsers()){
            UserQuizRelation userQuizRelation = new UserQuizRelation();
            userQuizRelation.setUser(user);
            userQuizRelation.setQuiz(newQuiz);
            userQuizRelation.setQuizScore(0);
            userQuizRelation.setCompleted(false);
            userQuizRelationService.saveUserQuizRelation(userQuizRelation);
        }
        return newQuiz; }

    @Override
    public Quiz find(int quizId) {

        return quizRepository.findById(quizId);
    }

    @Override
    public List<Quiz> findQuizByUser(User user) {
        return quizRepository.findByUsers(user);
    }

    @Override
    public Quiz update(Quiz quiz) {
        Quiz quizUpdated = quizRepository.findById(quiz.getId());
        quizUpdated.setQuizName(quiz.getQuizName());
        quizUpdated.setStartTime(quiz.getStartTime());
        quizUpdated.setEndTime(quiz.getEndTime());
        quizRepository.save(quizUpdated);
        return quizUpdated;
    }

    @Override
    public Quiz delete(int quizId) {

        Quiz quizDeleted = quizRepository.findById(quizId);
        List<UserQuizRelation> userQuizRelations = userQuizRelationService.findByQuiz(quizDeleted);
        for (UserQuizRelation userQuizRelation :  userQuizRelations ){
            userQuizRelationService.delete(userQuizRelation.getId());
        }

        quizRepository.delete(quizDeleted);
        return quizDeleted;
    }
}
