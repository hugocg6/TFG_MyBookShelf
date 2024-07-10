package com.example.backend.repository;

import com.example.backend.dto.BookPerMonthDTO;
import com.example.backend.model.Book;
import com.example.backend.model.Collection;
import com.example.backend.model.User;
import com.example.backend.model.UserBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserBookRepositoryTest {

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @BeforeEach
    void setUp() {

        Collection collection = new Collection();
        collection.setName("Collection 1");
        collectionRepository.save(collection);

        Book book1 = new Book();
        book1.setCollection(collection);
        book1.setPublishDate(new Date());
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setCollection(collection);
        book2.setPublishDate(new Date());
        bookRepository.save(book2);

        User user = new User();
        user.setName("user1");
        userRepository.save(user);

        UserBook userBook1 = new UserBook();
        userBook1.setBook(book1);
        userBook1.setUser(user);
        userBook1.setRead(true);
        userBook1.setReadingDate(new Date());
        userBookRepository.save(userBook1);

        UserBook userBook2 = new UserBook();
        userBook2.setBook(book2);
        userBook2.setUser(user);
        userBook2.setRead(false);
        userBook2.setReadingDate(new Date());
        userBookRepository.save(userBook2);
    }

    @Test
    void findUserBooksByBooks() {
        User user = new User();
        user.setName("user1");

        Collection collection = new Collection();
        collection.setName("Collection 1");

        Book book1 = new Book();
        book1.setCollection(collection);
        book1.setPublishDate(new Date());

        Book book2 = new Book();
        book2.setCollection(collection);
        book2.setPublishDate(new Date());

        List<UserBook> userBooks = userBookRepository.findUserBooksByBooks(List.of(book1.getId(), book2.getId()), user.getId());
        assertThat(userBooks).hasSize(2);
        assertThat(userBooks.get(0).getBook().getId()).isEqualTo(book1.getId());
        assertThat(userBooks.get(1).getBook().getId()).isEqualTo(book2.getId());
    }

    @Test
    void findByUserIdAndBookId() {
        User user = new User();
        user.setName("user1");

        Collection collection = new Collection();
        collection.setName("Collection 1");

        Book book1 = new Book();
        book1.setCollection(collection);
        book1.setPublishDate(new Date());

        UserBook userBook = userBookRepository.findByUserIdAndBookId(user.getId(), book1.getId());
        assertThat(userBook).isNotNull();
        assertThat(userBook.getBook().getId()).isEqualTo(book1.getId());
    }

    @Test
    void countUnreadBooksInCollection() {
        User user = new User();
        user.setName("user1");

        Collection collection = new Collection();
        collection.setName("Collection 1");

        int count = userBookRepository.countUnreadBooksInCollection(user.getId(), collection.getId());
        assertThat(count).isEqualTo(1);
    }

    @Test
    void countBooksReadPerMonth() {
        User user = new User();
        user.setName("user1");

        List<BookPerMonthDTO> booksReadPerMonth = userBookRepository.countBooksReadPerMonth(user.getId());
        assertThat(booksReadPerMonth).hasSize(1);
        assertThat(booksReadPerMonth.get(0).getMonth()).isEqualTo("2024-06");
        assertThat(booksReadPerMonth.get(0).getCount()).isEqualTo(1);
    }
}