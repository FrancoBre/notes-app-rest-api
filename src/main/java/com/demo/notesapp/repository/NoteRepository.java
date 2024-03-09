package com.demo.notesapp.repository;

import com.demo.notesapp.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NoteRepository extends CrudRepository<Note, Long> {

}
