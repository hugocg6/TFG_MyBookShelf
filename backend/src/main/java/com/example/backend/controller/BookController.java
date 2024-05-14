package com.example.backend.controller;

import com.example.backend.model.UserBook;
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
        UserBook userBook = userService.findByUserIdAndBookId((Long) session.getAttribute("user"), bookId);
        userBook.setRead(true);
        userBook.setReadingDate(new Date());
        userService.save(userBook);
    return "redirect:/collection/" + userBook.getCollectionId();
    }

    @PostMapping("/{bookId}/unmarkRead")
    public String unmarkRead(HttpSession session, @PathVariable("bookId") Long bookId) {
        UserBook userBook = userService.findByUserIdAndBookId((Long) session.getAttribute("user"), bookId);
        userBook.setRead(false);
        userBook.setReadingDate(null);
        userService.save(userBook);
        return "redirect:/collection/" + userBook.getCollectionId();
    }
}
