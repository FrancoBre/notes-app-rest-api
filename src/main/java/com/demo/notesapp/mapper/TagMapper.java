package com.demo.notesapp.mapper;

import com.demo.notesapp.dto.TagDTO;
import com.demo.notesapp.model.Tag;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TagMapper {

    public static Tag mapToEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        return tag;
    }

    public static TagDTO mapToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        return tagDTO;
    }
}