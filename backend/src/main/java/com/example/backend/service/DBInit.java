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

    @Autowired
    public DBInit(AuthorRepository authorRepository, BookRepository bookRepository,
                  CollectionRepository collectionRepository, PublisherRepository publisherRepository,
                  CollectionAuthorRepository collectionAuthorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.collectionRepository = collectionRepository;
        this.publisherRepository = publisherRepository;
        this.collectionAuthorRepository = collectionAuthorRepository;
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

        //Collection 1

        List<Book> bookList1 = new ArrayList<>();

        Book book11 = new Book(new Date(1537394400000L), false, 224, 1);
        bookList1.add(book11);
        Book book12 = new Book(new Date(1544050800000L), false, 224, 2);
        bookList1.add(book12);
        Book book13 = new Book(new Date(1549494000000L), false, 208, 3);
        bookList1.add(book13);
        Book book14 = new Book(new Date(1556748000000L), false, 208, 4);
        bookList1.add(book14);
        Book book15 = new Book(new Date(1561586400000L), false, 208, 5);
        bookList1.add(book15);
        Book book16 = new Book(new Date(1568239200000L), false, 208, 6);
        bookList1.add(book16);
        Book book17 = new Book(new Date(1591826400000L), false, 208, 7);
        bookList1.add(book17);
        Book book18 = new Book(new Date(1599688800000L), false, 208, 8);
        bookList1.add(book18);

        bookRepository.saveAll(bookList1);

        Collection collection1 = new Collection();
        Author kaito = new Author();

        List<CollectionAuthor> kaitoCollectionAuthorList = new ArrayList<>();
        CollectionAuthor kaitoCollectionAuthor = new CollectionAuthor(collection1, kaito);
        kaitoCollectionAuthorList.add(kaitoCollectionAuthor);

        kaito.setName("KAITO");
        kaito.setCollections(kaitoCollectionAuthorList);

        collection1.setName("Ao no Flag");
        collection1.setBooks(bookList1);
        collection1.setAuthor(kaitoCollectionAuthorList);
        collection1.setPublisher(ivrea);
        collection1.setPlot("Tres amigos de tercero de bachillerato y los dilemas de la vida en ese momento crucial en el que el mundo adulto se va acercando y hay que tomar decisiones. El protagonista, Taichi Ichinose, es un chico bastante seco, que no suele exteriorizar lo que le ocurre, y que prefiere mantenerse alejado del resto." +
                "\n" +
                "Pero su compañera de clase, Futaba Kuze, acaba convenciéndolo para que la ayude a tener una cita con su amigo de la infancia, Touma Mita, uno de los chicos más populares del colegio. Esos que son simpáticos, alegres, buenos en todo lo que hacen y admirados. O sea, todo lo contrario a él." +
                "\n" +
                "Por supuesto que las cosas no serán tan sencillas, ni los sentimientos tan correspondidos, al menos en el sentido más tradicional y esperado en este tipo de mangas. Una especie de triángulo amoroso se va formando (en todo el sentido de la palabra), en el que inicialmente todos parecen perder.");
        setCollectionImage(collection1, "covers/ao_no_flag.jpg");

        authorRepository.save(kaito);
        collectionRepository.save(collection1);
        collectionAuthorRepository.save(kaitoCollectionAuthor);

        // Collection Akira
        List<Book> akiraBookList = new ArrayList<>();

        Book akiraBook1 = new Book(new Date(1572476400000L), false, 350, 1);
        akiraBookList.add(akiraBook1);
        Book akiraBook2 = new Book(new Date(1599170400000L), false, 296, 2);
        akiraBookList.add(akiraBook2);
        Book akiraBook3 = new Book(new Date(1607641200000L), false, 280, 3);
        akiraBookList.add(akiraBook3);
        Book akiraBook4 = new Book(new Date(1612479600000L), false, 392, 4);
        akiraBookList.add(akiraBook4);
        Book akiraBook5 = new Book(new Date(1617919200000L), false, 408, 5);
        akiraBookList.add(akiraBook5);
        Book akiraBook6 = new Book(new Date(1623362400000L), false, 432, 6);
        akiraBookList.add(akiraBook6);

        bookRepository.saveAll(akiraBookList);

        Collection akiraCollection = new Collection();
        Author katsuhitoOtomo = new Author();

        List<CollectionAuthor> katsuhitoOtomoCollectionAuthorList = new ArrayList<>();
        CollectionAuthor katsuhitoOtomoCollectionAuthor = new CollectionAuthor(akiraCollection, katsuhitoOtomo);
        katsuhitoOtomoCollectionAuthorList.add(katsuhitoOtomoCollectionAuthor);

        katsuhitoOtomo.setName("Katsuhiro Otomo");
        katsuhitoOtomo.setCollections(katsuhitoOtomoCollectionAuthorList);

        akiraCollection.setName("Akira");
        akiraCollection.setBooks(akiraBookList);
        akiraCollection.setAuthor(katsuhitoOtomoCollectionAuthorList);
        akiraCollection.setPublisher(normaEditorial);
        akiraCollection.setPlot("En el año 2019, después de una misteriosa explosión que destruyó Tokio, la ciudad se reconstruye como Neo-Tokio. Kaneda y Tetsuo, miembros de una pandilla de motociclistas, se ven involucrados en experimentos gubernamentales psíquicos que desatan poderes incontrolables.");

        setCollectionImage(akiraCollection, "covers/akira.jpeg");

        authorRepository.save(katsuhitoOtomo);
        collectionRepository.save(akiraCollection);
        collectionAuthorRepository.save(katsuhitoOtomoCollectionAuthor);

        // Collection BLAME!
        List<Book> blameBookList = new ArrayList<>();

        Book blameBook1 = new Book(new Date(1497132000000L), false, 416, 1);
        blameBookList.add(blameBook1);
        Book blameBook2 = new Book(new Date(1499724000000L), false, 383, 2);
        blameBookList.add(blameBook2);
        Book blameBook3 = new Book(new Date(1505080800000L), false, 360, 3);
        blameBookList.add(blameBook3);
        Book blameBook4 = new Book(new Date(1510354800000L), false, 370, 4);
        blameBookList.add(blameBook4);
        Book blameBook5 = new Book(new Date(1518303600000L), false, 352, 5);
        blameBookList.add(blameBook5);
        Book blameBook6 = new Book(new Date(1523397600000L), false, 338, 6);
        blameBookList.add(blameBook6);

        bookRepository.saveAll(blameBookList);

        Collection blameCollection = new Collection();
        Author tsutomuNihei = new Author();

        List<CollectionAuthor> tsutomuNiheiCollectionAuthorList = new ArrayList<>();
        CollectionAuthor tsutomuNiheiCollectionAuthor = new CollectionAuthor(blameCollection, tsutomuNihei);
        tsutomuNiheiCollectionAuthorList.add(tsutomuNiheiCollectionAuthor);

        tsutomuNihei.setName("Tsutomu Nihei");
        tsutomuNihei.setCollections(tsutomuNiheiCollectionAuthorList);

        blameCollection.setName("BLAME!");
        blameCollection.setBooks(blameBookList);
        blameCollection.setAuthor(tsutomuNiheiCollectionAuthorList);
        blameCollection.setPublisher(paniniManga);
        blameCollection.setPlot("En un vasto mundo donde la Megaestructura se expande sin control, Killy, un hombre misterioso con un arma poderosa, busca la Terminal Genética para salvar a la humanidad de la extinción. En su camino, enfrenta peligros y descubre secretos oscuros en un entorno postapocalíptico.");

        setCollectionImage(blameCollection, "covers/blame.jpg");

        authorRepository.save(tsutomuNihei);
        collectionRepository.save(blameCollection);
        collectionAuthorRepository.save(tsutomuNiheiCollectionAuthor);

        //Collection Biomega
        List<Book> biomegaBookList = new ArrayList<>();

        Book biomegaBook1 = new Book(new Date(1669244400000L), false, 432, 1);
        biomegaBookList.add(biomegaBook1);
        Book biomegaBook2 = new Book(new Date(1674687600000L), false, 392, 2);
        biomegaBookList.add(biomegaBook2);
        Book biomegaBook3 = new Book(new Date(1682546400000L), false, 392, 3);
        biomegaBookList.add(biomegaBook3);

        bookRepository.saveAll(biomegaBookList);

        Collection biomegaCollection = new Collection();

        CollectionAuthor tsutomuNiheiCollectionAuthor1 = new CollectionAuthor(biomegaCollection, tsutomuNihei);
        tsutomuNiheiCollectionAuthorList.add(tsutomuNiheiCollectionAuthor1);

        tsutomuNihei.setCollections(tsutomuNiheiCollectionAuthorList);

        biomegaCollection.setName("Biomega");
        biomegaCollection.setBooks(biomegaBookList);
        biomegaCollection.setAuthor(tsutomuNiheiCollectionAuthorList);
        biomegaCollection.setPublisher(paniniManga);
        biomegaCollection.setPlot("En un futuro postapocalíptico, Zouichi Kanoe, un agente de la Corporación de Salvaguardia Genética, debe proteger a una joven llamada Eon Green. Juntos, enfrentan a monstruos biotecnológicos y descubren conspiraciones relacionadas con la propagación del virus N5S que convierte a los humanos en criaturas aberrantes.");

        setCollectionImage(biomegaCollection, "covers/biomega.jpg");

        collectionRepository.save(biomegaCollection);
        collectionAuthorRepository.save(tsutomuNiheiCollectionAuthor1);


    }

    private void setCollectionImage(Collection collection, String classPathResource) throws IOException {
        ClassPathResource image = new ClassPathResource(classPathResource);
        collection.setBookImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
