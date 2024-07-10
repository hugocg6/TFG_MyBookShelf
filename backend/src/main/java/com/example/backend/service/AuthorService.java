package com.example.backend.service;

import com.example.backend.model.CollectionAuthor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    public List<String> getAuthorsNames(List<CollectionAuthor> authorsList){
        return authorsList.stream()
                .map(collectionAuthor -> collectionAuthor.getAuthor().getName())
                .collect(Collectors.toList());
    }
}
