package com.example.tests;

import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<TestEntity, Integer> {
}
