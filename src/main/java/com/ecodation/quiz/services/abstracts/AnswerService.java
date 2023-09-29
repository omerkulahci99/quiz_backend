package com.ecodation.quiz.services.abstracts;

import com.ecodation.quiz.entities.Answer;

public interface AnswerService {

    Answer save(Answer answer);

    Answer find(int answerId);

    Answer update(Answer newAnswer);

    Answer delete(int answerId);

}
