package com.example.backend.repository;

import com.example.backend.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("select c from Collection c " +
            "join Book b on b.collection = c " +
            "where b.publishDate = (" +
            "select min(b2.publishDate) from Book b2 where b2.collection = c) " +
            "order by b.publishDate desc " +
            "limit 8")
    List<Collection> findLastAddedCollection();
}
