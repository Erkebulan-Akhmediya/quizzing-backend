package com.example.answer;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void createAnswer(AnswerEntity answer) {
        this.answerRepository.save(answer);
    }

    public ArrayList<AnswerEntity> getAllAnswersByQuestionId(int questionId) {
        return this.answerRepository.findAllByQuestionId(questionId);
    }

    public AnswerEntity getAnswerById(int answerId) {
        return this.answerRepository.findById(answerId).orElse(null);
    }

    public void updateAnswerById(int id, AnswerEntity newAnswer) throws Exception {
        AnswerEntity oldAnswer = this.answerRepository.findById(id).orElse(null);
        if (oldAnswer == null) throw new Exception("not found");

        oldAnswer.setName(newAnswer.getName());
        oldAnswer.setCorrect(newAnswer.isCorrect());
        this.answerRepository.save(oldAnswer);
    }

    @Transactional
    public void deleteAllAnswersByQuestionId(int questionId) {
        this.answerRepository.deleteAllByQuestionId(questionId);
    }

    public void deleteAnswerById(int answerId) {
        this.answerRepository.deleteById(answerId);
    }
}
