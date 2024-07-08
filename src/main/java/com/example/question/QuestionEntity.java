package com.example.question;

import com.example.answer.AnswerEntity;
import com.example.tests.TestEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestEntity test;

    @OneToMany(targetEntity = AnswerEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<AnswerEntity> answers;

    QuestionEntity(String name,TestEntity test) {
        this.name = name;
        this.test = test;
    }
}
