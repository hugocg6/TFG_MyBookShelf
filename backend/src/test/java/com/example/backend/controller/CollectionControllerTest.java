package com.example.backend.controller;

import com.example.backend.dto.CollectionDTO;
import com.example.backend.model.Collection;
import com.example.backend.model.User;
import com.example.backend.model.UserBook;
import com.example.backend.model.UserCollection;
import com.example.backend.repository.UserCollectionRepository;
import com.example.backend.service.BookService;
import com.example.backend.service.CollectionService;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class CollectionControllerTest {

    @Mock
    private CollectionService collectionService;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @Mock
    private UserCollectionRepository userCollectionRepository;

    @InjectMocks
    private CollectionController collectionController;

    private MockMvc mockMvc;
    private MockHttpSession session;
    private CollectionDTO collectionDTO;
    private UserCollection userCollection;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(collectionController).build();
        session = new MockHttpSession();
        session.setAttribute("user", 1L);

        collectionDTO = new CollectionDTO();
        collectionDTO.setId(1L);

        userCollection = new UserCollection();
        userCollection.setAdditionDate(new Date());
    }

    @Test
    void getCollection() throws Exception {
        when(collectionService.findCollectionById(anyLong())).thenReturn(collectionDTO);
        when(userService.isCollectionAdded(anyLong(), anyLong())).thenReturn(true);
        when(userService.findUserBooksByBooks(anyList(), anyLong())).thenReturn(Collections.emptyList());
        when(userService.findUserCollectionByUserAndCollection(anyLong(), anyLong())).thenReturn(userCollection);

        mockMvc.perform(get("/collection/1").session(session))
                .andExpect(view().name("collection-example"))
                .andExpect(model().attributeExists("collection"))
                .andExpect(model().attributeExists("similarCollection"));

        verify(collectionService, times(1)).findCollectionById(1L);
        verify(userService, times(1)).isCollectionAdded(1L, 1L);
        verify(userService, times(1)).findUserBooksByBooks(anyList(), eq(1L));
        verify(userService, times(1)).findUserCollectionByUserAndCollection(1L, 1L);
    }

    @Test
    void downloadImage() throws Exception {
        Collection collection = new Collection();
        Blob mockBlob = mock(Blob.class);
        when(mockBlob.getBinaryStream()).thenReturn(new ByteArrayInputStream("test".getBytes()));
        collection.setBookImage(mockBlob);

        when(collectionService.findById(anyLong())).thenReturn(Optional.of(collection));

        mockMvc.perform(get("/collection/1/image"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE));

        verify(collectionService, times(1)).findById(1L);
    }

    @Test
    void addCollection() throws Exception {
        Collection collection = new Collection();
        User user = new User();
        user.setId(1L);
        user.setCollections(Collections.singletonList(new UserCollection(user, collection)));
        user.setBooks(Collections.singletonList(new UserBook()));

        when(collectionService.findById(anyLong())).thenReturn(Optional.of(collection));
        when(userService.findById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/collection/1/addCollection").session(session))
                .andExpect(redirectedUrl("/collection/1"));

        verify(collectionService, times(1)).findById(1L);
        verify(userService, times(1)).findById(1L);
        verify(bookService, times(1)).saveAll(anyList());
        verify(collectionService, times(1)).save(any(UserCollection.class));
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void removeCollection() throws Exception {
        mockMvc.perform(post("/collection/1/removeCollection").session(session))
                .andExpect(redirectedUrl("/collection/1"));

        verify(userService, times(1)).deleteCollectionFromUser(1L, 1L);
    }
}