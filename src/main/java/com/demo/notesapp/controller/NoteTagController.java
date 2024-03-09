package com.demo.notesapp.controller;

import com.demo.notesapp.dto.NoteTagDTO;
import com.demo.notesapp.model.NoteTagId;
import com.demo.notesapp.service.NoteTagService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
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
@RequestMapping("/note-tag")
@RequiredArgsConstructor
public class NoteTagController {

    private final NoteTagService noteTagService;

    @GetMapping
    public ResponseEntity<List<NoteTagDTO>> getAllNoteTags() {
        List<NoteTagDTO> noteTags = noteTagService.getAll();
        return ResponseEntity.ok(noteTags);
    }

    @PostMapping
    public ResponseEntity<NoteTagDTO> createNoteTag(@RequestBody NoteTagDTO noteTag) {
        NoteTagDTO createdNoteTag = noteTagService.createOrUpdate(noteTag);
        return ResponseEntity.ok(createdNoteTag);
    }

    @PutMapping("/{noteId}/{tagId}")
    public ResponseEntity<NoteTagDTO> updateNoteTag(@RequestBody NoteTagDTO noteTag,
        @PathVariable("noteId") Long noteId, @PathVariable("tagId") Long tagId) throws EntityNotFoundException {
        NoteTagId id = new NoteTagId();
        id.setNoteId(noteId);
        id.setTagId(tagId);

        noteTag.setNoteId(noteId);
        noteTag.setTagId(tagId);
        NoteTagDTO updatedNoteTag = noteTagService.createOrUpdate(noteTag);
        return ResponseEntity.ok(updatedNoteTag);
    }

    @DeleteMapping("/{noteId}/{tagId}")
    public ResponseEntity<Void> deleteNoteTag(@PathVariable("noteId") Long noteId, @PathVariable("tagId") Long tagId)
        throws EntityNotFoundException {
        NoteTagId id = new NoteTagId();
        id.setNoteId(noteId);
        id.setTagId(tagId);

        noteTagService.deleteNoteTagById(id);
        return ResponseEntity.noContent().build();
    }
}