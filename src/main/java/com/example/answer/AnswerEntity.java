package com.example.answer;

import com.example.question.QuestionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answer")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;

    @Column(
            name = "is_correct",
            columnDefinition = "boolean default false"
    )
    boolean isCorrect = false;

    @ManyToOne
    @JoinColumn(name = "question_id")
    QuestionEntity question;

    public AnswerEntity(String name, boolean isCorrect, QuestionEntity question) {
        this.name = name;
        this.isCorrect = isCorrect;
        this.question = question;
    }
}
