package com.example.question.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    public int id;
    public String name;
    public int testId;

    public QuestionDto(String name) {
        this.name = name;
    }

    public QuestionDto(String name, int testId) {
        this.name = name;
        this.testId = testId;
    }
}
