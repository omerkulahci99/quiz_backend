package com.ecodation.quiz.repositorys;

import com.ecodation.quiz.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("answerRepository")
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Answer findById(int answerId);
}
