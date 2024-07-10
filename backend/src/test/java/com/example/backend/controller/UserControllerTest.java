package com.example.backend.controller;

import com.example.backend.model.*;
import com.example.backend.service.CollectionService;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private CollectionService collectionService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private MockHttpSession session;
    private User user;
    private Collection collection;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        session = new MockHttpSession();
        session.setAttribute("user", 1L);

        user = new User();
        user.setId(1L);

        collection = new Collection();
        collection.setId(1L);

        UserCollection userCollection = new UserCollection();
        userCollection.setCollection(collection);
        user.setCollections(List.of(userCollection));
    }

    @Test
    void getProfile() throws Exception {
        when(userService.findById(anyLong())).thenReturn(Optional.of(user));
        when(collectionService.findCollectionsByIds(anyList())).thenReturn(Collections.singletonList(collection));
        when(userService.getBooksReadPerMonth(anyLong())).thenReturn(Collections.emptyList());
        when(userService.findUserReadCollections(anyLong())).thenReturn(Collections.singletonList(collection));
        when(userService.getMostCommonAuthorByUserId(anyLong())).thenReturn(new Author());
        when(userService.getMostCommonDemographyByUserId(anyLong())).thenReturn(new Demography("Demography Name"));

        mockMvc.perform(get("/collection/profile").session(session))
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("addedCollections"))
                .andExpect(model().attributeExists("addedCollectionsCount"))
                .andExpect(model().attributeExists("booksReadByMonth"))
                .andExpect(model().attributeExists("readCollections"))
                .andExpect(model().attributeExists("readCollectionsCount"))
                .andExpect(model().attributeExists("readPercentage"))
                .andExpect(model().attributeExists("mostCommonAuthor"))
                .andExpect(model().attributeExists("mostCommonDemography"));

        verify(userService, times(1)).findById(1L);
        verify(collectionService, times(1)).findCollectionsByIds(anyList());
        verify(userService, times(1)).getBooksReadPerMonth(1L);
        verify(userService, times(1)).findUserReadCollections(1L);
        verify(userService, times(1)).getMostCommonAuthorByUserId(1L);
        verify(userService, times(1)).getMostCommonDemographyByUserId(1L);
    }
}