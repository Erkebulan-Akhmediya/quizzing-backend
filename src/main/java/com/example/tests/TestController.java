package com.example.tests;

import com.example.tests.dto.TestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/tests")
public class TestController {
    private final TestService testService;

    TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<Void> createTest(@RequestBody TestDto testDto) {
        TestEntity test = new TestEntity(testDto.name, testDto.description, testDto.duration);
        this.testService.createTest(test);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable("id") int id) {
        TestEntity test = this.testService.getTestById(id);
        if (test == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(
                    new TestDto(test.getName(), test.getDescription(), test.getDuration())
            );
        }
    }

    @GetMapping
    public ResponseEntity<List<TestDto>> getAllTests() {
        ArrayList<TestDto> testDtos = new ArrayList<>();
        ArrayList<TestEntity> tests = this.testService.getAllTests();
        for (TestEntity test : tests) {
            testDtos.add(new TestDto(test.getName(), test.getDescription(), test.getDuration()));
        }
        return ResponseEntity.ok(testDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable("id") int id) {
        try {
            this.testService.deleteTestById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
