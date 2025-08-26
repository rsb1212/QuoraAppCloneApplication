package com.example.QuoraAppCloneApplication.controllers;

import com.example.QuoraAppCloneApplication.dtos.QuestionDTO;
import com.example.QuoraAppCloneApplication.models.Question;
import com.example.QuoraAppCloneApplication.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions(@RequestParam int page,@RequestParam int size){
        return questionService.getQuestions(page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id){
       Optional<Question> question=questionService.getQuestionById(id);
       return question.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Question createQuestion(@RequestBody QuestionDTO questionDTO){
       return questionService.createQuestion(questionDTO);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
       return ResponseEntity.notFound().build();
    }
}
