package com.example.backend.controller;

import com.example.backend.model.UserBook;
import com.example.backend.model.UserCollection;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;
    private MockHttpSession session;
    private UserBook userBook;
    private UserCollection userCollection;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        session = new MockHttpSession();
        session.setAttribute("user", 1L);

        userBook = new UserBook();
        userBook.setCollectionId(1L);

        userCollection = new UserCollection();
    }

    @Test
    void markRead() throws Exception {
        when(userService.findByUserIdAndBookId(anyLong(), anyLong())).thenReturn(userBook);
        when(userService.countUnreadBooksInCollection(anyLong(), anyLong())).thenReturn(0);
        when(userService.findByUserIdAndCollectionId(anyLong(), anyLong())).thenReturn(userCollection);

        mockMvc.perform(post("/book/1/markRead").session(session))
                .andExpect(redirectedUrl("/collection/1"));

        verify(userService, times(1)).findByUserIdAndBookId(1L, 1L);
        verify(userService, times(1)).save(any(UserBook.class));
        verify(userService, times(1)).countUnreadBooksInCollection(1L, 1L);
        verify(userService, times(1)).findByUserIdAndCollectionId(1L, 1L);
        verify(userService, times(1)).save(any(UserCollection.class));
    }

    @Test
    void unmarkRead() throws Exception {
        when(userService.findByUserIdAndBookId(anyLong(), anyLong())).thenReturn(userBook);
        when(userService.countUnreadBooksInCollection(anyLong(), anyLong())).thenReturn(1);
        when(userService.findByUserIdAndCollectionId(anyLong(), anyLong())).thenReturn(userCollection);

        mockMvc.perform(post("/book/1/unmarkRead").session(session))
                .andExpect(redirectedUrl("/collection/1"));

        verify(userService, times(1)).findByUserIdAndBookId(1L, 1L);
        verify(userService, times(1)).save(any(UserBook.class));
        verify(userService, times(1)).countUnreadBooksInCollection(1L, 1L);
        verify(userService, times(1)).findByUserIdAndCollectionId(1L, 1L);
        verify(userService, times(1)).save(any(UserCollection.class));
    }
}