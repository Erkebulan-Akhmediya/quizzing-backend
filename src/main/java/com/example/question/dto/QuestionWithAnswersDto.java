package com.example.question.dto;

import com.example.answer.dto.AnswerDto;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class QuestionWithAnswersDto extends QuestionDto {
    public List<AnswerDto> answers;

    public QuestionWithAnswersDto(int id, String name, int testId, List<AnswerDto> answers) {
        this.id = id;
        this.name = name;
        this.testId = testId;
        this.answers = answers;
    }
}
