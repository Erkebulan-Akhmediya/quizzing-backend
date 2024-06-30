package com.example.question;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void createQuestion(QuestionEntity question) {
        this.questionRepository.save(question);
    }

    public List<QuestionEntity> getAllQuestionsByTestId(int testId) {
        return this.questionRepository.findByTestId(testId);
    }

    public QuestionEntity getQuestionById(int questionId) {
        return this.questionRepository.findById(questionId).orElse(null);
    }

    public void deleteQuestionById(int questionId) {
        this.questionRepository.deleteById(questionId);
    }

    @Transactional
    public void deleteAllQuestionsByTestId(int testId) {
        this.questionRepository.deleteByTestId(testId);
    }

}
