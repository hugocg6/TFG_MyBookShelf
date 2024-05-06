package com.example.backend.controller;

import com.example.backend.model.Collection;
import com.example.backend.service.CollectionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SearchController {

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

    @GetMapping("/search")
    public String searchAll(Model model) {
        List<Collection> collections = collectionService.findAll();
        model.addAttribute("collections", collections);
        model.addAttribute("header", "All collections");
        return "search";
    }

    @GetMapping("search/author/{author}")
    public String searchByAuthor(@PathVariable String author, Model model) {
        List<Collection> collections = collectionService.findCollectionByAuthorName(author);
        model.addAttribute("collections", collections);
        model.addAttribute("header", author);
        return "search";
    }

    @GetMapping("search/demography/{demography}")
    public String searchByDemography(@PathVariable String demography, Model model) {
        List<Collection> collections = collectionService.findCollectionByDemography(demography);
        model.addAttribute("collections", collections);
        model.addAttribute("header", demography);
        return "search";
    }
}
