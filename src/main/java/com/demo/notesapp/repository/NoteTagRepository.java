package com.demo.notesapp.repository;

import com.demo.notesapp.model.NoteTag;
import com.demo.notesapp.model.NoteTagId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteTagRepository extends CrudRepository<NoteTag, NoteTagId> {

}
