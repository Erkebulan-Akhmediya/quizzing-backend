package com.example.question;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<QuestionEntity, Integer> {
    List<QuestionEntity> findByTestId(int test_id);
    void deleteByTestId(int test_id);
}
