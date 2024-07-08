package com.example.question;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Integer> {
    List<QuestionEntity> findByTestId(int test_id);
    void deleteByTestId(int test_id);
}
