package com.example.backend.repository;

import com.example.backend.model.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

}
