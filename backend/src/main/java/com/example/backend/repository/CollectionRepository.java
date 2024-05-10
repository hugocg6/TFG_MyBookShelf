package com.example.backend.repository;

import com.example.backend.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<Collection> findAllByOrderByNameAsc();

    @Query("select c from Collection c " +
            "join Book b on b.collection = c " +
            "where b.publishDate = (" +
            "select min(b2.publishDate) from Book b2 where b2.collection = c) " +
            "order by b.publishDate desc " +
            "limit 6")
    List<Collection> findLastAddedCollection();

    @Query("SELECT c FROM Collection c " +
            "JOIN CollectionAuthor ca ON ca.collection = c " +
            "JOIN Author a ON ca.author = a WHERE a.name = :authorName " +
            "order by c.name asc ")
    List<Collection> findCollectionByAuthorName(@Param("authorName") String authorName);

    @Query("SELECT c FROM Collection c JOIN Demography d ON c.demography = d WHERE d.name = :demography order by c.name asc ")
    List<Collection> findCollectionByDemography(@Param("demography") String demography);

    @Query("SELECT DISTINCT c " +
            "FROM Collection c " +
            "JOIN c.author a " +
            "WHERE a.author IN (" +
            "    SELECT a2 FROM Collection cl JOIN cl.author a2 WHERE cl.id = :collectionId" +
            ") " +
            "AND c.id <> :collectionId")
    List<Collection> findCollectionsWithSameAuthor(@Param("collectionId") Long collectionId);

    @Query("SELECT DISTINCT c " +
            "FROM Collection c " +
            "WHERE c.demography.id = (" +
            "    SELECT cl.demography.id FROM Collection cl WHERE cl.id = :collectionId" +
            ") " +
            "AND c.id <> :collectionId")
    List<Collection> findCollectionsWithSameDemography(@Param("collectionId") Long collectionId);

    @Query("SELECT c from Collection c where c.id in :collectionIds order by c.name asc ")
    List<Collection> findCollectionsByIdIs(@Param("collectionIds") List<Long> collectionIds);
}
