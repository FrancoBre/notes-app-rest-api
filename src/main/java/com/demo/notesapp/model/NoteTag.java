package com.demo.notesapp.model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "note_tag")
public class NoteTag {

    @EmbeddedId
    private NoteTagId id;

    @MapsId("noteId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Note note;
}