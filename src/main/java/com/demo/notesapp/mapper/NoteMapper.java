package com.demo.notesapp.mapper;

import com.demo.notesapp.dto.NoteDTO;
import com.demo.notesapp.model.Note;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NoteMapper {

    public static Note mapToEntity(NoteDTO noteDTO) {
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.getUser().setId(noteDTO.getUserId());
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setArchived(noteDTO.getArchived());
        return note;
    }

    public static NoteDTO mapToDTO(Note note) {
        com.demo.notesapp.dto.NoteDTO noteDTO = new com.demo.notesapp.dto.NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setUserId(note.getUser().getId());
        noteDTO.setTitle(note.getTitle());
        noteDTO.setContent(note.getContent());
        noteDTO.setArchived(note.getArchived());
        noteDTO.setCreatedAt(String.valueOf(note.getCreatedAt()));
        noteDTO.setUpdatedAt(String.valueOf(note.getUpdatedAt()));
        return noteDTO;
    }

    public static List<NoteDTO> mapToDTO(List<Note> notes) {
        return notes.stream().map(NoteMapper::mapToDTO).toList();
    }
}
