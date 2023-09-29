package com.ecodation.quiz.repositorys;

import com.ecodation.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("questionRepository")
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findById(int questionId);
}
