package com.example.tests;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void createTest(TestEntity test) {
        this.testRepository.save(test);
    }

    public TestEntity getTestById(int id) {
        return this.testRepository.findById(id).orElse(null);
    }

    public ArrayList<TestEntity> getAllTests() {
        return (ArrayList<TestEntity>) this.testRepository.findAll();
    }

    public void deleteTestById(int id) {
        this.testRepository.deleteById(id);
    }

}
