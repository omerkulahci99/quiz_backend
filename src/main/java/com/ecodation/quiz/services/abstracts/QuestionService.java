package com.ecodation.quiz.services.abstracts;

import com.ecodation.quiz.entities.Question;

public interface QuestionService {

    Question save(Question question);

    Question find(int questionId);

    Question update(Question question);

    Question delete(int questionId);



}
