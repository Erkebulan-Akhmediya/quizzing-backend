package com.example.question;

import com.example.tests.TestEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;

    @ManyToOne
    @JoinColumn(name = "test_id")
    TestEntity test;

    @OneToMany
    List<QuestionEntity> questions;

    QuestionEntity(String name,TestEntity test) {
        this.name = name;
        this.test = test;
    }
}
