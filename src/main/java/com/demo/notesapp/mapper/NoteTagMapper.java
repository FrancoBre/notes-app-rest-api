package com.demo.notesapp.mapper;

import com.demo.notesapp.dto.NoteTagDTO;
import com.demo.notesapp.model.NoteTag;
import com.demo.notesapp.model.NoteTagId;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NoteTagMapper {

    public static List<NoteTagDTO> mapToDTO(List<NoteTag> result) {
        List<NoteTagDTO> dtoList = new ArrayList<>();
        for (NoteTag noteTag : result) {
            dtoList.add(NoteTagMapper.mapToDTO(noteTag));
        }
        return dtoList;
    }

    public static NoteTagDTO mapToDTO(NoteTag noteTag) {
        NoteTagDTO dto = new NoteTagDTO();
        dto.setNoteId(noteTag.getId().getNoteId());
        dto.setTagId(noteTag.getId().getTagId());
        return dto;
    }

    public static NoteTag mapToEntity(NoteTagDTO dto) {
        NoteTag noteTag = new NoteTag();
        NoteTagId id = new NoteTagId();
        id.setNoteId(dto.getNoteId());
        id.setTagId(dto.getTagId());
        noteTag.setId(id);
        return noteTag;
    }


}
