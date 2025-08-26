package com.example.QuoraAppCloneApplication.services;

import com.example.QuoraAppCloneApplication.dtos.AnswerDTO;
import com.example.QuoraAppCloneApplication.models.Answer;
import com.example.QuoraAppCloneApplication.models.Question;
import com.example.QuoraAppCloneApplication.repositories.AnswerRepository;
import com.example.QuoraAppCloneApplication.repositories.QuestionRepository;
import com.example.QuoraAppCloneApplication.repositories.UserRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private UserRepository userRepository;

   public AnswerService(AnswerRepository answerRepository,QuestionRepository questionRepository,UserRepository userRepository){
       this.answerRepository=answerRepository;
       this.questionRepository=questionRepository;
       this.userRepository=userRepository;
   }

   public List<Answer> getAnswerByQuestionId(Long questionId,int page,int size){
      return  answerRepository.findByAnswerId(questionId, PageRequest.of(page,size)).getContent();
   }

   public Optional<Answer> getAnswerById(Long id){
       return answerRepository.findById(id);
   }

   public Answer createAnswer(AnswerDTO answerDTO){
       Answer answer=new Answer();
       answer.setContent(answerDTO.getContent());

       Optional<Question> question=questionRepository.findById(answerDTO.getQuestionId());
       question.ifPresent(answer::setQuestion);
      return answerRepository.save(answer);
   }

   public  void deleteAnswer(Long id){
       answerRepository.deleteById(id);
   }


}
