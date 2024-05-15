package com.example.backend.controller;

import com.example.backend.model.UserBook;
import com.example.backend.model.UserCollection;
import com.example.backend.repository.UserBookRepository;
import com.example.backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.Date;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private UserService userService;

    @PostMapping("/{bookId}/markRead")
    public String markRead(HttpSession session, @PathVariable("bookId") Long bookId) {

        Long userId = (Long) session.getAttribute("user");

        UserBook userBook = userService.findByUserIdAndBookId(userId, bookId);
        userBook.setRead(true);
        userBook.setReadingDate(new Date());
        userService.save(userBook);

        Long collectionId = userBook.getCollectionId();

        if(userService.countUnreadBooksInCollection(userId, collectionId) == 0) {
            UserCollection userCollection = userService.findByUserIdAndCollectionId(userId, collectionId);
            userCollection.setRead(true);
            userCollection.setReadDate(new Date());
            userService.save(userCollection);
        }
    return "redirect:/collection/" + userBook.getCollectionId();
    }

    @PostMapping("/{bookId}/unmarkRead")
    public String unmarkRead(HttpSession session, @PathVariable("bookId") Long bookId) {

        Long userId = (Long) session.getAttribute("user");

        UserBook userBook = userService.findByUserIdAndBookId(userId, bookId);
        userBook.setRead(false);
        userBook.setReadingDate(null);
        userService.save(userBook);

        Long collectionId = userBook.getCollectionId();

        if(userService.countUnreadBooksInCollection(userId, collectionId) != 0) {
            UserCollection userCollection = userService.findByUserIdAndCollectionId(userId, collectionId);
            userCollection.setRead(false);
            userCollection.setReadDate(null);
            userService.save(userCollection);
        }

        return "redirect:/collection/" + userBook.getCollectionId();
    }
}
