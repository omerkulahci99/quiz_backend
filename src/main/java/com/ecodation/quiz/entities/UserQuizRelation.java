package com.ecodation.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserQuizRelation {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Quiz quiz;

    @JsonIgnore
    @ManyToOne
    private User user;

    private boolean isCompleted;
    private int quizScore;

}
