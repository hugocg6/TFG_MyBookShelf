package com.example.backend.service;

import com.example.backend.dto.BookDTO;
import com.example.backend.dto.CollectionDTO;
import com.example.backend.model.Book;
import com.example.backend.model.Collection;
import com.example.backend.model.UserCollection;
import com.example.backend.repository.CollectionRepository;
import com.example.backend.repository.UserCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectionService {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserCollectionRepository userCollectionRepository;

    public List<Collection> findAll(){return collectionRepository.findAllByOrderByNameAsc();}

    public Optional<Collection> findById(long id){return collectionRepository.findById(id);}

    public List<Collection> findLastAddedCollection(){return collectionRepository.findLastAddedCollection();}

    public List<Collection> findCollectionByAuthorName(String authorName){return collectionRepository.findCollectionByAuthorName(authorName);}

    public List<Collection> findCollectionByDemography(String demography){return collectionRepository.findCollectionByDemography(demography);}

    public UserCollection save(UserCollection userCollection){return userCollectionRepository.save(userCollection);}

    public List<Collection> findCollectionsByIds(List<Long> collectionsIds){return collectionRepository.findCollectionsByIdIs(collectionsIds);}

    public List<Collection> searchCollections(String query){return collectionRepository.searchCollections(query);}

    public List<Collection> findSimilarCollection(Long collectionId){
        List<Collection> sameAuthorCollections = collectionRepository.findCollectionsWithSameAuthor(collectionId);

        // Fetch collections with the same demography
        List<Collection> sameDemographyCollections = collectionRepository.findCollectionsWithSameDemography(collectionId);

        HashSet<Collection> combinedCollections = new HashSet<>(sameAuthorCollections);
        combinedCollections.addAll(sameDemographyCollections);

        // Return the first 6 collections
        return combinedCollections.stream().limit(6).collect(Collectors.toList());
    }

    public CollectionDTO findCollectionById(long id){
        return collectionRepository.findById(id)
                .map(collection -> {
                    return new CollectionDTO(
                            collection.getId(), collection.getName(),
                            entityToDTO(collection.getBooks()),
                            authorService.getAuthorsNames(collection.getAuthor()),
                            collection.getPublisher().getName(),
                            collection.getPlot(), collection.getDemography().getName());
                })
                .orElse(null);
    }

    private List<BookDTO> entityToDTO(List<Book> entityList){
        return entityList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BookDTO convertToDto(Book book) {
        BookDTO bookDto = new BookDTO();
        bookDto.setId(book.getId());
        bookDto.setNum(book.getNumeration());
        bookDto.setPublishDate(dateMonthYearFormatter(book.getPublishDate().getTime()));
        bookDto.setPages(book.getPages());
        return bookDto;
    }

    private String dateMonthYearFormatter(long dateLong){
        Date date = new Date(dateLong);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        return dateFormat.format(date);
    }
}
