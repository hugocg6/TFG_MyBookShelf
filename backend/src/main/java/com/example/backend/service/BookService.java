package com.example.backend.service;

import com.example.backend.model.UserBook;
import com.example.backend.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private UserBookRepository userBookRepository;

    public UserBook save(UserBook userBook) {return userBookRepository.save(userBook);}
    public List<UserBook> saveAll(List<UserBook> userBooks) { return userBookRepository.saveAll(userBooks);}
}
