package com.example.QuoraAppCloneApplication.controllers;

import com.example.QuoraAppCloneApplication.dtos.TagDTO;
import com.example.QuoraAppCloneApplication.models.Tag;
import com.example.QuoraAppCloneApplication.services.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    private TagService tagService;

    public TagController(TagService tagService){
        this.tagService=tagService;
    }

    @GetMapping
    public List<Tag> getAllTags(){
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id){
        Optional<Tag> tag=tagService.getTagById(id);
        return tag.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public  Tag createTag(@RequestBody TagDTO tagDTO){
        return tagService.createTag(tagDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
