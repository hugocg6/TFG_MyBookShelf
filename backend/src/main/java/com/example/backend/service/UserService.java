package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserCollectionRepository;
import com.example.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCollectionRepository userCollectionRepository;

    public Optional<User> findById(Long id) {return userRepository.findById(id);}

    public User save(User user) {return userRepository.save(user);}

    public boolean isCollectionAdded(Long userId, Long collectionId) {return userCollectionRepository.isCollectionAdded(userId, collectionId);}

    @Transactional
    public void deleteCollectionFromUser(Long userId, Long collectionId) {userCollectionRepository.deleteCollectionFromUser(userId, collectionId);}
}
