package com.example.QuoraAppCloneApplication.controllers;

import com.example.QuoraAppCloneApplication.models.Answer;
import com.example.QuoraAppCloneApplication.models.Comment;
import com.example.QuoraAppCloneApplication.services.CommentService;
import jakarta.websocket.server.PathParam;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment/{commentId}")
    public List<Comment> getCommentByCommentId(@PathVariable Long commentId, @RequestParam int page, @RequestParam int size) {
        reutrn commentService.getRepliesByCommentId(commentId, page, size);
    }
}
