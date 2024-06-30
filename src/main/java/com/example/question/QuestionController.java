package com.example.question;

import com.example.question.dto.QuestionDto;
import com.example.tests.TestEntity;
import com.example.tests.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tests/{test_id}/question")
public class QuestionController {
    private final QuestionService questionService;
    private final TestService testService;

    QuestionController(QuestionService questionService, TestService testService) {
        this.questionService = questionService;
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<Void> createQuestion(
            @PathVariable("test_id") int testId,
            @RequestBody QuestionDto questionDto
    ) {
        try {
            TestEntity test = this.testService.getTestById(testId);
            if (test == null) return ResponseEntity.notFound().build();
            QuestionEntity question = new QuestionEntity(questionDto.name, test);
            this.questionService.createQuestion(question);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/multiple")
    public ResponseEntity<Void> createMultipleQuestions(
            @PathVariable("test_id") int testId,
            @RequestBody QuestionDto[] questionDtos
    ) {
        try {
            TestEntity test = this.testService.getTestById(testId);
            if (test == null) return ResponseEntity.notFound().build();
            for (QuestionDto questionDto : questionDtos) {
                QuestionEntity question = new QuestionEntity(questionDto.name, test);
                this.questionService.createQuestion(question);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{question_id}")
    public ResponseEntity<QuestionDto> getQuestionById(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId
    ) {
        try {
            TestEntity test = this.testService.getTestById(testId);
            if (test == null) return ResponseEntity.notFound().build();

            QuestionEntity question = this.questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.notFound().build();

            if (!question.getTest().equals(test)) return ResponseEntity.notFound().build();

            QuestionDto questionDto = new QuestionDto(
                    question.getId(),
                    question.getName(),
                    testId
            );
            return ResponseEntity.ok().body(questionDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestionsByTestId(@PathVariable("test_id") int testId) {
        try {
            List<QuestionEntity> questionEntities = this.questionService.getAllQuestionsByTestId(testId);
            List<QuestionDto> questionDtos = new ArrayList<>();
            for (QuestionEntity questionEntity : questionEntities) {
                questionDtos.add(
                        new QuestionDto(questionEntity.getId(), questionEntity.name, testId)
                );
            }
            return ResponseEntity.ok(questionDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{question_id}")
    public ResponseEntity<Void> deleteQuestionById(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId
    ) {
        try {
            TestEntity test = this.testService.getTestById(testId);
            if (test == null) return ResponseEntity.notFound().build();
            this.questionService.deleteQuestionById(questionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllQuestionsByTestId(@PathVariable("test_id") int testId) {
        try {
            this.questionService.deleteAllQuestionsByTestId(testId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
