package com.example.backend.repository;

import com.example.backend.model.Book;
import com.example.backend.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {

    @Query("select ub from UserBook ub where ub.book in ( "
            +"select b from Book b where b.id in :bookIds)"
            +" and ub.user.id = :currentUserId "
            +"order by ub.book.id asc ")
    List<UserBook> findUserBooksByBooks(@Param("bookIds") List<Long> bookIds, @Param("currentUserId") Long currentUserId);

    @Query("select ub from UserBook ub where ub.user.id = :userId and ub.book.id = :bookId order by ub.book.id asc ")
    UserBook findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

    @Query("SELECT COUNT(ub) FROM UserBook ub WHERE ub.user.id = :userId AND ub.book.collection.id = :collectionId AND ub.read = false")
    int countUnreadBooksInCollection(@Param("userId") Long userId, @Param("collectionId") Long collectionId);
}
