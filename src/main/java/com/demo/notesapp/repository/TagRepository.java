package com.demo.notesapp.repository;

import com.demo.notesapp.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TagRepository extends JpaRepository<Tag, Integer> {

}