package com.example.backend.repository;

import com.example.backend.model.Collection;
import com.example.backend.model.UserCollection;
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
}
