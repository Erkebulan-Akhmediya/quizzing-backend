package com.example.answer;

import com.example.answer.dto.AnswerDto;
import com.example.question.QuestionEntity;
import com.example.question.QuestionService;
import com.example.tests.TestEntity;
import com.example.tests.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/tests/{test_id}/question/{question_id}/answer")
public class AnswerController {
    private final TestService testService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    public AnswerController(
            TestService testService,
            QuestionService questionService,
            AnswerService answerService
    ) {
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<Void> createAnswer(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId,
            @RequestBody AnswerDto answerDto
    ) {
        try {
            TestEntity test = testService.getTestById(testId);
            if (test == null) return ResponseEntity.badRequest().build();

            QuestionEntity question = questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.badRequest().build();

            answerService.createAnswer(
                    new AnswerEntity(
                            answerDto.name,
                            answerDto.isCorrect,
                            question
                    )
            );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/multiple")
    public ResponseEntity<Void> createMultipleAnswer(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId,
            @RequestBody ArrayList<AnswerDto> answerDtos
    ) {
        try {
            TestEntity test = testService.getTestById(testId);
            if (test == null) return ResponseEntity.badRequest().build();

            QuestionEntity question = questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.badRequest().build();

            for (AnswerDto answerDto : answerDtos) {
                answerService.createAnswer(
                        new AnswerEntity(answerDto.name, answerDto.isCorrect, question)
                );
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<AnswerDto>> getAnswersByQuestionId(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId
    ) {
        try {
            TestEntity test = testService.getTestById(testId);
            if (test == null) return ResponseEntity.badRequest().build();

            QuestionEntity question = questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.badRequest().build();

            ArrayList<AnswerEntity> answers = this.answerService.getAllAnswersByQuestionId(questionId);
            ArrayList<AnswerDto> answerDtos = new ArrayList<>();
            for (AnswerEntity answer : answers) {
                answerDtos.add(
                        new AnswerDto(
                                answer.getQuestion().getId(),
                                answer.getId(),
                                answer.getName(),
                                answer.isCorrect()
                        )
                );
            }
            return ResponseEntity.ok(answerDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{answer_id}")
    public ResponseEntity<AnswerDto> getAnswerById(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId,
            @PathVariable("answer_id") int answerId
    ) {
        try {
            TestEntity test = this.testService.getTestById(testId);
            if (test == null) return ResponseEntity.badRequest().build();

            QuestionEntity question = this.questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.badRequest().build();

            AnswerEntity answer = this.answerService.getAnswerById(answerId);
            if (answer == null) return ResponseEntity.badRequest().build();

            return ResponseEntity.ok(
                    new AnswerDto(
                            answer.getQuestion().getId(),
                            answerId,
                            answer.getName(),
                            answer.isCorrect()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateAnswersByQuestionId(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId,
            @RequestBody ArrayList<AnswerDto> answerDtos
    ) {
        try {
            TestEntity test = testService.getTestById(testId);
            if (test == null) return ResponseEntity.badRequest().build();

            QuestionEntity question = questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.badRequest().build();

            for (AnswerDto answerDto : answerDtos) {
                this.answerService.updateAnswerById(
                        answerDto.id,
                        new AnswerEntity(answerDto.name, answerDto.isCorrect, question)
                );
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{answer_id}")
    public ResponseEntity<Void> updateAnswer(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId,
            @PathVariable("answer_id") int answerId,
            @RequestBody AnswerDto answerDto
    ) {
        try {
            TestEntity test = testService.getTestById(testId);
            if (test == null) return ResponseEntity.badRequest().build();

            QuestionEntity question = this.questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.badRequest().build();

            AnswerEntity answer = this.answerService.getAnswerById(answerId);
            if (answer == null) return ResponseEntity.badRequest().build();

            this.answerService.updateAnswerById(
                    answerId,
                    new AnswerEntity(answerDto.name, answerDto.isCorrect, question)
            );

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAnswersByQuestionId(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId
    ) {
        try {
            TestEntity test = this.testService.getTestById(testId);
            if (test == null) return ResponseEntity.badRequest().build();

            QuestionEntity question = this.questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.badRequest().build();

            this.answerService.deleteAllAnswersByQuestionId(questionId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{answer_id}")
    public ResponseEntity<Void> deleteAnswerById(
            @PathVariable("test_id") int testId,
            @PathVariable("question_id") int questionId,
            @PathVariable("answer_id") int answerId
    ) {
        try {
            TestEntity test = this.testService.getTestById(testId);
            if (test == null) return ResponseEntity.badRequest().build();

            QuestionEntity question = this.questionService.getQuestionById(questionId);
            if (question == null) return ResponseEntity.badRequest().build();

            AnswerEntity answer = this.answerService.getAnswerById(answerId);
            if (answer == null) return ResponseEntity.badRequest().build();

            this.answerService.deleteAnswerById(answerId);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
