package com.ecodation.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Lob
    @Column(name = "questionText", length = 1000)
    private String questionText;

    @Column(name = "uploadDate")
    private Date uploadDate;


    @OneToOne
    private Answer correctAnswer;

    @JsonIgnore
    @ManyToOne
    private Quiz quiz;


    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //sorular silinince cevaplar da silinecek!
    private List<Answer> answers;
}
