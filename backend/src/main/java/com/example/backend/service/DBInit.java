package com.example.backend.service;

import com.example.backend.model.*;
import com.example.backend.repository.*;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DBInit {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CollectionRepository collectionRepository;
    private final PublisherRepository publisherRepository;
    private final CollectionAuthorRepository collectionAuthorRepository;
    private final DemographyRepository demographyRepository;

    @Autowired
    public DBInit(AuthorRepository authorRepository, BookRepository bookRepository,
                  CollectionRepository collectionRepository, PublisherRepository publisherRepository,
                  CollectionAuthorRepository collectionAuthorRepository,
                  DemographyRepository demographyRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.collectionRepository = collectionRepository;
        this.publisherRepository = publisherRepository;
        this.collectionAuthorRepository = collectionAuthorRepository;
        this.demographyRepository = demographyRepository;
    }


    @PostConstruct
    public void init() throws IOException {

        //Publishers
        Publisher ivrea = new Publisher("Ivrea");
        publisherRepository.save(ivrea);

        Publisher planetaComic = new Publisher("Planeta Comic");
        publisherRepository.save(planetaComic);

        Publisher normaEditorial = new Publisher("Norma Editorial");
        publisherRepository.save(normaEditorial);

        Publisher paniniManga = new Publisher("Panini Manga");
        publisherRepository.save(paniniManga);

        Publisher eccEdiciones = new Publisher("ECC Ediciones");
        publisherRepository.save(eccEdiciones);

        Publisher milkyWayEdiciones = new Publisher("Milky Way Ediciones");
        publisherRepository.save(milkyWayEdiciones);

        //Demographies
        Demography shonen = new Demography("Shonen");
        demographyRepository.save(shonen);

        Demography seinen = new Demography("Seinen");
        demographyRepository.save(seinen);

        Demography shojo = new Demography("Shojo");
        demographyRepository.save(shojo);

        Demography josei = new Demography("Josei");
        demographyRepository.save(josei);

        Demography kodomo = new Demography("Kodomo");
        demographyRepository.save(kodomo);


        //Collection Ao no Flag

        Collection aoNoFlagCollection = new Collection();
        Author kaito = new Author();

        List<CollectionAuthor> kaitoCollectionAuthorList = new ArrayList<>();
        CollectionAuthor kaitoCollectionAuthor = new CollectionAuthor(aoNoFlagCollection, kaito);
        kaitoCollectionAuthorList.add(kaitoCollectionAuthor);

        kaito.setName("KAITO");
        kaito.setCollections(kaitoCollectionAuthorList);

        aoNoFlagCollection.setName("Ao no Flag");
        aoNoFlagCollection.setAuthor(kaitoCollectionAuthorList);
        aoNoFlagCollection.setPublisher(ivrea);
        aoNoFlagCollection.setDemography(shonen);
        aoNoFlagCollection.setPlot("Three friends from the third year of high school and the dilemmas of life at that crucial moment when the adult world is approaching and decisions must be made. The protagonist, Taichi Ichinose, is a rather reserved boy who doesn't usually express what is happening to him and prefers to keep himself away from the rest.");
        setCollectionImage(aoNoFlagCollection, "static/misc/covers/ao_no_flag.jpg");

        List<Book> aoNoFlagBookList = new ArrayList<>();

        Book book11 = new Book(new Date(1537394400000L), false, 224, 1, aoNoFlagCollection);
        aoNoFlagBookList.add(book11);
        Book book12 = new Book(new Date(1544050800000L), false, 224, 2, aoNoFlagCollection);
        aoNoFlagBookList.add(book12);
        Book book13 = new Book(new Date(1549494000000L), false, 208, 3, aoNoFlagCollection);
        aoNoFlagBookList.add(book13);
        Book book14 = new Book(new Date(1556748000000L), false, 208, 4, aoNoFlagCollection);
        aoNoFlagBookList.add(book14);
        Book book15 = new Book(new Date(1561586400000L), false, 208, 5, aoNoFlagCollection);
        aoNoFlagBookList.add(book15);
        Book book16 = new Book(new Date(1568239200000L), false, 208, 6, aoNoFlagCollection);
        aoNoFlagBookList.add(book16);
        Book book17 = new Book(new Date(1591826400000L), false, 208, 7, aoNoFlagCollection);
        aoNoFlagBookList.add(book17);
        Book book18 = new Book(new Date(1599688800000L), false, 208, 8, aoNoFlagCollection);
        aoNoFlagBookList.add(book18);

        aoNoFlagCollection.setBooks(aoNoFlagBookList);

        authorRepository.save(kaito);
        collectionRepository.save(aoNoFlagCollection);
        bookRepository.saveAll(aoNoFlagBookList);
        collectionAuthorRepository.save(kaitoCollectionAuthor);

        // Collection Akira

        Collection akiraCollection = new Collection();
        Author katsuhitoOtomo = new Author();

        List<CollectionAuthor> katsuhitoOtomoCollectionAuthorList = new ArrayList<>();
        CollectionAuthor katsuhitoOtomoCollectionAuthor = new CollectionAuthor(akiraCollection, katsuhitoOtomo);
        katsuhitoOtomoCollectionAuthorList.add(katsuhitoOtomoCollectionAuthor);

        katsuhitoOtomo.setName("Katsuhiro Otomo");
        katsuhitoOtomo.setCollections(katsuhitoOtomoCollectionAuthorList);

        akiraCollection.setName("Akira");
        akiraCollection.setAuthor(katsuhitoOtomoCollectionAuthorList);
        akiraCollection.setPublisher(normaEditorial);
        akiraCollection.setDemography(seinen);
        akiraCollection.setPlot("In the year 2019, after a mysterious explosion that destroyed Tokyo, the city is rebuilt as Neo-Tokio. Kaneda and Tetsuo, members of a motorcycle gang, become involved in government psychic experiments that unleash uncontrollable powers.");
        setCollectionImage(akiraCollection, "static/misc/covers/akira.jpeg");

        List<Book> akiraBookList = new ArrayList<>();

        Book akiraBook1 = new Book(new Date(1572476400000L), false, 350, 1, akiraCollection);
        akiraBookList.add(akiraBook1);
        Book akiraBook2 = new Book(new Date(1599170400000L), false, 296, 2, akiraCollection);
        akiraBookList.add(akiraBook2);
        Book akiraBook3 = new Book(new Date(1607641200000L), false, 280, 3, akiraCollection);
        akiraBookList.add(akiraBook3);
        Book akiraBook4 = new Book(new Date(1612479600000L), false, 392, 4, akiraCollection);
        akiraBookList.add(akiraBook4);
        Book akiraBook5 = new Book(new Date(1617919200000L), false, 408, 5, akiraCollection);
        akiraBookList.add(akiraBook5);
        Book akiraBook6 = new Book(new Date(1623362400000L), false, 432, 6, akiraCollection);
        akiraBookList.add(akiraBook6);

        akiraCollection.setBooks(akiraBookList);

        authorRepository.save(katsuhitoOtomo);
        collectionRepository.save(akiraCollection);
        bookRepository.saveAll(akiraBookList);
        collectionAuthorRepository.save(katsuhitoOtomoCollectionAuthor);

        // Collection BLAME!

        Collection blameCollection = new Collection();
        Author tsutomuNihei = new Author();

        List<CollectionAuthor> tsutomuNiheiCollectionAuthorList = new ArrayList<>();
        CollectionAuthor tsutomuNiheiCollectionAuthor = new CollectionAuthor(blameCollection, tsutomuNihei);
        tsutomuNiheiCollectionAuthorList.add(tsutomuNiheiCollectionAuthor);

        tsutomuNihei.setName("Tsutomu Nihei");
        tsutomuNihei.setCollections(tsutomuNiheiCollectionAuthorList);

        blameCollection.setName("BLAME!");
        blameCollection.setAuthor(tsutomuNiheiCollectionAuthorList);
        blameCollection.setPublisher(paniniManga);
        blameCollection.setDemography(seinen);
        blameCollection.setPlot("In a vast world where the Megastructure expands uncontrollably, Killy, a mysterious man with a powerful weapon, seeks the Genetic Terminal to save humanity from extinction. Along his journey, he faces dangers and uncovers dark secrets in a post-apocalyptic environment.");
        setCollectionImage(blameCollection, "static/misc/covers/blame.jpg");

        List<Book> blameBookList = new ArrayList<>();

        Book blameBook1 = new Book(new Date(1497132000000L), false, 416, 1, blameCollection);
        blameBookList.add(blameBook1);
        Book blameBook2 = new Book(new Date(1499724000000L), false, 383, 2, blameCollection);
        blameBookList.add(blameBook2);
        Book blameBook3 = new Book(new Date(1505080800000L), false, 360, 3, blameCollection);
        blameBookList.add(blameBook3);
        Book blameBook4 = new Book(new Date(1510354800000L), false, 370, 4, blameCollection);
        blameBookList.add(blameBook4);
        Book blameBook5 = new Book(new Date(1518303600000L), false, 352, 5, blameCollection);
        blameBookList.add(blameBook5);
        Book blameBook6 = new Book(new Date(1523397600000L), false, 338, 6, blameCollection);
        blameBookList.add(blameBook6);

        blameCollection.setBooks(blameBookList);

        authorRepository.save(tsutomuNihei);
        collectionRepository.save(blameCollection);
        bookRepository.saveAll(blameBookList);
        collectionAuthorRepository.save(tsutomuNiheiCollectionAuthor);

        //Collection Biomega

        Collection biomegaCollection = new Collection();

        CollectionAuthor tsutomuNiheiCollectionAuthor1 = new CollectionAuthor(biomegaCollection, tsutomuNihei);
        tsutomuNiheiCollectionAuthorList.add(tsutomuNiheiCollectionAuthor1);

        tsutomuNihei.setCollections(tsutomuNiheiCollectionAuthorList);

        biomegaCollection.setName("Biomega");
        biomegaCollection.setAuthor(tsutomuNiheiCollectionAuthorList);
        biomegaCollection.setPublisher(paniniManga);
        biomegaCollection.setDemography(seinen);
        biomegaCollection.setPlot("In a post-apocalyptic future, Zouichi Kanoe, an agent of the Genetic Preservation Corporation, must protect a young girl named Eon Green. Together, they face biotechnological monsters and uncover conspiracies related to the spread of the N5S virus, which transforms humans into aberrant creatures.");
        setCollectionImage(biomegaCollection, "static/misc/covers/biomega.jpg");

        List<Book> biomegaBookList = new ArrayList<>();

        Book biomegaBook1 = new Book(new Date(1669244400000L), false, 432, 1, biomegaCollection);
        biomegaBookList.add(biomegaBook1);
        Book biomegaBook2 = new Book(new Date(1674687600000L), false, 392, 2, biomegaCollection);
        biomegaBookList.add(biomegaBook2);
        Book biomegaBook3 = new Book(new Date(1682546400000L), false, 392, 3, biomegaCollection);
        biomegaBookList.add(biomegaBook3);

        biomegaCollection.setBooks(biomegaBookList);

        collectionRepository.save(biomegaCollection);
        bookRepository.saveAll(biomegaBookList);
        collectionAuthorRepository.save(tsutomuNiheiCollectionAuthor1);

        // Collection Death Note

        Collection deathNoteCollection = new Collection();
        Author tsugumiOhba = new Author();
        Author takeshiObata = new Author();

        List<CollectionAuthor> deathNoteCollectionAuthorList = new ArrayList<>();
        CollectionAuthor tsugumiOhbaCollectionAuthor = new CollectionAuthor(deathNoteCollection, tsugumiOhba);
        deathNoteCollectionAuthorList.add(tsugumiOhbaCollectionAuthor);
        CollectionAuthor takeshiObataCollectionAuthor = new CollectionAuthor(deathNoteCollection, takeshiObata);
        deathNoteCollectionAuthorList.add(takeshiObataCollectionAuthor);

        tsugumiOhba.setName("Tsugumi Ohba");
        tsugumiOhba.setCollections(deathNoteCollectionAuthorList);
        takeshiObata.setName("Takeshi Obata");
        takeshiObata.setCollections(deathNoteCollectionAuthorList);

        deathNoteCollection.setName("Death Note");
        deathNoteCollection.setAuthor(deathNoteCollectionAuthorList);
        deathNoteCollection.setPublisher(normaEditorial);
        deathNoteCollection.setDemography(shonen);
        deathNoteCollection.setPlot("Light Yagami, a high school student, discovers a mysterious notebook called the Death Note. Whoever's name is written in the notebook dies. With the power to control life and death, Light sets out to create a utopia free of criminals, but he faces opposition from the enigmatic detective L.");
        setCollectionImage(deathNoteCollection, "static/misc/covers/death_note.jpg");

        List<Book> deathNoteBookList = new ArrayList<>();

        Book deathNoteBook1 = new Book(new Date(1363906800000L), false, 384, 1, deathNoteCollection);
        deathNoteBookList.add(deathNoteBook1);
        Book deathNoteBook2 = new Book(new Date(1369346400000L), false, 392, 2, deathNoteCollection);
        deathNoteBookList.add(deathNoteBook2);
        Book deathNoteBook3 = new Book(new Date(1372370400000L), false, 408, 3, deathNoteCollection);
        deathNoteBookList.add(deathNoteBook3);
        Book deathNoteBook4 = new Book(new Date(1374789600000L), false, 408, 4, deathNoteCollection);
        deathNoteBookList.add(deathNoteBook4);
        Book deathNoteBook5 = new Book(new Date(1377208800000L), false, 376, 5, deathNoteCollection);
        deathNoteBookList.add(deathNoteBook5);
        Book deathNoteBook6 = new Book(new Date(1380232800000L), false, 416, 6, deathNoteCollection);
        deathNoteBookList.add(deathNoteBook6);

        deathNoteCollection.setBooks(deathNoteBookList);

        authorRepository.save(tsugumiOhba);
        authorRepository.save(takeshiObata);
        collectionRepository.save(deathNoteCollection);
        bookRepository.saveAll(deathNoteBookList);
        collectionAuthorRepository.save(tsugumiOhbaCollectionAuthor);
        collectionAuthorRepository.save(takeshiObataCollectionAuthor);

    }

    private void setCollectionImage(Collection collection, String classPathResource) throws IOException {
        ClassPathResource image = new ClassPathResource(classPathResource);
        collection.setBookImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
