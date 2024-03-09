package com.demo.notesapp.controller;

import com.demo.notesapp.dto.NoteDTO;
import com.demo.notesapp.service.NoteService;
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
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        List<NoteDTO> notes = noteService.getAll();
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO note) {
        NoteDTO createdNote = noteService.createOrUpdate(note);
        return ResponseEntity.ok(createdNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDTO> updateNote(@RequestBody NoteDTO note, @PathVariable("id") Long id)
        throws EntityNotFoundException {
        note.setId(id);
        NoteDTO updatedNote = noteService.createOrUpdate(note);
        return ResponseEntity.ok(updatedNote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable("id") Long id) throws EntityNotFoundException {
        NoteDTO note = noteService.getNoteById(id);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) throws EntityNotFoundException {
        return ResponseEntity.noContent().build();
//        noteService.deleteNoteById(id);
//        return ResponseEntity.noContent().build();
    }
}
