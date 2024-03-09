package com.demo.notesapp.controller;

import com.demo.notesapp.dto.TagDTO;
import com.demo.notesapp.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<TagDTO> tags = tagService.getAll();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable int id) {
        TagDTO tag = tagService.getById(id).get();
        return ResponseEntity.ok(tag);
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tag) {
        TagDTO createdTag = tagService.createOrUpdate(tag);
        return ResponseEntity.ok(createdTag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> updateTag(@RequestBody TagDTO tag, @PathVariable int id) {
        tag.setId(id);
        TagDTO updatedTag = tagService.createOrUpdate(tag);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable int id) {
        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}