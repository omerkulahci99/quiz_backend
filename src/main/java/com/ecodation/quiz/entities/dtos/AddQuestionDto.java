package com.ecodation.quiz.entities.dtos;

import com.ecodation.quiz.entities.Answer;
import com.ecodation.quiz.entities.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddQuestionDto {

    private String questionText;
    private Date uploadDate;
    private Answer correctAnswer;
    private Quiz quiz;
}
