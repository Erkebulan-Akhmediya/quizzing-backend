package com.example.answer.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnswerDto {
    public int questionId;
    public int id;
    public String name;
    public boolean isCorrect;
}
