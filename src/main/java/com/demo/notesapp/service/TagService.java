package com.demo.notesapp.service;

import com.demo.notesapp.dto.TagDTO;
import com.demo.notesapp.mapper.TagMapper;
import com.demo.notesapp.model.Tag;
import com.demo.notesapp.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    public List<TagDTO> getAll() {
        List<Tag> tags = repository.findAll();
        List<TagDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagDTOs.add(TagMapper.mapToDTO(tag));
        }
        return tagDTOs;
    }

    public Optional<TagDTO> getById(int id) {
        Optional<Tag> tag = repository.findById(id);
        if (tag.isPresent()) {
            return Optional.of(TagMapper.mapToDTO(tag.get()));
        } else {
            return Optional.empty();
        }
    }

    public TagDTO createOrUpdate(TagDTO tagDTO) {
        Tag oldEntity = TagMapper.mapToEntity(tagDTO);

        if (oldEntity.getId() == 0) {
            Tag newEntity = repository.save(oldEntity);
            return TagMapper.mapToDTO(newEntity);
        } else {
            Optional<Tag> tag = repository.findById(oldEntity.getId());
            if (tag.isPresent()) {
                Tag newEntity = tag.get();
                newEntity.setName(oldEntity.getName());
                newEntity = repository.save(newEntity);
                return TagMapper.mapToDTO(newEntity);
            } else {
                tag = Optional.of(repository.save(oldEntity));
                return TagMapper.mapToDTO(tag.get());
            }
        }
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}