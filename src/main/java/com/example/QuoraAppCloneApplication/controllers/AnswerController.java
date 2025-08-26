package com.example.QuoraAppCloneApplication.controllers;

import com.example.QuoraAppCloneApplication.dtos.AnswerDTO;
import com.example.QuoraAppCloneApplication.models.Answer;
import com.example.QuoraAppCloneApplication.models.Comment;
import com.example.QuoraAppCloneApplication.services.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {
    private AnswerService answerService;
    public  AnswerController(AnswerService answerService){
        this.answerService=answerService;
    }
    @GetMapping("answer/{answerId}")
    public List<Answer> getAnswerByQuestionId(@PathVariable Long answerId, @RequestParam int page, @RequestParam int size){
        return answerService.getAnswerByQuestionId(answerId,page,size);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id){
    Optional<Answer> answer=answerService.getAnswerById(id);
       return answer.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Answer createAnswer(@RequestParam AnswerDTO answerDTO){
        return answerService.createAnswer(answerDTO);
    }
    @PostMapping
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id){
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
