package com.example.backend.repository;

import com.example.backend.model.CollectionAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionAuthorRepository  extends JpaRepository<CollectionAuthor, Long> {
}
