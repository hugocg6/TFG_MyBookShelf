package com.example.backend.repository;

import com.example.backend.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserCollectionRepositoryTest {

    @Autowired
    private UserCollectionRepository userCollectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private DemographyRepository demographyRepository;

    @Autowired
    private UserBookRepository userBookRepository;

    private User user;
    private Collection collection;
    private Author author;
    private Demography demography;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setName("user1");
        userRepository.save(user);

        demography = new Demography();
        demography.setName("Demography 1");
        demographyRepository.save(demography);

        author = new Author();
        author.setName("Author 1");
        authorRepository.save(author);

        collection = new Collection();
        collection.setName("Collection 1");
        collection.setDemography(demography);
        collectionRepository.save(collection);

        Book book = new Book();
        book.setCollection(collection);
        bookRepository.save(book);

        UserCollection userCollection = new UserCollection();
        userCollection.setUser(user);
        userCollection.setCollection(collection);
        userCollectionRepository.save(userCollection);

        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBook(book);
        userBook.setRead(true);
        userBookRepository.save(userBook);
    }

    @Test
    void isCollectionAdded() {
        boolean isAdded = userCollectionRepository.isCollectionAdded(user.getId(), collection.getId());
        assertThat(isAdded).isTrue();
    }

    @Test
    void deleteCollectionFromUser() {
        userCollectionRepository.deleteCollectionFromUser(user.getId(), collection.getId());
        boolean isAdded = userCollectionRepository.isCollectionAdded(user.getId(), collection.getId());
        assertThat(isAdded).isFalse();
    }

    @Test
    void findPartiallyReadCollections() {
        List<Collection> collections = userCollectionRepository.findPartiallyReadCollections(user.getId());
        assertThat(collections).isNotEmpty();
    }

    @Test
    void findByUserIdAndCollectionId() {
        UserCollection userCollection = userCollectionRepository.findByUserIdAndCollectionId(user.getId(), collection.getId());
        assertThat(userCollection).isNotNull();
    }

    @Test
    void findUserReadCollections() {
        List<Collection> collections = userCollectionRepository.findUserReadCollections(user.getId());
        assertThat(collections).isNotEmpty();
    }

    @Test
    void findByUserIdCollection() {
        UserCollection userCollection = userCollectionRepository.findByUserIdCollection(user.getId(), collection.getId());
        assertThat(userCollection).isNotNull();
    }

    @Test
    void findLastReadCollectionByUserId() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Collection> collections = userCollectionRepository.findLastReadCollectionByUserId(user.getId(), pageable);
        assertThat(collections).isNotEmpty();
    }

    @Test
    void findMostCommonAuthorByUserId() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Author> authors = userCollectionRepository.findMostCommonAuthorByUserId(user.getId(), pageable);
        assertThat(authors).isNotEmpty();
    }

    @Test
    void findMostCommonDemographyByUserId() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Demography> demographyList = userCollectionRepository.findMostCommonDemographyByUserId(user.getId(), pageable);
        assertThat(demographyList).isNotEmpty();
    }
}