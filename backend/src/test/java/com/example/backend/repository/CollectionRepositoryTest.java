package com.example.backend.repository;

import com.example.backend.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CollectionRepositoryTest {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private DemographyRepository demographyRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private UserCollectionRepository userCollectionRepository;

    @BeforeEach
    void setUp() {

        Author author1 = new Author();
        author1.setName("Author 1");
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("Author 2");
        authorRepository.save(author2);

        Demography demography1 = new Demography();
        demography1.setName("Demography 1");
        demographyRepository.save(demography1);

        Demography demography2 = new Demography();
        demography2.setName("Demography 2");
        demographyRepository.save(demography2);

        Publisher publisher1 = new Publisher();
        publisher1.setName("Publisher 1");
        publisherRepository.save(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Publisher 2");
        publisherRepository.save(publisher2);

        Collection collection1 = new Collection();
        collection1.setName("Collection 1");
        collection1.setDemography(demography1);
        collection1.setPublisher(publisher1);
        collectionRepository.save(collection1);

        Collection collection2 = new Collection();
        collection2.setName("Collection 2");
        collection2.setDemography(demography2);
        collection2.setPublisher(publisher2);
        collectionRepository.save(collection2);

        Book book1 = new Book();
        book1.setCollection(collection1);
        book1.setPublishDate(new Date());
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setCollection(collection2);
        book2.setPublishDate(new Date());
        bookRepository.save(book2);

        UserCollection userCollection1 = new UserCollection();
        userCollection1.setCollection(collection1);
        userCollectionRepository.save(userCollection1);

        UserCollection userCollection2 = new UserCollection();
        userCollection2.setCollection(collection2);
        userCollectionRepository.save(userCollection2);
    }

    @Test
    void findAllByOrderByNameAsc() {
        List<Collection> collections = collectionRepository.findAllByOrderByNameAsc();
        assertThat(collections).hasSize(2);
        assertThat(collections.get(0).getName()).isEqualTo("Collection 1");
        assertThat(collections.get(1).getName()).isEqualTo("Collection 2");
    }

    @Test
    void findLastAddedCollection() {
        List<Collection> collections = collectionRepository.findLastAddedCollection();
        assertThat(collections).hasSize(2);
        assertThat(collections.get(0).getName()).isEqualTo("Collection 2");
        assertThat(collections.get(1).getName()).isEqualTo("Collection 1");
    }

    @Test
    void findCollectionByAuthorName() {
        List<Collection> collections = collectionRepository.findCollectionByAuthorName("Author 1");
        assertThat(collections).isEmpty();
    }

    @Test
    void findCollectionByDemography() {
        List<Collection> collections = collectionRepository.findCollectionByDemography("Demography 1");
        assertThat(collections).hasSize(1);
        assertThat(collections.get(0).getName()).isEqualTo("Collection 1");
    }

    @Test
    void findCollectionsByIdIs() {

        Demography demography1 = new Demography();
        demography1.setName("Demography 1");

        Publisher publisher1 = new Publisher();
        publisher1.setName("Publisher 1");

        Collection collection1 = new Collection();
        collection1.setName("Collection 1");
        collection1.setDemography(demography1);
        collection1.setPublisher(publisher1);

        Collection collection2 = new Collection();
        collection2.setName("Collection 2");
        collection2.setDemography(demography1);
        collection2.setPublisher(publisher1);

        List<Collection> collections = collectionRepository.findCollectionsByIdIs(List.of(collection1.getId(), collection2.getId()));
        assertThat(collections).hasSize(2);
    }

    @Test
    void searchCollections() {
        List<Collection> collections = collectionRepository.searchCollections("Collection");
        assertThat(collections).hasSize(2);
    }

    @Test
    void findTopAddedCollections() {
        List<Collection> collections = collectionRepository.findTopAddedCollections(PageRequest.of(0, 1));
        assertThat(collections).hasSize(1);
    }
}