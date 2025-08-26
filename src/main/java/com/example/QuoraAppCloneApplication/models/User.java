package com.example.QuoraAppCloneApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity

public class User extends BaseModel{

    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_tag",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )

    private Set<Tag> followedTags;
}
