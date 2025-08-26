package com.example.QuoraAppCloneApplication.dtos;

import lombok.Data;

@Data
public class AnswerDTO {

    private Long id;
    private String content;
    private Long userId;
    private Long questionId;

}
