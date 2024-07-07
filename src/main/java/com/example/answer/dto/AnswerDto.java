package com.example.answer.dto;

public class AnswerDto {
    public int questionId;
    public int id;
    public String name;
    public boolean isCorrect;

    public AnswerDto(int questionId, int id, String name, boolean isCorrect) {
        this.questionId = questionId;
        this.id = id;
        this.name = name;
        this.isCorrect = isCorrect;
    }
}
