package com.example.QuoraAppCloneApplication.services;

import com.example.QuoraAppCloneApplication.dtos.QuestionDTO;
import com.example.QuoraAppCloneApplication.models.Question;
import com.example.QuoraAppCloneApplication.models.Tag;
import com.example.QuoraAppCloneApplication.models.User;
import com.example.QuoraAppCloneApplication.repositories.QuestionRepository;
import com.example.QuoraAppCloneApplication.repositories.TagRepository;
import com.example.QuoraAppCloneApplication.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;
    public QuestionService (QuestionRepository questionRepository,UserRepository userRepository,TagRepository tagRepository){
        this.questionRepository=questionRepository;
        this.userRepository=userRepository;
        this.tagRepository=tagRepository;
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }
                                        //page int size
    public List<Question> getQuestions(int offset,int limit){
        return questionRepository.findAll(PageRequest.of(offset,limit)).getContent();
    }

    public Optional<Question> getQuestionById(Long id){
       return questionRepository.findById(id);
    }

    public Question createQuestion(QuestionDTO questionDTO){
        Question question=new Question();
        question.setTitel(questionDTO.getTitle());
        question.setContent(questionDTO.getContent());

        Optional<User> user=userRepository.findById((questionDTO.getUserId()));
        user.ifPresent(question::setUser);
        Set<Tag> tags=questionDTO.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        return questionRepository.save(question);
    }



}
