package com.example.backend.repository;

import com.example.backend.dto.BookPerMonthDTO;
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

    @Query("SELECT new com.example.backend.dto.BookPerMonthDTO(TO_CHAR(ub.readingDate, 'YYYY-MM'), COUNT(ub.id)) " +
            "FROM UserBook ub " +
            "WHERE ub.read = true " +
            "AND ub.user.id = :userId " +
            "AND EXTRACT(YEAR FROM ub.readingDate) = EXTRACT(YEAR FROM CURRENT_DATE) " +
            "GROUP BY TO_CHAR(ub.readingDate, 'YYYY-MM'), EXTRACT(MONTH FROM ub.readingDate) " +
            "ORDER BY EXTRACT(MONTH FROM ub.readingDate)")
    List<BookPerMonthDTO> countBooksReadPerMonth(@Param("userId") Long userId);
}
