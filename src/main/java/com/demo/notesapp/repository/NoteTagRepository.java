package com.demo.notesapp.repository;

import com.demo.notesapp.model.NoteTag;
import com.demo.notesapp.model.NoteTagId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NoteTagRepository extends CrudRepository<NoteTag, NoteTagId> {

}
