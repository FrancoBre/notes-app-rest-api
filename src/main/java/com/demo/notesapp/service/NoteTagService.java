package com.demo.notesapp.service;

import com.demo.notesapp.dto.NoteTagDTO;
import com.demo.notesapp.mapper.NoteTagMapper;
import com.demo.notesapp.model.NoteTag;
import com.demo.notesapp.model.NoteTagId;
import com.demo.notesapp.repository.NoteTagRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteTagService {

    private final NoteTagRepository repository;

    public List<NoteTagDTO> getAll() {
        List<NoteTag> result = (List<NoteTag>) repository.findAll();
        if (!result.isEmpty()) {
            return NoteTagMapper.mapToDTO(result);
        } else {
            return new ArrayList<>();
        }
    }

    public NoteTagDTO getNoteTagById(NoteTagId id) throws EntityNotFoundException {
        Optional<NoteTag> noteTag = repository.findById(id);
        if (noteTag.isPresent()) {
            return NoteTagMapper.mapToDTO(noteTag.get());
        } else {
            throw new EntityNotFoundException("No note-tag record exist for given id");
        }
    }

    public NoteTagDTO createOrUpdate(NoteTagDTO noteTagDTO) {
        NoteTag oldEntity = NoteTagMapper.mapToEntity(noteTagDTO);

        NoteTagId id = new NoteTagId();
        id.setNoteId(noteTagDTO.getNoteId());
        id.setTagId(noteTagDTO.getTagId());

        Optional<NoteTag> noteTag = repository.findById(id);
        if (noteTag.isPresent()) {
            NoteTag newEntity = noteTag.get();
            newEntity.setId(oldEntity.getId());
            newEntity = repository.save(newEntity);
            return NoteTagMapper.mapToDTO(newEntity);
        } else {
            NoteTag newEntity = repository.save(oldEntity);
            return NoteTagMapper.mapToDTO(newEntity);
        }
    }

    public void deleteNoteTagById(NoteTagId id) throws EntityNotFoundException {
        Optional<NoteTag> noteTag = repository.findById(id);
        if (noteTag.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No note-tag record exist for given id");
        }
    }
}