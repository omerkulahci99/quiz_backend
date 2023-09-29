package com.ecodation.quiz.controller;

import com.ecodation.quiz.entities.Question;
import com.ecodation.quiz.entities.dtos.AddQuestionDto;
import com.ecodation.quiz.services.abstracts.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/questions") // Controller için genel bir URL yolu belirliyoruz
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/create") // Yeni bir soru oluşturmak için POST isteği
    public ResponseEntity<Question> createQuestion(@RequestBody AddQuestionDto question) {
        Question addedQuestion = new Question();
        addedQuestion.setQuestionText(question.getQuestionText());
        addedQuestion.setQuiz(question.getQuiz());
        addedQuestion.setUploadDate(question.getUploadDate());
        addedQuestion.setCorrectAnswer(question.getCorrectAnswer());
        Question savedQuestion = questionService.save(addedQuestion);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/{questionId}") // Belirli bir soruyu getirmek için GET isteği
    public ResponseEntity<Question> getQuestion(@PathVariable int questionId) {
        Question question = questionService.find(questionId);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{questionId}") // Bir soruyu güncellemek için PUT isteği
    public ResponseEntity<Question> updateQuestion(@PathVariable int questionId, @RequestBody Question question) {
        Question existingQuestion = questionService.find(questionId);
        if (existingQuestion != null) {
            Question updatedQuestion = questionService.update(question);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{questionId}") // Bir soruyu silmek için DELETE isteği
    public ResponseEntity<Void> deleteQuestion(@PathVariable int questionId) {
        Question existingQuestion = questionService.find(questionId);
        if (existingQuestion != null) {
            questionService.delete(questionId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

