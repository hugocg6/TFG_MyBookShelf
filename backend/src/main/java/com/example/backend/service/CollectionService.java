package com.example.backend.service;

import com.example.backend.dto.BookDTO;
import com.example.backend.dto.CollectionDTO;
import com.example.backend.model.Book;
import com.example.backend.model.Collection;
import com.example.backend.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectionService {

    private final AuthorService authorService;
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository, AuthorService authorService) {
        this.collectionRepository = collectionRepository;
        this.authorService = authorService;
    }

    public List<Collection> findAll(){return collectionRepository.findAllByOrderByNameAsc();}

    public Optional<Collection> findById(long id){return collectionRepository.findById(id);}

    public List<Collection> findLastAddedCollection(){return collectionRepository.findLastAddedCollection();}

    public List<Collection> findCollectionByAuthorName(String authorName){return collectionRepository.findCollectionByAuthorName(authorName);}

    public List<Collection> findCollectionByDemography(String demography){return collectionRepository.findCollectionByDemography(demography);}

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
