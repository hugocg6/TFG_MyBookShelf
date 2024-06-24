package com.example.backend.controller;

import com.example.backend.model.Collection;
import com.example.backend.model.User;
import com.example.backend.model.UserCollection;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.CollectionService;
import com.example.backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute
    public void addAttributes(Model model, HttpSession session) {
        if (session.getAttribute("logged")==null
                || session.getAttribute("user")==null
                || !session.getAttribute("logged").equals(true)) {
            model.addAttribute("logged", false);
        }else
            model.addAttribute("logged", true);
    }

    @PostMapping("/profile/register")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password,
                               @RequestParam("email") String email, HttpSession session) {

        User user = new User(email, username, passwordEncoder.encode(password),
                false, null, new ArrayList<>(), new ArrayList<>());

        userService.save(user);
        session.setAttribute("user", user.getId());
        session.setAttribute("logged", true);
        return "redirect:/home";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session){
        Long userId = (Long) session.getAttribute("user");
        Optional<User> user = userService.findById(userId);
        List<Long> collectionIds = user.get().getCollections()
                        .stream()
                        .map(userCollection -> userCollection.getCollection().getId())
                        .toList();
        List<Collection> addedCollections = collectionService.findCollectionsByIds(collectionIds);
        model.addAttribute("addedCollections", addedCollections);
        model.addAttribute("addedCollectionsCount", addedCollections.size());

        List<Collection> readCollections = userService.findUserReadCollections(userId);
        model.addAttribute("readCollections", readCollections);
        model.addAttribute("readCollectionsCount", readCollections.size());
        int readPercentage = !addedCollections.isEmpty() ? (int)(((double)readCollections.size()/addedCollections.size()) * 100) : 0;
        model.addAttribute("readPercentage", Integer.toString(readPercentage));

        model.addAttribute("mostCommonAuthor", userService.getMostCommonAuthorByUserId(userId).getName());
        model.addAttribute("mostCommonDemography", userService.getMostCommonDemographyByUserId(userId).getName());
        return "profile";
    }
}
