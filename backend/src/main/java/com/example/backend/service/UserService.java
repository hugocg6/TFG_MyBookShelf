package com.example.backend.service;

import com.example.backend.dto.BookDTO;
import com.example.backend.model.*;
import com.example.backend.repository.UserBookRepository;
import com.example.backend.repository.UserCollectionRepository;
import com.example.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCollectionRepository userCollectionRepository;

    @Autowired
    private UserBookRepository userBookRepository;

    public Optional<User> findById(Long id) {return userRepository.findById(id);}

    public User save(User user) {return userRepository.save(user);}

    public boolean isCollectionAdded(Long userId, Long collectionId) {return userCollectionRepository.isCollectionAdded(userId, collectionId);}

    @Transactional
    public void deleteCollectionFromUser(Long userId, Long collectionId) {userCollectionRepository.deleteCollectionFromUser(userId, collectionId);}

    public List<UserBook> findUserBooksByBooks(List<BookDTO> collectionBooks, Long currentUserId) {
        List<Long> bookIds = new ArrayList<>();
        for (BookDTO bookDTO : collectionBooks) {
            bookIds.add(bookDTO.getId());
        }
        return userBookRepository.findUserBooksByBooks(bookIds, currentUserId);
    }

    public UserBook findByUserIdAndBookId(Long userId, Long bookId) {return userBookRepository.findByUserIdAndBookId(userId, bookId);}

    public UserCollection findByUserIdAndCollectionId(Long userId, Long collectionId) {return userCollectionRepository.findByUserIdAndCollectionId(userId, collectionId);}

    public UserBook save(UserBook userBook) {return userBookRepository.save(userBook);}

    public UserCollection save(UserCollection userCollection) {return userCollectionRepository.save(userCollection);}

    public List<Collection> findPartiallyReadCollections(Long userId) {return userCollectionRepository.findPartiallyReadCollections(userId);}

    public int countUnreadBooksInCollection(Long userId, Long collectionId) {return userBookRepository.countUnreadBooksInCollection(userId, collectionId);}

    public List<Collection> findUserReadCollections(Long userId) {return userCollectionRepository.findUserReadCollections(userId);}

    public UserCollection findUserCollectionByUserAndCollection(Long userId, Long collectionId){ return userCollectionRepository.findByUserIdCollection(userId, collectionId);}

    public Collection getLastReadCollectionByUserId(Long userId){
        List<Collection> result = userCollectionRepository.findLastReadCollectionByUserId(userId, PageRequest.of(0,1));
        return result.isEmpty() ? null : result.get(0);
    }

    public Author getMostCommonAuthorByUserId(Long userId) {
        List<Author> result = userCollectionRepository.findMostCommonAuthorByUserId(userId, PageRequest.of(0,1));
        return result.isEmpty() ? null : result.get(0);
    }

    public Demography getMostCommonDemographyByUserId(Long userId) {
        List<Demography> result = userCollectionRepository.findMostCommonDemographyByUserId(userId, PageRequest.of(0,1));
        return result.isEmpty() ? null : result.get(0);
    }
}
