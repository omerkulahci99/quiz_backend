package com.ecodation.quiz.controller;

import com.ecodation.quiz.entities.Answer;
import com.ecodation.quiz.entities.dtos.AddAnswerDto;
import com.ecodation.quiz.services.abstracts.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/answers") // Controller için genel bir URL yolu belirliyoruz
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/create") // Yeni bir cevap oluşturmak için POST isteği
    public ResponseEntity<Answer> createAnswer(@RequestBody AddAnswerDto answer) {
        Answer addedAnswer = new Answer();
        addedAnswer.setOrder(answer.getOrder());
        addedAnswer.setText(answer.getText());
        addedAnswer.setQuestion(answer.getQuestion());
        Answer savedAnswer = answerService.save(addedAnswer);
        return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
    }

    @GetMapping("/{answerId}") // Belirli bir cevabı getirmek için GET isteği
    public ResponseEntity<Answer> getAnswer(@PathVariable int answerId) {
        Answer answer = answerService.find(answerId);
        if (answer != null) {
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update") // Bir cevabı güncellemek için PUT isteği
    public ResponseEntity<Answer> updateAnswer(@RequestBody Answer newAnswer) {
        Answer existingAnswer = answerService.find(newAnswer.getId());
        if (existingAnswer != null) {
            Answer updatedAnswer = answerService.update(newAnswer);
            return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{answerId}") // Bir cevabı silmek için DELETE isteği
    public ResponseEntity<Void> deleteAnswer(@PathVariable int answerId) {
        Answer existingAnswer = answerService.find(answerId);
        if (existingAnswer != null) {
            answerService.delete(answerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

