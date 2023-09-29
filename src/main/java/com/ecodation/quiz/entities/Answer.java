package com.ecodation.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "answer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "question" })
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "a_order")
    private int order;

    @Column(name = "text")
    private String text;


    @JsonIgnore
    @ManyToOne
    private Question question;


}
