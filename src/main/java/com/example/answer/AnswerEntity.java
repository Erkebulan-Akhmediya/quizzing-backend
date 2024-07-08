package com.example.answer;

import com.example.question.QuestionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "answer")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(
            name = "is_correct",
            columnDefinition = "boolean default false"
    )
    private boolean isCorrect = false;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    public AnswerEntity(String name, boolean isCorrect, QuestionEntity question) {
        this.name = name;
        this.isCorrect = isCorrect;
        this.question = question;
    }
}
