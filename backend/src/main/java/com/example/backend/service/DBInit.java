package com.example.backend.service;

import com.example.backend.model.*;
import com.example.backend.repository.*;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DBInit {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CollectionRepository collectionRepository;
    private final PublisherRepository publisherRepository;
    private final CollectionAuthorRepository collectionAuthorRepository;

    @Autowired
    public DBInit(AuthorRepository authorRepository, BookRepository bookRepository,
                  CollectionRepository collectionRepository, PublisherRepository publisherRepository,
                  CollectionAuthorRepository collectionAuthorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.collectionRepository = collectionRepository;
        this.publisherRepository = publisherRepository;
        this.collectionAuthorRepository = collectionAuthorRepository;
    }


    @PostConstruct
    public void init() throws IOException {

        //Collection 1

        List<Book> bookList1 = new ArrayList<>();

        Book book11 = new Book(new Date(1537394400000L), false, 224, 1);
        bookList1.add(book11);
        Book book12 = new Book(new Date(1537394400000L), false, 224, 2);
        bookList1.add(book12);
        Book book13 = new Book(new Date(1537394400000L), false, 208, 3);
        bookList1.add(book13);
        Book book14 = new Book(new Date(1537394400000L), false, 208, 4);
        bookList1.add(book14);
        Book book15 = new Book(new Date(1537394400000L), false, 208, 5);
        bookList1.add(book15);
        Book book16 = new Book(new Date(1537394400000L), false, 208, 6);
        bookList1.add(book16);
        Book book17 = new Book(new Date(1537394400000L), false, 208, 7);
        bookList1.add(book17);
        Book book18 = new Book(new Date(1537394400000L), false, 208, 8);
        bookList1.add(book18);

        bookRepository.saveAll(bookList1);

        Publisher publisher1 = new Publisher("Ivrea");

        publisherRepository.save(publisher1);

        Collection collection1 = new Collection();
        Author author1 = new Author();

        List<CollectionAuthor> collectionAuthorList1 = new ArrayList<>();
        CollectionAuthor collectionAuthor1 = new CollectionAuthor(collection1, author1);
        collectionAuthorList1.add(collectionAuthor1);

        author1.setName("KAITO");
        author1.setCollections(collectionAuthorList1);

        collection1.setName("Ao no Flag");
        collection1.setBooks(bookList1);
        collection1.setAuthor(collectionAuthorList1);
        collection1.setPublisher(publisher1);
        collection1.setPlot("Mucho lloro");
        setCollectionImage(collection1, "covers/ao_no_flag.jpg");

        authorRepository.save(author1);
        collectionRepository.save(collection1);
        collectionAuthorRepository.save(collectionAuthor1);
    }

    private void setCollectionImage(Collection collection, String classPathResource) throws IOException {
        ClassPathResource image = new ClassPathResource(classPathResource);
        collection.setBookImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
