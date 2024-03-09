package com.demo.notesapp.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class NoteTagId implements Serializable {

    private Long noteId;
    private Long tagId;

}