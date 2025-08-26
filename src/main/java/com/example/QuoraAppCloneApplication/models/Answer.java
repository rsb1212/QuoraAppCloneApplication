package com.example.QuoraAppCloneApplication.models;

import jakarta.persistence.*;
import lombok.Data;

import javax.xml.stream.events.Comment;
import java.util.Set;

@Data
@Entity
public class Answer extends BaseModel{
    private String content;

    @ManyToOne
    @JoinColumn(name ="question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "answer")
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(
            joinColumns =@JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likedBy;

}
