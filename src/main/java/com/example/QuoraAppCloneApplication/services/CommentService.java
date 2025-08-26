package com.example.QuoraAppCloneApplication.services;

import com.example.QuoraAppCloneApplication.dtos.CommentDTO;
import com.example.QuoraAppCloneApplication.models.Answer;
import com.example.QuoraAppCloneApplication.models.Comment;
import com.example.QuoraAppCloneApplication.repositories.AnswerRepository;
import com.example.QuoraAppCloneApplication.repositories.CommentRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private AnswerRepository answerRepository;

    public CommentService(CommentRepository commentRepository,AnswerRepository answerRepository){
        this.commentRepository=commentRepository;
        this.answerRepository=answerRepository;
    }

    public List<Comment> getCommentByAnswerId(Long answerId,int page,int size){
        return commentRepository.findByAnswerId(answerId, PageRequest.of(page,size)).getContent();
    }

    public List<Comment> getRepliesByCommentId(Long commentId,int page,int size){
       return commentRepository.findByParentCommentId(commentId,PageRequest.of(page,size)).getContent();
    }

    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }

    public Comment createComment(CommentDTO commentDTO){
        Comment comment=new Comment();
        comment.setContent(commentDTO.getContent());
        Optional<Answer> answer=answerRepository.findById(commentDTO.getAnswerId());
        answer.ifPresent(comment::setAnswer);

        if(commentDTO.getPrentCommentId()!=null){
            Optional<Comment> parentComment=commentRepository.findById((commentDTO.getPrentCommentId()));
            parentComment.ifPresent(comment::setParentComment);
        }
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id){
        commentRepository.findById(id);
    }
}
