package com.ecodation.quiz.controller;

import com.ecodation.quiz.entities.Quiz;
import com.ecodation.quiz.entities.UserQuizRelation;
import com.ecodation.quiz.services.abstracts.QuizService;
import com.ecodation.quiz.services.abstracts.UserQuizRelationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/1.0/user-quiz-relations") // Controller için genel bir URL yolu belirliyoruz
public class UserQuizRelationController {

    private final QuizService quizService;
    private final UserQuizRelationService userQuizRelationService;

    public UserQuizRelationController(UserQuizRelationService userQuizRelationService, QuizService quizService) {
        this.userQuizRelationService = userQuizRelationService;
        this.quizService = quizService;
    }

    @PostMapping("/create") // Yeni bir kullanıcı-quiz ilişkisi oluşturmak için POST isteği
    public ResponseEntity<UserQuizRelation> createUserQuizRelation(@RequestBody UserQuizRelation userQuizRelation) {
        UserQuizRelation savedRelation = userQuizRelationService.saveUserQuizRelation(userQuizRelation);
        return new ResponseEntity<>(savedRelation, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}") // Belirli bir kullanıcının ilişkili quiz'lerini getirmek için GET isteği
    public ResponseEntity<List<UserQuizRelation>> getQuizRelationsByUser(@PathVariable int userId) {
        List<UserQuizRelation> relations = userQuizRelationService.findByUser(userId);
        return new ResponseEntity<>(relations, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/{quizId}") // Belirli bir kullanıcının ilişkili quiz'lerini getirmek için GET isteği
    public ResponseEntity<UserQuizRelation> findByUserAndQuiz(@PathVariable int userId, @PathVariable int quizId) {
        UserQuizRelation userQuizRelation = userQuizRelationService.findByUserAndQuiz(userId, quizId);
        return new ResponseEntity<>(userQuizRelation, HttpStatus.OK);
    }

    @PutMapping("/update") // Bir kullanıcı-quiz ilişkisini güncellemek için PUT isteği
    public ResponseEntity<UserQuizRelation> updateQuizRelation(@RequestBody UserQuizRelation userQuizRelation) {
        UserQuizRelation existingRelation = userQuizRelationService.findById(userQuizRelation.getId());
        if (existingRelation != null) {
            UserQuizRelation updatedRelation = userQuizRelationService.update(userQuizRelation);
            return new ResponseEntity<>(updatedRelation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/quiz/{quizId}") // Belirli bir quiz'e ait ilişkileri getirmek için GET isteği
    public ResponseEntity<?> getRelationsByQuiz(@PathVariable int quizId) {
        Quiz quiz = quizService.find(quizId);
        if (quiz != null) {
            List<UserQuizRelation> relations = userQuizRelationService.findByQuiz(quiz);
            return new ResponseEntity<>(relations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Belirtilen quiz bulunamadı", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{relationId}") // Bir kullanıcı-quiz ilişkisini silmek için DELETE isteği
    public ResponseEntity<Void> deleteQuizRelation(@PathVariable int relationId) {
        UserQuizRelation existingRelation = userQuizRelationService.findById(relationId);
        if (existingRelation != null) {
            userQuizRelationService.delete(relationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
