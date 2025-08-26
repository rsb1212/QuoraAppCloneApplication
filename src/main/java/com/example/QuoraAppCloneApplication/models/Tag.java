package com.example.QuoraAppCloneApplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Tag extends BaseModel{

    private String name;

    @ManyToMany(mappedBy = "followers")

    private Set<User> followers;

}
