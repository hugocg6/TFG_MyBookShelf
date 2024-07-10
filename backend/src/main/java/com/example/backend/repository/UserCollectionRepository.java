package com.example.backend.repository;

import com.example.backend.model.Author;
import com.example.backend.model.Collection;
import com.example.backend.model.Demography;
import com.example.backend.model.UserCollection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCollectionRepository extends JpaRepository<UserCollection, Long> {

    @Query("SELECT COUNT(uc) > 0 " +
            "FROM UserCollection uc " +
            "WHERE uc.user.id = :userId " +
            "AND uc.collection.id = :collectionId")
    boolean isCollectionAdded(@Param("userId") Long userId, @Param("collectionId") Long collectionId);

    @Modifying
    @Query("DELETE FROM UserCollection uc " +
            "WHERE uc.user.id = :userId " +
            "AND uc.collection.id = :collectionId")
    void deleteCollectionFromUser(@Param("userId") Long userId, @Param("collectionId") Long collectionId);

    @Query("SELECT c FROM Collection c " +
            "JOIN c.books b " +
            "JOIN UserBook ub ON ub.book.id = b.id " +
            "WHERE ub.user.id = :userId AND ub.read = true " +
            "GROUP BY c.id " +
            "HAVING COUNT(ub.id) > 0 AND COUNT(ub.id) < (SELECT COUNT(b2.id) FROM Book b2 WHERE b2.collection.id = c.id)")
    List<Collection> findPartiallyReadCollections(@Param("userId") Long userId);

    @Query("SELECT uc FROM UserCollection uc WHERE uc.user.id = :userId AND uc.collection.id = :collectionId")
    UserCollection findByUserIdAndCollectionId(@Param("userId") Long userId, @Param("collectionId") Long collectionId);

    @Query("SELECT c from Collection c " +
            "join UserCollection uc on uc.collection.id = c.id " +
            "where uc.read = true " +
            "and uc.user.id = :userId " +
            "order by uc.readDate desc ")
    List<Collection> findUserReadCollections(@Param("userId") Long userId);

    @Query("SELECT uc from UserCollection uc where uc.user.id = :userId and uc.collection.id = :collectionId")
    UserCollection findByUserIdCollection(@Param("userId") Long userId, @Param("collectionId") Long collectionId);

    @Query("SELECT uc.collection " +
                  "FROM UserCollection uc " +
                  "WHERE uc.user.id = :userId AND uc.read = TRUE " +
                  "ORDER BY uc.readDate DESC")
    List<Collection> findLastReadCollectionByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT a FROM UserCollection uc " +
            "JOIN Collection c on uc.collection.id = c.id " +
            "JOIN CollectionAuthor ca on ca.collection = c " +
            "JOIN Author a on ca.author = a " +
            "WHERE uc.user.id = :userId " +
            "GROUP BY a.id, ca.author.name " +
            "ORDER BY COUNT(a.id) DESC")
    List<Author> findMostCommonAuthorByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT d " +
            "FROM UserCollection uc JOIN uc.collection c " +
            "JOIN c.demography d " +
            "WHERE uc.user.id = :userId " +
            "GROUP BY d.id, d.name " +
            "ORDER BY COUNT(d.id) DESC")
    List<Demography> findMostCommonDemographyByUserId(@Param("userId") Long userId, Pageable pageable);
}
