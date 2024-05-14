package com.example.backend.controller;

import com.example.backend.dto.CollectionDTO;
import com.example.backend.model.*;
import com.example.backend.repository.UserCollectionRepository;
import com.example.backend.service.BookService;
import com.example.backend.service.CollectionService;
import com.example.backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;
    @Autowired
    private UserCollectionRepository userCollectionRepository;

    @ModelAttribute
    public void addAttributes(Model model, HttpSession session) {
        if (session.getAttribute("logged")==null
                || session.getAttribute("user")==null
                || !session.getAttribute("logged").equals(true)) {
            model.addAttribute("logged", false);
        }else
            model.addAttribute("logged", true);
    }

    @GetMapping("/{id}")
    public String getCollection(HttpSession session, Model model, @PathVariable long id) {
        CollectionDTO collection = collectionService.findCollectionById(id);
        if (session.getAttribute("user")!= null && userService.isCollectionAdded((Long) session.getAttribute("user"), id)) {
            model.addAttribute("added", true);
            List<UserBook> userBookList = userService.findUserBooksByBooks(collection.getBooks(), (Long) session.getAttribute("user"));
            model.addAttribute("userBooks", userBookList);
        }else
            model.addAttribute("added", false);
        List<Collection> similarCollection = collectionService.findSimilarCollection(id);
        model.addAttribute("collection", collection);
        model.addAttribute("similarCollection", similarCollection);
        return "collection-example";
    }

    @GetMapping("/{id}/image")
    @Transactional
    public ResponseEntity<byte[]> downloadImage(@PathVariable long id) throws SQLException, IOException {
        Optional<Collection> optionalCollection = collectionService.findById(id);

        if (optionalCollection.isPresent() && optionalCollection.get().getBookImage() != null) {
            Collection collection = optionalCollection.get();
            InputStream imageStream = collection.getBookImage().getBinaryStream();

            // Convert InputStream to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = imageStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type if needed

            return ResponseEntity.ok().headers(headers).body(byteArrayOutputStream.toByteArray());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/addCollection")
    public String addCollection(HttpSession session, Model model, @PathVariable long id) {
        Optional<Collection> collection = collectionService.findById(id);
        Long currentUserId = (Long) session.getAttribute("user");

        Optional<User> optionalUser = userService.findById(currentUserId);

        UserCollectionId userCollectionId = new UserCollectionId(id, optionalUser.get().getId());

        UserCollection userCollection = new UserCollection();
        userCollection.setId(userCollectionId);
        userCollection.setUser(optionalUser.get());
        userCollection.setCollection(collection.get());
        userCollection.setAdded(true);

        List<UserBook> userBookList = new ArrayList<>();
        for (Book book : collection.get().getBooks()) {
            UserBookId userBookId = new UserBookId(optionalUser.get().getId(), book.getId());
            UserBook userBook = new UserBook(book, optionalUser.get(), userBookId, id);
            userBookList.add(userBook);
        }

        optionalUser.get().getCollections().add(userCollection);
        optionalUser.get().getBooks().addAll(userBookList);

        bookService.saveAll(userBookList);
        collectionService.save(userCollection);
        userService.save(optionalUser.get());

        return "redirect:/collection/" + id;
    }

    @PostMapping("/{id}/removeCollection")
    public String removeCollection(HttpSession session, Model model, @PathVariable long id) {
        userService.deleteCollectionFromUser((Long) session.getAttribute("user"), id);
        model.addAttribute("added", false);
        return "redirect:/collection/" + id;
    }
}
