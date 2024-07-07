package com.example.answer;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AnswerRepository extends CrudRepository<AnswerEntity, Integer> {
    ArrayList<AnswerEntity> findAllByQuestionId(int questionId);
    void deleteAllByQuestionId(int questionId);
}
