package com.ecodation.quiz.controller;

import com.ecodation.quiz.entities.Quiz;
import com.ecodation.quiz.entities.User;
import com.ecodation.quiz.entities.UserQuizRelation;
import com.ecodation.quiz.services.abstracts.QuizService;
import com.ecodation.quiz.services.abstracts.UserQuizRelationService;
import com.ecodation.quiz.services.abstracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/1.0/quizzes") // Controller için genel bir URL yolu belirliyoruz
public class QuizController {
    private final UserService userService;
    private final QuizService quizService;
    private final UserQuizRelationService userQuizRelationService;

    public QuizController(QuizService quizService, UserService userService, UserQuizRelationService userQuizRelationService) {
        this.quizService = quizService;
        this.userService = userService;
        this.userQuizRelationService = userQuizRelationService;
    }

    @PostMapping("/create") // Yeni bir quiz oluşturmak için POST isteği
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/{quizId}") // Belirli bir quiz'i getirmek için GET isteği
    public ResponseEntity<?> getQuiz(@PathVariable int userId, @PathVariable int quizId) {
        Quiz quiz = quizService.find(quizId);
        userQuizRelationService.takeTheQuiz(userId, quiz);
        if (quiz != null) {
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}") // Bir kullanıcının tüm quizz'lerini getirmek için GET isteği
    public ResponseEntity<List<Quiz>> getUserQuizzes(@PathVariable int userId) {
        User user = userService.find(userId); // Kullanıcı nesnesi oluşturulmalı, burada varsayılan bir User oluşturduk.
        List<Quiz> quizzes = quizService.findQuizByUser(user);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @PutMapping("/update") // Bir quiz'i güncellemek için PUT isteği
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        Quiz existingQuiz = quizService.find(quiz.getId());
        if (existingQuiz != null) {
            Quiz updatedQuiz = quizService.update(quiz);
            return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{quizId}") // Bir quiz'i silmek için DELETE isteği
    public ResponseEntity<Void> deleteQuiz(@PathVariable int quizId) {
        Quiz existingQuiz = quizService.find(quizId);
        if (existingQuiz != null) {
            quizService.delete(quizId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

