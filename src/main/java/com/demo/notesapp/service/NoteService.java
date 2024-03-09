package com.demo.notesapp.service;

import com.demo.notesapp.dto.NoteDTO;
import com.demo.notesapp.mapper.NoteMapper;
import com.demo.notesapp.model.Note;
import com.demo.notesapp.repository.NoteRepository;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public List<NoteDTO> getAll() {
        List<Note> result = (List<Note>) repository.findAll();
        if (!result.isEmpty()) {
            return NoteMapper.mapToDTO(result);
        } else {
            return new ArrayList<>();
        }
    }

    public NoteDTO getNoteById(Long id) throws EntityNotFoundException {
        Optional<Note> note = repository.findById(id);
        if (note.isPresent()) {
            return NoteMapper.mapToDTO(note.get());
        } else {
            throw new EntityNotFoundException("No note record exist for given id");
        }
    }

    public NoteDTO createOrUpdate(NoteDTO noteDTO) {
        Note oldEntity = NoteMapper.mapToEntity(noteDTO);

        if (oldEntity.getId() == null) {
            oldEntity.setCreatedAt(Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));
            oldEntity.setUpdatedAt(Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));

            Note newEntity = repository.save(oldEntity);
            return NoteMapper.mapToDTO(newEntity);
        } else {
            Optional<Note> note = repository.findById(oldEntity.getId());
            if (note.isPresent()) {
                Note newEntity = note.get();
                newEntity.setTitle(oldEntity.getTitle());
                newEntity.setContent(oldEntity.getContent());
                newEntity.setArchived(oldEntity.getArchived());
                newEntity.setUpdatedAt(Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));
                newEntity = repository.save(newEntity);
                return NoteMapper.mapToDTO(newEntity);
            } else {
                note = Optional.of(repository.save(oldEntity));
                return NoteMapper.mapToDTO(note.get());
            }
        }
    }

    public void deleteNoteById(Long id) throws EntityNotFoundException {
        Optional<Note> note = repository.findById(id);
        if (note.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No note record exist for given id");
        }
    }
}