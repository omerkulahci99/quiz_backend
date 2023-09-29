package com.ecodation.quiz.services.concretes;

import com.ecodation.quiz.entities.Question;
import com.ecodation.quiz.repositorys.QuestionRepository;
import com.ecodation.quiz.services.abstracts.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionManager implements QuestionService {

    private final QuestionRepository questionRepository;
    public QuestionManager(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question find(int questionId) {
        return questionRepository.findById(questionId) ;
    }

    @Override
    public Question update(Question question) {
        Question questionUpdated = questionRepository.findById(question.getId());
        questionUpdated.setQuestionText(question.getQuestionText());
        questionUpdated.setCorrectAnswer(question.getCorrectAnswer());
        questionRepository.save(questionUpdated);
        return questionUpdated;
    }


    @Override
    public Question delete(int questionId) {
        Question questionDeleted = questionRepository.findById(questionId);
        questionRepository.delete(questionDeleted);
        return questionDeleted;

    }
}
