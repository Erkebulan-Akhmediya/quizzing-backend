package com.example.tests;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private int duration;

    TestEntity(String name, String description, int duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }
}
