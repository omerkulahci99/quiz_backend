package com.ecodation.quiz.services.concretes;

import com.ecodation.quiz.entities.Answer;
import com.ecodation.quiz.repositorys.AnswerRepository;
import com.ecodation.quiz.services.abstracts.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerManager implements AnswerService {

    private final AnswerRepository answerRepository;
    public AnswerManager(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer find(int answerId) { return answerRepository.findById(answerId); }

    @Override
    public Answer update(Answer newAnswer) {
        Answer answerUpdated = answerRepository.findById(newAnswer.getId());
        answerUpdated.setText(newAnswer.getText());
        answerUpdated.setOrder(newAnswer.getOrder());
        answerRepository.save(answerUpdated);
        return answerUpdated;

    }

    @Override
    public Answer delete (int answerId) {
        Answer answerDeleted = answerRepository.findById(answerId);
        answerRepository.delete(answerDeleted);
        return answerDeleted;
    }
}
