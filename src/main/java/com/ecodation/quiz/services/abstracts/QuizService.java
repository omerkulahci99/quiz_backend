package com.ecodation.quiz.services.abstracts;

import com.ecodation.quiz.entities.Quiz;
import com.ecodation.quiz.entities.User;
import java.util.List;

public interface QuizService {

    Quiz saveQuiz(Quiz quiz);

    Quiz find(int quizId);

    List<Quiz> findQuizByUser(User user);


    Quiz update(Quiz quiz);

    Quiz delete(int quizId);



}
