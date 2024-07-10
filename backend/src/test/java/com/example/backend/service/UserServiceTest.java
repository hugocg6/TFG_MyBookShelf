package com.example.backend.service;

import com.example.backend.dto.BookDTO;
import com.example.backend.dto.BookPerMonthDTO;
import com.example.backend.model.Author;
import com.example.backend.model.Collection;
import com.example.backend.model.Demography;
import com.example.backend.model.UserBook;
import com.example.backend.repository.UserBookRepository;
import com.example.backend.repository.UserCollectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserBookRepository userBookRepository;

    @Mock
    private UserCollectionRepository userCollectionRepository;

    @InjectMocks
    private UserService userService;

    private BookDTO bookDTO1;
    private BookDTO bookDTO2;
    private UserBook userBook;
    private Collection collection;
    private Author author;
    private Demography demography;

    @BeforeEach
    public void setUp() {

        bookDTO1 = new BookDTO();
        bookDTO1.setId(1L);

        bookDTO2 = new BookDTO();
        bookDTO2.setId(2L);

        userBook = new UserBook();

        collection = new Collection();
        collection.setId(1L);
        collection.setName("Collection 1");

        author = new Author();
        author.setName("Author 1");

        demography = new Demography();
        demography.setName("Demography 1");
    }

    @Test
    void findUserBooksByBooks() {
        List<Long> bookIds = Arrays.asList(1L, 2L);
        when(userBookRepository.findUserBooksByBooks(bookIds, 1L)).thenReturn(Collections.singletonList(userBook));

        List<UserBook> result = userService.findUserBooksByBooks(Arrays.asList(bookDTO1, bookDTO2), 1L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(userBook);
    }

    @Test
    void getLastReadCollectionByUserId() {
        when(userCollectionRepository.findLastReadCollectionByUserId(anyLong(), PageRequest.of(0, 1)))
                .thenReturn(Collections.singletonList(collection));

        Collection result = userService.getLastReadCollectionByUserId(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Collection 1");
    }

    @Test
    void getMostCommonAuthorByUserId() {
        when(userCollectionRepository.findMostCommonAuthorByUserId(anyLong(), PageRequest.of(0, 1)))
                .thenReturn(Collections.singletonList(author));

        Author result = userService.getMostCommonAuthorByUserId(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Author 1");
    }

    @Test
    void getMostCommonDemographyByUserId() {
        when(userCollectionRepository.findMostCommonDemographyByUserId(anyLong(), PageRequest.of(0, 1)))
                .thenReturn(Collections.singletonList(demography));

        Demography result = userService.getMostCommonDemographyByUserId(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Demography 1");
    }

    @Test
    void getBooksReadPerMonth() {
        BookPerMonthDTO bookPerMonthDTO = new BookPerMonthDTO("2024-06", 5L);
        when(userBookRepository.countBooksReadPerMonth(anyLong()))
                .thenReturn(Collections.singletonList(bookPerMonthDTO));

        List<BookPerMonthDTO> result = userService.getBooksReadPerMonth(1L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMonth()).isEqualTo("2024-06");
        assertThat(result.get(0).getCount()).isEqualTo(5L);
    }
}