package com.example.backend.controller;

import com.example.backend.model.Collection;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.CollectionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    CollectionService collectionService;

    @ModelAttribute
    public void addAttributes(Model model, HttpSession session) {
        if (session.getAttribute("logged")==null
                || session.getAttribute("user")==null
                || !session.getAttribute("logged").equals(true)) {
            model.addAttribute("logged", false);
        }else
            model.addAttribute("logged", true);
    }

    @GetMapping("/home")
    public String init(Model model, HttpSession session) {
        List<Collection> recentlyAdded = collectionService.findLastAddedCollection();
        model.addAttribute("recentlyAdded", recentlyAdded);
        return "index";
    }

}
