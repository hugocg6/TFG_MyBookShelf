package com.example.backend.service;

import com.example.backend.dto.CollectionDTO;
import com.example.backend.model.Author;
import com.example.backend.model.Collection;
import com.example.backend.model.CollectionAuthor;
import com.example.backend.model.Publisher;
import com.example.backend.repository.CollectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CollectionServiceTest {

    @Mock
    private CollectionRepository collectionRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private CollectionService collectionService;

    private Collection collection;
    private Author author;
    private Publisher publisher;

    @BeforeEach
    public void setUp() {

        author = new Author();
        author.setName("Author 1");

        publisher = new Publisher();
        publisher.setName("Publisher 1");

        collection = new Collection();
        collection.setId(1L);
        collection.setName("Collection 1");
        collection.setAuthor(List.of(new CollectionAuthor(collection, author)));
        collection.setPublisher(publisher);
        collection.setBooks(Collections.emptyList());
    }

    @Test
    void findSimilarCollection() {
        when(collectionRepository.findCollectionsWithSameAuthor(anyLong())).thenReturn(Collections.singletonList(collection));
        when(collectionRepository.findCollectionsWithSameDemography(anyLong())).thenReturn(Collections.singletonList(collection));

        List<Collection> result = collectionService.findSimilarCollection(1L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Collection 1");
    }

    @Test
    void findCollectionById() {
        when(collectionRepository.findById(anyLong())).thenReturn(Optional.of(collection));
        when(authorService.getAuthorsNames(Collections.singletonList(new CollectionAuthor(collection, author)))).thenReturn(List.of("Author 1"));

        CollectionDTO result = collectionService.findCollectionById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Collection 1");
        assertThat(result.getAuthor()).isEqualTo(List.of("Author 1"));
        assertThat(result.getPublisher()).isEqualTo("Publisher 1");
    }
}