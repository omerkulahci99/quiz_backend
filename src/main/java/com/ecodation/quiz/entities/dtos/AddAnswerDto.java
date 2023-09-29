package com.ecodation.quiz.entities.dtos;

import com.ecodation.quiz.entities.Question;
import lombok.Data;

@Data
public class AddAnswerDto {

    private int order;
    private String text;
    private Question question;

}
