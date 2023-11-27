package com.example.backend.repository;

import com.example.backend.model.CollectionAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionAuthorRepository  extends JpaRepository<CollectionAuthor, Long> {
}
