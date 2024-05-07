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

        //Collection Abara

        Collection abaraCollection = new Collection();

        CollectionAuthor tsutomuNiheiCollectionAuthor2 = new CollectionAuthor(abaraCollection, tsutomuNihei);
        tsutomuNiheiCollectionAuthorList.add(tsutomuNiheiCollectionAuthor2);

        tsutomuNihei.setCollections(tsutomuNiheiCollectionAuthorList);

        abaraCollection.setName("Abara");
        abaraCollection.setAuthor(tsutomuNiheiCollectionAuthorList);
        abaraCollection.setPublisher(paniniManga);
        abaraCollection.setDemography(seinen);
        abaraCollection.setPlot("In a world where creatures known as Gauna appear, humanity turns to fighters called 'Kuro Gauna' to protect themselves. Itou Denji is a Kuro Gauna who must face the chaos and violence of a dystopian world while fighting monstrous beings.");
        setCollectionImage(abaraCollection, "static/misc/covers/abara.jpg");

        List<Book> abaraBookList = new ArrayList<>();

        Book abaraBook1 = new Book(new Date(1666821600000L), false, 404, 1, abaraCollection);
        abaraBookList.add(abaraBook1);

        abaraCollection.setBooks(abaraBookList);

        collectionRepository.save(abaraCollection);
        bookRepository.saveAll(abaraBookList);
        collectionAuthorRepository.save(tsutomuNiheiCollectionAuthor2);

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

        //Collection Punpun

        Collection punpunCollection = new Collection();
        Author inioAsano = new Author();

        List<CollectionAuthor> asanoCollectionAuthorList = new ArrayList<>();
        CollectionAuthor asanoCollectionAuthor = new CollectionAuthor(punpunCollection, inioAsano);
        asanoCollectionAuthorList.add(asanoCollectionAuthor);

        inioAsano.setName("Inio Asano");
        inioAsano.setCollections(asanoCollectionAuthorList);

        punpunCollection.setName("Goodnight Punpun");
        punpunCollection.setAuthor(asanoCollectionAuthorList);
        punpunCollection.setPublisher(normaEditorial);
        punpunCollection.setDemography(seinen);
        punpunCollection.setPlot("The story follows the life of Punpun Punyama, a young man whose life is marked by difficult events and complex relationships. As Punpun grows up, he faces loss, heartbreak, and a sense of hopelessness, all while maintaining a facade of normality.");
        setCollectionImage(punpunCollection, "static/misc/covers/punpun.jpg");

        List<Book> punpunBookList = new ArrayList<>();

        Book punpunBook1 = new Book(new Date(1446073200000L), false, 224, 1, punpunCollection);
        punpunBookList.add(punpunBook1);
        Book punpunBook2 = new Book(new Date(1449183600000L), false, 208, 2, punpunCollection);
        punpunBookList.add(punpunBook2);
        Book punpunBook3 = new Book(new Date(1453417200000L), false, 208, 3, punpunCollection);
        punpunBookList.add(punpunBook3);
        Book punpunBook4 = new Book(new Date(1455836400000L), false, 224, 4, punpunCollection);
        punpunBookList.add(punpunBook4);
        Book punpunBook5 = new Book(new Date(1458255600000L), false, 224, 5, punpunCollection);
        punpunBookList.add(punpunBook5);
        Book punpunBook6 = new Book(new Date(1460325600000L), false, 224, 6, punpunCollection);
        punpunBookList.add(punpunBook6);
        Book punpunBook7 = new Book(new Date(1462399200000L), false, 224, 7, punpunCollection);
        punpunBookList.add(punpunBook7);
        Book punpunBook8 = new Book(new Date(1466632800000L), false, 224, 8, punpunCollection);
        punpunBookList.add(punpunBook8);
        Book punpunBook9 = new Book(new Date(1469138400000L), false, 224, 9, punpunCollection);
        punpunBookList.add(punpunBook9);
        Book punpunBook10 = new Book(new Date(1472162400000L), false, 224, 10, punpunCollection);
        punpunBookList.add(punpunBook10);
        Book punpunBook11 = new Book(new Date(1475186400000L), false, 224, 11, punpunCollection);
        punpunBookList.add(punpunBook11);
        Book punpunBook12 = new Book(new Date(1477692000000L), false, 256, 12, punpunCollection);
        punpunBookList.add(punpunBook12);
        Book punpunBook13 = new Book(new Date(1477692000000L), false, 272, 13, punpunCollection);
        punpunBookList.add(punpunBook13);

        punpunCollection.setBooks(punpunBookList);

        authorRepository.save(inioAsano);
        collectionRepository.save(punpunCollection);
        bookRepository.saveAll(punpunBookList);
        collectionAuthorRepository.save(asanoCollectionAuthor);

        // Collection Innocent

        Collection innocentCollection = new Collection();
        Author shinichiSakamoto = new Author();

        List<CollectionAuthor> shinichiSakamotoCollectionAuthorList = new ArrayList<>();
        CollectionAuthor innocentCollectionAuthor = new CollectionAuthor(innocentCollection, shinichiSakamoto);
        shinichiSakamotoCollectionAuthorList.add(innocentCollectionAuthor);

        shinichiSakamoto.setName("Shin'ichi Sakamoto");
        shinichiSakamoto.setCollections(shinichiSakamotoCollectionAuthorList);

        innocentCollection.setName("Innocent");
        innocentCollection.setAuthor(shinichiSakamotoCollectionAuthorList);
        innocentCollection.setPublisher(milkyWayEdiciones);
        innocentCollection.setDemography(seinen);
        innocentCollection.setPlot("The story follows Charles-Henri Sanson, who, as France's royal executioner, is destined to execute people for their crimes. However, Sanson struggles with the moral dilemma of his work and the events surrounding the French Revolution.");
        setCollectionImage(innocentCollection, "static/misc/covers/innocent.jpg");

        List<Book> innocentBookList = new ArrayList<>();

        Book innocentBook1 = new Book(new Date(1477346400000L), false, 204, 1, innocentCollection);
        innocentBookList.add(innocentBook1);
        Book innocentBook2 = new Book(new Date(1481842800000L), false, 208, 2, innocentCollection);
        innocentBookList.add(innocentBook2);
        Book innocentBook3 = new Book(new Date(1488150000000L), false, 210, 3, innocentCollection);
        innocentBookList.add(innocentBook3);
        Book innocentBook4 = new Book(new Date(1493244000000L), false, 210, 4, innocentCollection);
        innocentBookList.add(innocentBook4);
        Book innocentBook5 = new Book(new Date(1498514400000L), false, 226, 5, innocentCollection);
        innocentBookList.add(innocentBook5);
        Book innocentBook6 = new Book(new Date(1503957600000L), false, 210, 6, innocentCollection);
        innocentBookList.add(innocentBook6);
        Book innocentBook7 = new Book(new Date(1508882400000L), false, 210, 7, innocentCollection);
        innocentBookList.add(innocentBook7);
        Book innocentBook8 = new Book(new Date(1514415600000L), false, 226, 8, innocentCollection);
        innocentBookList.add(innocentBook8);
        Book innocentBook9 = new Book(new Date(1519772400000L), false, 226, 9, innocentCollection);
        innocentBookList.add(innocentBook9);

        innocentCollection.setBooks(innocentBookList);

        authorRepository.save(shinichiSakamoto);
        collectionRepository.save(innocentCollection);
        bookRepository.saveAll(innocentBookList);
        collectionAuthorRepository.save(innocentCollectionAuthor);

        //Collection The Climber

        Collection climberCollection = new Collection();

        CollectionAuthor shinichiSakamotoCollectionAuthor1 = new CollectionAuthor(climberCollection, shinichiSakamoto);
        shinichiSakamotoCollectionAuthorList.add(shinichiSakamotoCollectionAuthor1);

        shinichiSakamoto.setCollections(shinichiSakamotoCollectionAuthorList);

        climberCollection.setName("The Climber");
        climberCollection.setAuthor(shinichiSakamotoCollectionAuthorList);
        climberCollection.setPublisher(milkyWayEdiciones);
        climberCollection.setDemography(seinen);
        climberCollection.setPlot("Mori Buntarou is a solitary high school student who discovers a passion for mountain climbing. As he ascends to greater heights, he learns about perseverance, friendship, and the harsh realities of the mountains. 'The Climber' explores the internal and external struggles faced by those who pursue their dreams, no matter the risk.");
        setCollectionImage(climberCollection, "static/misc/covers/the_climber.jpg");

        List<Book> climberBookList = new ArrayList<>();

        int climberVolumeCount = 17; // "The Climber" has 17 volumes
        long climberStartPublicationDate = 1201824000000L; // First volume's publication date (example)
        long climberInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= climberVolumeCount; i++) {
            Book book = new Book(new Date(climberStartPublicationDate + (i - 1) * climberInterval), false, 200, i, climberCollection);
            climberBookList.add(book);
        }

        climberCollection.setBooks(climberBookList);

        collectionRepository.save(climberCollection);
        bookRepository.saveAll(climberBookList);
        collectionAuthorRepository.save(shinichiSakamotoCollectionAuthor1);


        //Collection Nijigahara Holograph

        Collection nijigahraCollection = new Collection();

        CollectionAuthor asanoCollectionAuthor1 = new CollectionAuthor(nijigahraCollection, inioAsano);
        asanoCollectionAuthorList.add(asanoCollectionAuthor1);

        inioAsano.setCollections(asanoCollectionAuthorList);

        nijigahraCollection.setName("Nijigahara Holograph");
        nijigahraCollection.setAuthor(asanoCollectionAuthorList);
        nijigahraCollection.setPublisher(milkyWayEdiciones);
        nijigahraCollection.setDemography(seinen);
        nijigahraCollection.setPlot("Nijigahara Holograph follows a group of students whose lives are intertwined by a mysterious incident that occurred in the past. As the characters grow, the repercussions of this event resonate throughout their lives, exploring pain, trauma and violence under a dark and surreal atmosphere.");
        setCollectionImage(nijigahraCollection, "static/misc/covers/nijigahara_holograph.jpg");

        List<Book> nijigahraBookList = new ArrayList<>();

        Book nijigahraBook1 = new Book(new Date(1411423200000L), false, 300, 1, nijigahraCollection);
        nijigahraBookList.add(nijigahraBook1);

        nijigahraCollection.setBooks(nijigahraBookList);

        collectionRepository.save(nijigahraCollection);
        bookRepository.saveAll(nijigahraBookList);
        collectionAuthorRepository.save(asanoCollectionAuthor1);

        //Collection A Girl on the Shore

        Collection shoreCollection = new Collection();

        CollectionAuthor asanoCollectionAuthor2 = new CollectionAuthor(shoreCollection, inioAsano);
        asanoCollectionAuthorList.add(asanoCollectionAuthor2);

        inioAsano.setCollections(asanoCollectionAuthorList);

        shoreCollection.setName("A Girl on the Shore");
        shoreCollection.setAuthor(asanoCollectionAuthorList);
        shoreCollection.setPublisher(milkyWayEdiciones);
        shoreCollection.setDemography(seinen);
        shoreCollection.setPlot("Koume Sato and Keisuke Isobe are two teenagers caught between innocence and emotional awakening. Through their intimate encounters, they explore the complicated nature of love, pain, and longing, while grappling with their own problems and secrets in a coastal setting.");
        setCollectionImage(shoreCollection, "static/misc/covers/la_chica_a_la_orilla_del_mar.jpg");

        List<Book> shoreBookList = new ArrayList<>();

        Book shoreBook1 = new Book(new Date(1390518000000L), false, 194, 1, shoreCollection);
        shoreBookList.add(shoreBook1);
        Book shoreBook2 = new Book(new Date(1395183600000L), false, 216, 2, shoreCollection);
        shoreBookList.add(shoreBook2);

        shoreCollection.setBooks(shoreBookList);

        collectionRepository.save(shoreCollection);
        bookRepository.saveAll(shoreBookList);
        collectionAuthorRepository.save(asanoCollectionAuthor2);

        //Collection Solanin

        Collection solaninCollection = new Collection();

        CollectionAuthor asanoCollectionAuthor3 = new CollectionAuthor(solaninCollection, inioAsano);
        asanoCollectionAuthorList.add(asanoCollectionAuthor3);

        inioAsano.setCollections(asanoCollectionAuthorList);

        solaninCollection.setName("Solanin");
        solaninCollection.setAuthor(asanoCollectionAuthorList);
        solaninCollection.setPublisher(normaEditorial);
        solaninCollection.setDemography(seinen);
        solaninCollection.setPlot("Meiko Inoue is a young office worker dissatisfied with her job and her daily life. He decides to quit and look for something more meaningful, but soon realizes that the path to self-discovery is not easy. Together with her boyfriend Naruo, who dreams of being a musician, and her friends, she explores love, loss, and the importance of friendship.");
        setCollectionImage(solaninCollection, "static/misc/covers/solanin.jpg");

        List<Book> solaninBookList = new ArrayList<>();

        Book solaninBook1 = new Book(new Date(1548370800000L), false, 468, 1, solaninCollection);
        solaninBookList.add(solaninBook1);

        solaninCollection.setBooks(solaninBookList);

        collectionRepository.save(solaninCollection);
        bookRepository.saveAll(solaninBookList);
        collectionAuthorRepository.save(asanoCollectionAuthor3);

        // Collection Soloist in a Cage

        Collection soloistCollection = new Collection();
        Author soloistAuthor = new Author();

        List<CollectionAuthor> soloistCollectionAuthorList = new ArrayList<>();
        CollectionAuthor soloistCollectionAuthor = new CollectionAuthor(soloistCollection, soloistAuthor);
        soloistCollectionAuthorList.add(soloistCollectionAuthor);

        soloistAuthor.setName("Shiro Moriya");
        soloistAuthor.setCollections(soloistCollectionAuthorList);

        soloistCollection.setName("Soloist in a Cage");
        soloistCollection.setAuthor(soloistCollectionAuthorList);
        soloistCollection.setPublisher(milkyWayEdiciones);
        soloistCollection.setDemography(shonen);
        soloistCollection.setPlot("Chloe, a young woman, grows up in a high-security prison, where violence and despotism are common. She tries to protect her little brother as she fights to escape this brutal environment and discover a world beyond the prison walls.");
        setCollectionImage(soloistCollection, "static/misc/covers/soloist_in_a_cage.jpg");

        List<Book> soloistBookList = new ArrayList<>();

        Book soloistBook1 = new Book(new Date(1670194800000L), false, 248, 1, soloistCollection);
        soloistBookList.add(soloistBook1);
        Book soloistBook2 = new Book(new Date(1677538800000L), false, 240, 2, soloistCollection);
        soloistBookList.add(soloistBook2);
        Book soloistBook3 = new Book(new Date(1682546400000L), false, 240, 3, soloistCollection);
        soloistBookList.add(soloistBook3);

        soloistCollection.setBooks(soloistBookList);

        authorRepository.save(soloistAuthor);
        collectionRepository.save(soloistCollection);
        bookRepository.saveAll(soloistBookList);
        collectionAuthorRepository.save(soloistCollectionAuthor);


        // Collection Look Back

        Collection lookBackCollection = new Collection();
        Author tatsukiFujimoto = new Author();

        List<CollectionAuthor> tatsukiFujimotoCollectionAuthorList = new ArrayList<>();
        CollectionAuthor tatsukiFujimotoCollectionAuthor = new CollectionAuthor(lookBackCollection, tatsukiFujimoto);
        tatsukiFujimotoCollectionAuthorList.add(tatsukiFujimotoCollectionAuthor);

        tatsukiFujimoto.setName("Tatsuki Fujimoto");
        tatsukiFujimoto.setCollections(tatsukiFujimotoCollectionAuthorList);

        lookBackCollection.setName("Look Back");
        lookBackCollection.setAuthor(tatsukiFujimotoCollectionAuthorList);
        lookBackCollection.setPublisher(normaEditorial);
        lookBackCollection.setDemography(shonen);
        lookBackCollection.setPlot("The story follows Fujino and Kyomoto, two girls with very different artistic abilities, but whose paths cross in their childhood and develop a strong relationship over the years. As they grow, their choices and dreams take them on different paths, but their emotional connection remains deep and meaningful.");
        setCollectionImage(lookBackCollection, "static/misc/covers/look_back.jpg");

        List<Book> lookBackBookList = new ArrayList<>();

        Book lookBackBook1 = new Book(new Date(1649368800000L), false, 160, 1, lookBackCollection);
        lookBackBookList.add(lookBackBook1);

        lookBackCollection.setBooks(lookBackBookList);

        authorRepository.save(tatsukiFujimoto);
        collectionRepository.save(lookBackCollection);
        bookRepository.saveAll(lookBackBookList);
        collectionAuthorRepository.save(tatsukiFujimotoCollectionAuthor);

        // Collection Fire Punch

        Collection firePunchCollection = new Collection();

        CollectionAuthor tatsukiFujimotoCollectionAuthor1 = new CollectionAuthor(firePunchCollection, tatsukiFujimoto);
        tatsukiFujimotoCollectionAuthorList.add(tatsukiFujimotoCollectionAuthor1);

        tatsukiFujimoto.setCollections(tatsukiFujimotoCollectionAuthorList);

        firePunchCollection.setName("Fire Punch");
        firePunchCollection.setAuthor(tatsukiFujimotoCollectionAuthorList);
        firePunchCollection.setPublisher(normaEditorial);
        firePunchCollection.setDemography(shonen);
        firePunchCollection.setPlot("In a world frozen by a witch, people with special abilities are known as 'Blessed'. Agni, one of them, is able to regenerate his body despite being enveloped in flames that do not go out. After a tragedy caused by another Blessed One, Agni seeks revenge in a story of pain, redemption and hope.");
        setCollectionImage(firePunchCollection, "static/misc/covers/fire_punch.jpg");

        List<Book> firePunchBookList = new ArrayList<>();

        Book firePunchBook1 = new Book(new Date(1481846400000L), false, 200, 1, firePunchCollection);
        firePunchBookList.add(firePunchBook1);
        Book firePunchBook2 = new Book(new Date(1489612800000L), false, 200, 2, firePunchCollection);
        firePunchBookList.add(firePunchBook2);
        Book firePunchBook3 = new Book(new Date(1497379200000L), false, 200, 3, firePunchCollection);
        firePunchBookList.add(firePunchBook3);
        Book firePunchBook4 = new Book(new Date(1505145600000L), false, 200, 4, firePunchCollection);
        firePunchBookList.add(firePunchBook4);
        Book firePunchBook5 = new Book(new Date(1512912000000L), false, 200, 5, firePunchCollection);
        firePunchBookList.add(firePunchBook5);
        Book firePunchBook6 = new Book(new Date(1520678400000L), false, 200, 6, firePunchCollection);
        firePunchBookList.add(firePunchBook6);
        Book firePunchBook7 = new Book(new Date(1528444800000L), false, 200, 7, firePunchCollection);
        firePunchBookList.add(firePunchBook7);
        Book firePunchBook8 = new Book(new Date(1536211200000L), false, 200, 8, firePunchCollection);
        firePunchBookList.add(firePunchBook8);

        firePunchCollection.setBooks(firePunchBookList);

        collectionRepository.save(firePunchCollection);
        bookRepository.saveAll(firePunchBookList);
        collectionAuthorRepository.save(tatsukiFujimotoCollectionAuthor1);

        //Collection Goodbye Eri
        Collection goodbyeEriCollection = new Collection();

        CollectionAuthor tatsukiFujimotoCollectionAuthor2 = new CollectionAuthor(goodbyeEriCollection, tatsukiFujimoto);
        tatsukiFujimotoCollectionAuthorList.add(tatsukiFujimotoCollectionAuthor2);

        tatsukiFujimoto.setCollections(tatsukiFujimotoCollectionAuthorList);

        goodbyeEriCollection.setName("Goodbye, Eri");
        goodbyeEriCollection.setAuthor(tatsukiFujimotoCollectionAuthorList);
        goodbyeEriCollection.setPublisher(normaEditorial);
        goodbyeEriCollection.setDemography(shonen);
        goodbyeEriCollection.setPlot("Yuta Ito films his mother's final days as part of her wish to create a documentary of her life. After her death, Yuta's film creates controversy, leading him to meet Eri, a mysterious girl who shares his love of movies. Together, they explore the boundaries of storytelling and reality, uncovering profound truths about memory, loss, and acceptance.");
        setCollectionImage(goodbyeEriCollection, "static/misc/covers/goodbye_eri.jpg");

        List<Book> goodbyeEriBookList = new ArrayList<>();

        Book goodbyeEriBook1 = new Book(new Date(1649462400000L), false, 200, 1, goodbyeEriCollection); // single volume
        goodbyeEriBookList.add(goodbyeEriBook1);

        goodbyeEriCollection.setBooks(goodbyeEriBookList);

        collectionRepository.save(goodbyeEriCollection);
        bookRepository.saveAll(goodbyeEriBookList);
        collectionAuthorRepository.save(tatsukiFujimotoCollectionAuthor2);

        //Collection Jigokuraku

        Collection jigokurakuCollection = new Collection();
        Author yujiKaku = new Author();

        CollectionAuthor yujiKakuCollectionAuthor = new CollectionAuthor(jigokurakuCollection, yujiKaku);
        List<CollectionAuthor> yujiKakuCollectionAuthorList = new ArrayList<>();
        yujiKakuCollectionAuthorList.add(yujiKakuCollectionAuthor);

        yujiKaku.setName("Yûji Kaku");
        yujiKaku.setCollections(yujiKakuCollectionAuthorList);

        jigokurakuCollection.setName("Jigokuraku");
        jigokurakuCollection.setAuthor(yujiKakuCollectionAuthorList);
        jigokurakuCollection.setPublisher(normaEditorial);
        jigokurakuCollection.setDemography(shonen);
        jigokurakuCollection.setPlot("In a world frozen by a witch, people with special abilities are known as 'Blessed'. Agni, one of them, is able to regenerate his body despite being enveloped in flames that do not go out. After a tragedy caused by another Blessed One, Agni seeks revenge in a story of pain, redemption and hope.");
        setCollectionImage(jigokurakuCollection, "static/misc/covers/jigokuraku.jpg");

        List<Book> jigokurakuBookList = new ArrayList<>();

        Book jigokurakuBook1 = new Book(new Date(1546300800000L), false, 200, 1, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook1);
        Book jigokurakuBook2 = new Book(new Date(1554076800000L), false, 200, 2, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook2);
        Book jigokurakuBook3 = new Book(new Date(1561843200000L), false, 200, 3, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook3);
        Book jigokurakuBook4 = new Book(new Date(1569614400000L), false, 200, 4, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook4);
        Book jigokurakuBook5 = new Book(new Date(1577385600000L), false, 200, 5, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook5);
        Book jigokurakuBook6 = new Book(new Date(1585156800000L), false, 200, 6, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook6);
        Book jigokurakuBook7 = new Book(new Date(1592928000000L), false, 200, 7, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook7);
        Book jigokurakuBook8 = new Book(new Date(1600699200000L), false, 200, 8, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook8);
        Book jigokurakuBook9 = new Book(new Date(1608470400000L), false, 200, 9, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook9);
        Book jigokurakuBook10 = new Book(new Date(1616241600000L), false, 200, 10, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook10);
        Book jigokurakuBook11 = new Book(new Date(1626241600000L), false, 200, 11, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook11);
        Book jigokurakuBook12 = new Book(new Date(1636241600000L), false, 200, 12, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook12);
        Book jigokurakuBook13 = new Book(new Date(1646241600000L), false, 200, 13, jigokurakuCollection);
        jigokurakuBookList.add(jigokurakuBook13);

        jigokurakuCollection.setBooks(jigokurakuBookList);

        authorRepository.save(yujiKaku);
        collectionRepository.save(jigokurakuCollection);
        bookRepository.saveAll(jigokurakuBookList);
        collectionAuthorRepository.save(yujiKakuCollectionAuthor);

        Collection ayashimonCollection = new Collection();

        //Collection Ayashimon

        CollectionAuthor yujiKakuCollectionAuthor1 = new CollectionAuthor(ayashimonCollection, yujiKaku);
        yujiKakuCollectionAuthorList.add(yujiKakuCollectionAuthor1);

        yujiKaku.setCollections(yujiKakuCollectionAuthorList);

        ayashimonCollection.setName("Ayashimon");
        ayashimonCollection.setAuthor(yujiKakuCollectionAuthorList);
        ayashimonCollection.setPublisher(normaEditorial);
        ayashimonCollection.setDemography(shonen);
        ayashimonCollection.setPlot("Maruo Kaido is a young man with extraordinary strength and a passion for fighting. When he crosses paths with Urara, a member of the supernatural 'Ayashimon' gang, he finds himself caught in the middle of a battle between yokai and human factions. With Urara's guidance, Maruo discovers a world filled with mythical creatures and begins a journey that will test his strength and resolve.");
        setCollectionImage(ayashimonCollection, "static/misc/covers/ayashimon.jpg");

        List<Book> ayashimonBookList = new ArrayList<>();

        Book ayashimonBook1 = new Book(new Date(1633046400000L), false, 200, 1, ayashimonCollection);
        ayashimonBookList.add(ayashimonBook1);
        Book ayashimonBook2 = new Book(new Date(1640812800000L), false, 200, 2, ayashimonCollection);
        ayashimonBookList.add(ayashimonBook2);
        Book ayashimonBook3 = new Book(new Date(1648579200000L), false, 200, 3, ayashimonCollection);
        ayashimonBookList.add(ayashimonBook3);

        ayashimonCollection.setBooks(ayashimonBookList);

        collectionRepository.save(ayashimonCollection);
        bookRepository.saveAll(ayashimonBookList);
        collectionAuthorRepository.save(yujiKakuCollectionAuthor1);

        //Collection Kimetsu no Yaiba

        Collection kimetsuCollection = new Collection();
        Author koyoharuGotouge = new Author();

        CollectionAuthor koyoharuGotougeCollectionAuthor = new CollectionAuthor(kimetsuCollection, koyoharuGotouge);
        List<CollectionAuthor> koyoharuGotougeCollectionAuthorList = new ArrayList<>();
        koyoharuGotougeCollectionAuthorList.add(koyoharuGotougeCollectionAuthor);

        koyoharuGotouge.setName("Koyoharu Gotouge");
        koyoharuGotouge.setCollections(koyoharuGotougeCollectionAuthorList);

        kimetsuCollection.setName("Kimetsu no Yaiba");
        kimetsuCollection.setAuthor(koyoharuGotougeCollectionAuthorList);
        kimetsuCollection.setPublisher(normaEditorial);
        kimetsuCollection.setDemography(shonen);
        kimetsuCollection.setPlot("Tanjiro Kamado is a kind-hearted boy whose family is slaughtered by demons. When his sister Nezuko is turned into a demon, Tanjiro joins the Demon Slayer Corps to find a cure and avenge his family. Alongside other Demon Slayers, he battles powerful demons and learns the secrets of the Breath styles to become a formidable fighter.");
        setCollectionImage(kimetsuCollection, "static/misc/covers/kimetsu_no_yaiba.jpg");

        List<Book> kimetsuBookList = new ArrayList<>();

        long[] publicationDates = {
                1443657600000L, 1451606400000L, 1459363200000L, 1466976000000L,
                1474646400000L, 1482268800000L, 1489948800000L, 1497542400000L,
                1505212800000L, 1512835200000L, 1520467200000L, 1528089600000L,
                1535760000000L, 1543382400000L, 1550956800000L, 1558636800000L,
                1566268800000L, 1573852800000L, 1581523200000L, 1589145600000L,
                1596758400000L, 1604371200000L, 1612041600000L
        };

        for (int i = 1; i <= 23; i++) {
            Book book = new Book(new Date(publicationDates[i - 1]), false, 192, i, kimetsuCollection);
            kimetsuBookList.add(book);
        }

        kimetsuCollection.setBooks(kimetsuBookList);

        authorRepository.save(koyoharuGotouge);
        collectionRepository.save(kimetsuCollection);
        bookRepository.saveAll(kimetsuBookList);
        collectionAuthorRepository.save(koyoharuGotougeCollectionAuthor);

        //Collection Gyo

        Collection gyoCollection = new Collection();
        Author junjiIto = new Author();

        CollectionAuthor junjiItoCollectionAuthor = new CollectionAuthor(gyoCollection, junjiIto);
        List<CollectionAuthor> junjiItoCollectionAuthorList = new ArrayList<>();
        junjiItoCollectionAuthorList.add(junjiItoCollectionAuthor);

        junjiIto.setName("Junji Ito");
        junjiIto.setCollections(junjiItoCollectionAuthorList);

        gyoCollection.setName("Gyo");
        gyoCollection.setAuthor(junjiItoCollectionAuthorList);
        gyoCollection.setPublisher(eccEdiciones);
        gyoCollection.setDemography(seinen);
        gyoCollection.setPlot("A bizarre and deadly phenomenon occurs when fish with mechanical legs start emerging from the sea, spreading disease and death. Tadashi and his girlfriend Kaori are caught in this horrific event, fighting to survive as the world around them is plunged into chaos and decay.");
        setCollectionImage(gyoCollection, "static/misc/covers/gyo.jpg");

        List<Book> gyoBookList = new ArrayList<>();

        Book gyoBook1 = new Book(new Date(1009756800000L), false, 400, 1, gyoCollection);
        gyoBookList.add(gyoBook1);

        gyoCollection.setBooks(gyoBookList);

        authorRepository.save(junjiIto);
        collectionRepository.save(gyoCollection);
        bookRepository.saveAll(gyoBookList);
        collectionAuthorRepository.save(junjiItoCollectionAuthor);

        // Collection Uzumaki
        Collection uzumakiCollection = new Collection();

        CollectionAuthor junjiItoCollectionAuthor1 = new CollectionAuthor(uzumakiCollection, junjiIto);
        junjiItoCollectionAuthorList.add(junjiItoCollectionAuthor1);

        junjiIto.setCollections(junjiItoCollectionAuthorList);

        uzumakiCollection.setName("Uzumaki");
        uzumakiCollection.setAuthor(junjiItoCollectionAuthorList);
        uzumakiCollection.setPublisher(planetaComic);
        uzumakiCollection.setDemography(seinen);
        uzumakiCollection.setPlot("In the small town of Kurouzu-cho, strange spirals begin appearing in various forms, driving residents to madness and death. Kirie Goshima and her boyfriend Shuichi Saito attempt to understand and escape the spiral curse as it engulfs their town.");
        setCollectionImage(uzumakiCollection, "static/misc/covers/uzumaki.jpg");

        List<Book> uzumakiBookList = new ArrayList<>();

        Book uzumakiBook1 = new Book(new Date(902352000000L), false, 644, 1, uzumakiCollection);
        uzumakiBookList.add(uzumakiBook1);

        uzumakiCollection.setBooks(uzumakiBookList);

        collectionRepository.save(uzumakiCollection);
        bookRepository.saveAll(uzumakiBookList);
        collectionAuthorRepository.save(junjiItoCollectionAuthor1);


        // Collection Tomie
        Collection tomieCollection = new Collection();

        CollectionAuthor junjiItoCollectionAuthor2 = new CollectionAuthor(tomieCollection, junjiIto);
        List<CollectionAuthor> junjiItoCollectionAuthorList2 = new ArrayList<>();
        junjiItoCollectionAuthorList2.add(junjiItoCollectionAuthor2);

        junjiIto.setCollections(junjiItoCollectionAuthorList2);

        tomieCollection.setName("Tomie");
        tomieCollection.setAuthor(junjiItoCollectionAuthorList2);
        tomieCollection.setPublisher(eccEdiciones);
        tomieCollection.setDemography(seinen);
        tomieCollection.setPlot("Tomie is a beautiful girl with the uncanny ability to regenerate from any injury, even death. Her presence drives those around her to madness, jealousy, and murder. Each story in the series explores the chaos and terror she brings to those who encounter her.");
        setCollectionImage(tomieCollection, "static/misc/covers/tomie.jpg");

        List<Book> tomieBookList = new ArrayList<>();

        Book tomieBook1 = new Book(new Date(780480000000L), false, 752, 1, tomieCollection);
        tomieBookList.add(tomieBook1);

        tomieCollection.setBooks(tomieBookList);

        collectionRepository.save(tomieCollection);
        bookRepository.saveAll(tomieBookList);
        collectionAuthorRepository.save(junjiItoCollectionAuthor2);

        //Collection Hellstar Remina

        Collection reminaCollection = new Collection();

        CollectionAuthor junjiItoCollectionAuthor3 = new CollectionAuthor(reminaCollection, junjiIto);
        List<CollectionAuthor> junjiItoCollectionAuthorList3 = new ArrayList<>();
        junjiItoCollectionAuthorList3.add(junjiItoCollectionAuthor3);

        junjiIto.setCollections(junjiItoCollectionAuthorList3);

        reminaCollection.setName("Hellstar Remina");
        reminaCollection.setAuthor(junjiItoCollectionAuthorList3);
        reminaCollection.setPublisher(eccEdiciones);
        reminaCollection.setDemography(seinen);
        reminaCollection.setPlot("A mysterious planet appears from another dimension, heading toward Earth. As it moves through space, it devours everything in its path. The planet is named 'Remina' after the scientist's daughter who discovered it. But as the cosmic horror approaches Earth, the public turns against the girl, blaming her for the impending doom.");
        setCollectionImage(reminaCollection, "static/misc/covers/hellstar_remina.jpg");

        List<Book> reminaBookList = new ArrayList<>();

        Book reminaBook1 = new Book(new Date(1080777600000L), false, 296, 1, reminaCollection);
        reminaBookList.add(reminaBook1);

        reminaCollection.setBooks(reminaBookList);

        collectionRepository.save(reminaCollection);
        bookRepository.saveAll(reminaBookList);
        collectionAuthorRepository.save(junjiItoCollectionAuthor3);

        //Collection Homunculus

        Collection homunculusCollection = new Collection();
        Author hideoYamamoto = new Author();

        CollectionAuthor hideoYamamotoCollectionAuthor1 = new CollectionAuthor(homunculusCollection, hideoYamamoto);
        List<CollectionAuthor> hideoYamamotoCollectionAuthorList = new ArrayList<>();
        hideoYamamotoCollectionAuthorList.add(hideoYamamotoCollectionAuthor1);

        hideoYamamoto.setName("Hideo Yamomoto");
        hideoYamamoto.setCollections(hideoYamamotoCollectionAuthorList);

        homunculusCollection.setName("Homunculus");
        homunculusCollection.setAuthor(hideoYamamotoCollectionAuthorList);
        homunculusCollection.setPublisher(ivrea);
        homunculusCollection.setDemography(seinen);
        homunculusCollection.setPlot("Susumu Nakoshi, a homeless man living in his car, agrees to undergo a controversial surgical procedure called trepanation, which involves drilling a hole in his skull. The surgery awakens strange and horrifying visions of 'homunculi'—representations of people's psychological trauma. As Nakoshi navigates these visions, he is drawn into a dark and unsettling journey.");
        setCollectionImage(homunculusCollection, "static/misc/covers/homunculus.jpg");

        List<Book> homunculusBookList = new ArrayList<>();

        int homunculusVolumeCount = 15; // "Homunculus" has 15 volumes
        long homunculusStartPublicationDate = 1030838400000L; // First volume's publication date (example)
        long homunculusInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between each volume

        for (int i = 1; i <= homunculusVolumeCount; i++) {
            Book book = new Book(new Date(homunculusStartPublicationDate + (i - 1) * homunculusInterval), false, 200, i, homunculusCollection);
            homunculusBookList.add(book);
        }

        homunculusCollection.setBooks(homunculusBookList);

        authorRepository.save(hideoYamamoto);
        collectionRepository.save(homunculusCollection);
        bookRepository.saveAll(homunculusBookList);
        collectionAuthorRepository.save(hideoYamamotoCollectionAuthor1);

        //Naruto Collection

        Collection narutoCollection = new Collection();
        Author masashiKishimoto = new Author();

        CollectionAuthor masashiKishimotoCollectionAuthor1 = new CollectionAuthor(narutoCollection, masashiKishimoto);
        List<CollectionAuthor> masashiKishimotoCollectionAuthorList = new ArrayList<>();
        masashiKishimotoCollectionAuthorList.add(masashiKishimotoCollectionAuthor1);

        masashiKishimoto.setName("Masashi Kishimoto");
        masashiKishimoto.setCollections(masashiKishimotoCollectionAuthorList);

        narutoCollection.setName("Naruto");
        narutoCollection.setAuthor(masashiKishimotoCollectionAuthorList);
        narutoCollection.setPublisher(planetaComic);
        narutoCollection.setDemography(shonen);
        narutoCollection.setPlot("Naruto Uzumaki is a young ninja with a dream to become the Hokage, the strongest ninja and leader of his village. However, he carries the burden of having the Nine-Tails, a powerful demon fox, sealed within him. Along with his friends and mentors, Naruto embarks on a journey of growth, friendship, and determination, facing numerous challenges and enemies.");
        setCollectionImage(narutoCollection, "static/misc/covers/naruto.jpg");

        List<Book> narutoBookList = new ArrayList<>();

        int narutoVolumeCount = 72; // "Naruto" has 72 volumes
        long narutoStartPublicationDate = 963072000000L; // First volume's publication date (example)
        long narutoInterval = 4 * 30 * 24 * 60 * 60 * 1000L; // Roughly four months between each volume

        for (int i = 1; i <= narutoVolumeCount; i++) {
            Book book = new Book(new Date(narutoStartPublicationDate + (i - 1) * narutoInterval), false, 200, i, narutoCollection);
            narutoBookList.add(book);
        }

        narutoCollection.setBooks(narutoBookList);

        authorRepository.save(masashiKishimoto);
        collectionRepository.save(narutoCollection);
        bookRepository.saveAll(narutoBookList);
        collectionAuthorRepository.save(masashiKishimotoCollectionAuthor1);

        //Collection 20th Century Boys

        Collection centuryBoysCollection = new Collection();
        Author naokiUrasawa = new Author();

        CollectionAuthor naokiUrasawaCollectionAuthor = new CollectionAuthor(centuryBoysCollection, naokiUrasawa);
        List<CollectionAuthor> naokiUrasawaCollectionAuthorList = new ArrayList<>();
        naokiUrasawaCollectionAuthorList.add(naokiUrasawaCollectionAuthor);

        naokiUrasawa.setName("Naoki Urasawa");
        naokiUrasawa.setCollections(naokiUrasawaCollectionAuthorList);

        centuryBoysCollection.setName("20th Century Boys");
        centuryBoysCollection.setAuthor(naokiUrasawaCollectionAuthorList);
        centuryBoysCollection.setPublisher(planetaComic);
        centuryBoysCollection.setDemography(seinen);
        centuryBoysCollection.setPlot("Kenji Endo and his childhood friends realize that a series of catastrophic events, including a mysterious cult and its leader known as 'Friend,' are connected to a story they created as kids. As they unravel the secrets of 'Friend,' they must confront their past and prevent a terrifying apocalypse.");
        setCollectionImage(centuryBoysCollection, "static/misc/covers/20th_century_boys.jpg");

        List<Book> centuryBoysBookList = new ArrayList<>();

        int centuryVolumeCount = 22; // "20th Century Boys" has 22 volumes
        long centuryStartPublicationDate = 899971200000L; // First volume's publication date (example)
        long centuryInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between each volume

        for (int i = 1; i <= centuryVolumeCount; i++) {
            Book book = new Book(new Date(centuryStartPublicationDate + (i - 1) * centuryInterval), false, 200, i, centuryBoysCollection);
            centuryBoysBookList.add(book);
        }

        centuryBoysCollection.setBooks(centuryBoysBookList);

        authorRepository.save(naokiUrasawa);
        collectionRepository.save(centuryBoysCollection);
        bookRepository.saveAll(centuryBoysBookList);
        collectionAuthorRepository.save(naokiUrasawaCollectionAuthor);

        // Collection Master Keaton
        Collection masterKeatonCollection = new Collection();

        CollectionAuthor naokiUrasawaCollectionAuthor1 = new CollectionAuthor(masterKeatonCollection, naokiUrasawa);
        naokiUrasawaCollectionAuthorList.add(naokiUrasawaCollectionAuthor1);

        naokiUrasawa.setCollections(naokiUrasawaCollectionAuthorList);

        masterKeatonCollection.setName("Master Keaton");
        masterKeatonCollection.setAuthor(naokiUrasawaCollectionAuthorList);
        masterKeatonCollection.setPublisher(planetaComic);
        masterKeatonCollection.setDemography(seinen);
        masterKeatonCollection.setPlot("Taichi Hiraga Keaton, a former SAS operative and insurance investigator, uses his skills and knowledge to solve complex cases around the world. Each story combines Keaton's archaeological expertise with his military training, creating a mix of mystery, adventure, and suspense.");
        setCollectionImage(masterKeatonCollection, "static/misc/covers/master_keaton.jpg");

        List<Book> masterKeatonBookList = new ArrayList<>();

        int masterKeatonVolumeCount = 18; // Master Keaton has 18 volumes
        long masterKeatonStartPublicationDate = 696384000000L; // First volume's publication date (example)
        long masterKeatonInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= masterKeatonVolumeCount; i++) {
            Book book = new Book(new Date(masterKeatonStartPublicationDate + (i - 1) * masterKeatonInterval), false, 200, i, masterKeatonCollection);
            masterKeatonBookList.add(book);
        }

        masterKeatonCollection.setBooks(masterKeatonBookList);

        collectionRepository.save(masterKeatonCollection);
        bookRepository.saveAll(masterKeatonBookList);
        collectionAuthorRepository.save(naokiUrasawaCollectionAuthor1);


        // Collection Monster
        Collection monsterCollection = new Collection();

        CollectionAuthor naokiUrasawaCollectionAuthor2 = new CollectionAuthor(monsterCollection, naokiUrasawa);
        naokiUrasawaCollectionAuthorList.add(naokiUrasawaCollectionAuthor2);

        naokiUrasawa.setCollections(naokiUrasawaCollectionAuthorList);

        monsterCollection.setName("Monster");
        monsterCollection.setAuthor(naokiUrasawaCollectionAuthorList);
        monsterCollection.setPublisher(planetaComic);
        monsterCollection.setDemography(seinen);
        monsterCollection.setPlot("Dr. Kenzo Tenma, a skilled neurosurgeon, saves the life of a young boy named Johan Liebert, only to discover that Johan grows up to be a brilliant yet twisted psychopath. As Tenma chases Johan across Europe to stop his reign of terror, he uncovers a tangled web of mystery, ethics, and morality.");
        setCollectionImage(monsterCollection, "static/misc/covers/monster.jpg");

        List<Book> monsterBookList = new ArrayList<>();

        int monsterVolumeCount = 18; // Monster has 18 volumes
        long monsterStartPublicationDate = 765072000000L; // First volume's publication date (example)
        long monsterInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= monsterVolumeCount; i++) {
            Book book = new Book(new Date(monsterStartPublicationDate + (i - 1) * monsterInterval), false, 200, i, monsterCollection);
            monsterBookList.add(book);
        }

        monsterCollection.setBooks(monsterBookList);

        collectionRepository.save(monsterCollection);
        bookRepository.saveAll(monsterBookList);
        collectionAuthorRepository.save(naokiUrasawaCollectionAuthor2);


        // Collection Billy Bat
        Collection billyBatCollection = new Collection();

        CollectionAuthor naokiUrasawaCollectionAuthor3 = new CollectionAuthor(billyBatCollection, naokiUrasawa);
        naokiUrasawaCollectionAuthorList.add(naokiUrasawaCollectionAuthor3);

        naokiUrasawa.setCollections(naokiUrasawaCollectionAuthorList);

        billyBatCollection.setName("Billy Bat");
        billyBatCollection.setAuthor(naokiUrasawaCollectionAuthorList);
        billyBatCollection.setPublisher(planetaComic);
        billyBatCollection.setDemography(seinen);
        billyBatCollection.setPlot("Kevin Yamagata, a Japanese-American comic book artist, discovers that his character Billy Bat may not be his own creation. As he digs deeper into the mystery of Billy Bat, he unravels a conspiracy that spans centuries and leads him into a complex world of espionage, secret societies, and alternate histories.");
        setCollectionImage(billyBatCollection, "static/misc/covers/billy_bat.jpg");

        List<Book> billyBatBookList = new ArrayList<>();

        int billyBatVolumeCount = 20; // Billy Bat has 20 volumes
        long billyBatStartPublicationDate = 1262304000000L; // First volume's publication date (example)
        long billyBatInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= billyBatVolumeCount; i++) {
            Book book = new Book(new Date(billyBatStartPublicationDate + (i - 1) * billyBatInterval), false, 200, i, billyBatCollection);
            billyBatBookList.add(book);
        }

        billyBatCollection.setBooks(billyBatBookList);

        collectionRepository.save(billyBatCollection);
        bookRepository.saveAll(billyBatBookList);
        collectionAuthorRepository.save(naokiUrasawaCollectionAuthor3);

        //Collection Parasyte

        Collection parasyteCollection = new Collection();
        Author hitoshiIwaaki = new Author();

        CollectionAuthor hitoshiIwaakiCollectionAuthor1 = new CollectionAuthor(parasyteCollection, hitoshiIwaaki);
        List<CollectionAuthor> hitoshiIwaakiCollectionAuthorList = new ArrayList<>();
        hitoshiIwaakiCollectionAuthorList.add(hitoshiIwaakiCollectionAuthor1);

        hitoshiIwaaki.setName("Hitoshi Iwaaki");
        hitoshiIwaaki.setCollections(hitoshiIwaakiCollectionAuthorList);

        parasyteCollection.setName("Parasyte");
        parasyteCollection.setAuthor(hitoshiIwaakiCollectionAuthorList);
        parasyteCollection.setPublisher(planetaComic);
        parasyteCollection.setDemography(seinen);
        parasyteCollection.setPlot("Shinichi Izumi, a high school student, becomes the host to a parasitic alien named Migi. While most parasites aim to take over their hosts' brains, Migi is limited to Shinichi's right hand. Together, they must navigate a world where other parasites seek to eliminate humans, balancing survival, ethics, and the struggle for humanity.");
        setCollectionImage(parasyteCollection, "static/misc/covers/parasyte.jpg");

        List<Book> parasyteBookList = new ArrayList<>();

        int parasyteVolumeCount = 10; // "Parasyte" has 10 volumes
        long parasyteStartPublicationDate = 592416000000L; // First volume's publication date (example)
        long parasyteInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= parasyteVolumeCount; i++) {
            Book book = new Book(new Date(parasyteStartPublicationDate + (i - 1) * parasyteInterval), false, 200, i, parasyteCollection);
            parasyteBookList.add(book);
        }

        parasyteCollection.setBooks(parasyteBookList);

        authorRepository.save(hitoshiIwaaki);
        collectionRepository.save(parasyteCollection);
        bookRepository.saveAll(parasyteBookList);
        collectionAuthorRepository.save(hitoshiIwaakiCollectionAuthor1);

        //Collection Haikyuu

        Collection haikyuuCollection = new Collection();
        Author haruichiFurudate = new Author();

        CollectionAuthor haruichiFurudateCollectionAuthor1 = new CollectionAuthor(haikyuuCollection, haruichiFurudate);
        List<CollectionAuthor> haruichiFurudateCollectionAuthorList = new ArrayList<>();
        haruichiFurudateCollectionAuthorList.add(haruichiFurudateCollectionAuthor1);

        haruichiFurudate.setName("Haruichi Furudate");
        haruichiFurudate.setCollections(haruichiFurudateCollectionAuthorList);

        haikyuuCollection.setName("Haikyuu!!");
        haikyuuCollection.setAuthor(haruichiFurudateCollectionAuthorList);
        haikyuuCollection.setPublisher(planetaComic);
        haikyuuCollection.setDemography(shonen);
        haikyuuCollection.setPlot("Shoyo Hinata, a high school student with a passion for volleyball, dreams of becoming a top player despite his short stature. He joins the Karasuno High School volleyball team, where he forms a unique partnership with the talented but temperamental Kageyama. Together, they face rival teams, build teamwork, and aim to reach the national championships.");
        setCollectionImage(haikyuuCollection, "static/misc/covers/haikyuu.jpg");

        List<Book> haikyuuBookList = new ArrayList<>();

        int volumeCount = 45; // "Haikyuu!!" has 45 volumes
        long startPublicationDate = 1338508800000L; // First volume's publication date (example)
        long interval = 3 * 30 * 24 * 60 * 60 * 1000L; // Roughly four months between volumes

        for (int i = 1; i <= volumeCount; i++) {
            Book book = new Book(new Date(startPublicationDate + (i - 1) * interval), false, 200, i, haikyuuCollection);
            haikyuuBookList.add(book);
        }

        haikyuuCollection.setBooks(haikyuuBookList);

        authorRepository.save(haruichiFurudate);
        collectionRepository.save(haikyuuCollection);
        bookRepository.saveAll(haikyuuBookList);
        collectionAuthorRepository.save(haruichiFurudateCollectionAuthor1);

        //Collection SNK
        Collection shingekiCollection = new Collection();
        Author hajimeIsayama = new Author();

// Author setup
        CollectionAuthor hajimeIsayamaCollectionAuthor1 = new CollectionAuthor(shingekiCollection, hajimeIsayama);
        List<CollectionAuthor> hajimeIsayamaCollectionAuthorList = new ArrayList<>();
        hajimeIsayamaCollectionAuthorList.add(hajimeIsayamaCollectionAuthor1);

        hajimeIsayama.setName("Hajime Isayama");
        hajimeIsayama.setCollections(hajimeIsayamaCollectionAuthorList);

        shingekiCollection.setName("Attack on Titan");
        shingekiCollection.setAuthor(hajimeIsayamaCollectionAuthorList);
        shingekiCollection.setPublisher(normaEditorial);
        shingekiCollection.setDemography(shonen);
        shingekiCollection.setPlot("Eren Yeager lives in a world where humanity is confined within enormous walls to protect themselves from Titans, massive humanoid creatures that devour people. After a Titan attack destroys his home and kills his mother, Eren vows to exterminate all Titans. Along with his friends Mikasa and Armin, he joins the Survey Corps to uncover the truth about the Titans and reclaim freedom for humanity.");
        setCollectionImage(shingekiCollection, "static/misc/covers/shingeki_no_kyojin.jpg");

        List<Book> shingekiBookList = new ArrayList<>();

        int snkVolumeCount = 34; // "Shingeki no Kyojin" has 34 volumes
        long snkStartPublicationDate = 1266278400000L; // First volume's publication date (example)
        long snkInterval = 4 * 30 * 24 * 60 * 60 * 1000L; // Roughly four months between volumes

        for (int i = 1; i <= snkVolumeCount; i++) {
            Book book = new Book(new Date(snkStartPublicationDate + (i - 1) * snkInterval), false, 200, i, shingekiCollection);
            shingekiBookList.add(book);
        }

        shingekiCollection.setBooks(shingekiBookList);

        authorRepository.save(hajimeIsayama);
        collectionRepository.save(shingekiCollection);
        bookRepository.saveAll(shingekiBookList);
        collectionAuthorRepository.save(hajimeIsayamaCollectionAuthor1);

        //Collection FMA
        Collection fmaCollection = new Collection();
        Author hiromuArakawa =  new Author();

        CollectionAuthor hiromuArakawaCollectionAuthor1 = new CollectionAuthor(fmaCollection, hiromuArakawa);
        List<CollectionAuthor> hiromuArakawaCollectionAuthorList = new ArrayList<>();
        hiromuArakawaCollectionAuthorList.add(hiromuArakawaCollectionAuthor1);

        hiromuArakawa.setName("Hiromu Arakawa");
        hiromuArakawa.setCollections(hiromuArakawaCollectionAuthorList);

        fmaCollection.setName("Fullmetal Alchemist");
        fmaCollection.setAuthor(hiromuArakawaCollectionAuthorList);
        fmaCollection.setPublisher(normaEditorial);
        fmaCollection.setDemography(shonen);
        fmaCollection.setPlot("Edward and Alphonse Elric are two brothers who practice alchemy. After a failed attempt to bring their mother back to life using alchemy, they suffer tragic consequences: Edward loses a leg, while Alphonse loses his entire body, his soul bound to a suit of armor. To regain what they've lost, they seek the Philosopher's Stone, a legendary artifact with immense power.");
        setCollectionImage(fmaCollection, "static/misc/covers/fullmetal_alchemist.jpg");

        List<Book> fmaBookList = new ArrayList<>();

        int fmaVolumeCount = 27; // "Fullmetal Alchemist" has 27 volumes
        long fmaStartPublicationDate = 1017638400000L; // First volume's publication date (example)
        long fmaInterval = 4 * 30 * 24 * 60 * 60 * 1000L; // Roughly four months between volumes

        for (int i = 1; i <= fmaVolumeCount; i++) {
            Book book = new Book(new Date(fmaStartPublicationDate + (i - 1) * fmaInterval), false, 200, i, fmaCollection);
            fmaBookList.add(book);
        }

        fmaCollection.setBooks(fmaBookList);

        authorRepository.save(hiromuArakawa);
        collectionRepository.save(fmaCollection);
        bookRepository.saveAll(fmaBookList);
        collectionAuthorRepository.save(hiromuArakawaCollectionAuthor1);

        //Collection Soul Eater
        Collection soulEaterCollection = new Collection();
        Author atsushiOhkubo = new Author();

        CollectionAuthor atsushiOhkuboCollectionAuthor1 = new CollectionAuthor(soulEaterCollection, atsushiOhkubo);
        List<CollectionAuthor> atsushiOhkuboCollectionAuthorList = new ArrayList<>();
        atsushiOhkuboCollectionAuthorList.add(atsushiOhkuboCollectionAuthor1);

        atsushiOhkubo.setName("Atsushi Ohkubo");
        atsushiOhkubo.setCollections(atsushiOhkuboCollectionAuthorList);

        soulEaterCollection.setName("Soul Eater");
        soulEaterCollection.setAuthor(atsushiOhkuboCollectionAuthorList);
        soulEaterCollection.setPublisher(normaEditorial);
        soulEaterCollection.setDemography(shonen);
        soulEaterCollection.setPlot("In the Death Weapon Meister Academy, students train to become Death Scythes—powerful weapons for Death, the Shinigami himself. The story follows Maka Albarn, a meister, and her partner Soul Eater, a living scythe. Together, they work to collect the souls of evil beings and prevent the resurrection of a dangerous witch. The series explores the relationships between meisters and their weapons, as well as the challenges they face in their journey to become Death Scythes.");
        setCollectionImage(soulEaterCollection, "static/misc/covers/soul_eater.jpg");

        List<Book> soulEaterBookList = new ArrayList<>();

        int seVolumeCount = 25; // "Soul Eater" has 25 volumes
        long seStartPublicationDate = 1057017600000L; // First volume's publication date (example)
        long seInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= seVolumeCount; i++) {
            Book book = new Book(new Date(seStartPublicationDate + (i - 1) * seInterval), false, 200, i, soulEaterCollection);
            soulEaterBookList.add(book);
        }

        soulEaterCollection.setBooks(soulEaterBookList);

        authorRepository.save(atsushiOhkubo);
        collectionRepository.save(soulEaterCollection);
        bookRepository.saveAll(soulEaterBookList);
        collectionAuthorRepository.save(atsushiOhkuboCollectionAuthor1);

        //Collection The Promised Neverland
        Collection promisedNeverlandCollection = new Collection();
        Author kaiuShirai = new Author();
        Author posukaDemizu = new Author();

        List<CollectionAuthor> promisedNeverlandCollectionAuthorList = new ArrayList<>();
        CollectionAuthor kaiuShiraiCollectionAuthor = new CollectionAuthor(promisedNeverlandCollection, kaiuShirai);
        promisedNeverlandCollectionAuthorList.add(kaiuShiraiCollectionAuthor);
        CollectionAuthor posukaDemizuCollectionAuthor = new CollectionAuthor(promisedNeverlandCollection, posukaDemizu);
        promisedNeverlandCollectionAuthorList.add(posukaDemizuCollectionAuthor);

        kaiuShirai.setName("Kaiu Shirai");
        kaiuShirai.setCollections(promisedNeverlandCollectionAuthorList);
        posukaDemizu.setName("Posuka Demizu");
        posukaDemizu.setCollections(promisedNeverlandCollectionAuthorList);

        promisedNeverlandCollection.setName("The Promised Neverland");
        promisedNeverlandCollection.setAuthor(promisedNeverlandCollectionAuthorList);
        promisedNeverlandCollection.setPublisher(normaEditorial);
        promisedNeverlandCollection.setDemography(shonen);
        promisedNeverlandCollection.setPlot("Emma, Ray, and Norman are the brightest children in Grace Field House, an orphanage run by the kind and caring 'Mom' Isabella. However, they discover a horrifying secret: the orphanage is a front for raising children as livestock for demons. Determined to escape, the trio devises a plan to free themselves and their friends, leading to a tense and gripping story of survival and bravery.");
        setCollectionImage(promisedNeverlandCollection, "static/misc/covers/the_promised_neverland.jpg");

        List<Book> promisedNeverlandBookList = new ArrayList<>();

        int pnVolumeCount = 20; // "The Promised Neverland" has 20 volumes
        long pnStartPublicationDate = 1477872000000L; // First volume's publication date (example)
        long pnInterval = 4 * 30 * 24 * 60 * 60 * 1000L; // Roughly four months between volumes

        for (int i = 1; i <= pnVolumeCount; i++) {
            Book book = new Book(new Date(pnStartPublicationDate + (i - 1) * pnInterval), false, 200, i, promisedNeverlandCollection);
            promisedNeverlandBookList.add(book);
        }

        promisedNeverlandCollection.setBooks(promisedNeverlandBookList);

        authorRepository.save(kaiuShirai);
        authorRepository.save(posukaDemizu);
        collectionRepository.save(promisedNeverlandCollection);
        bookRepository.saveAll(promisedNeverlandBookList);
        collectionAuthorRepository.save(kaiuShiraiCollectionAuthor);
        collectionAuthorRepository.save(posukaDemizuCollectionAuthor);

        //Collection Dorohedoro
        Collection dorohedoroCollection = new Collection();
        Author qHayashida = new Author();

        CollectionAuthor qHayashidaCollectionAuthor1 = new CollectionAuthor(dorohedoroCollection, qHayashida);
        List<CollectionAuthor> qHayashidaCollectionAuthorList = new ArrayList<>();
        qHayashidaCollectionAuthorList.add(qHayashidaCollectionAuthor1);

        qHayashida.setName("Q-Hayashida");
        qHayashida.setCollections(qHayashidaCollectionAuthorList);

        dorohedoroCollection.setName("Dorohedoro");
        dorohedoroCollection.setAuthor(qHayashidaCollectionAuthorList);
        dorohedoroCollection.setPublisher(eccEdiciones);
        dorohedoroCollection.setDemography(seinen);
        dorohedoroCollection.setPlot("Caiman, a man with a lizard head and no memory of his past, searches for the sorcerer responsible for his transformation. With his friend Nikaido, he navigates a violent world where sorcerers and humans coexist. As Caiman uncovers secrets about his identity, he faces powerful foes, dark conspiracies, and unexpected alliances.");
        setCollectionImage(dorohedoroCollection, "static/misc/covers/dorohedoro.jpg");

        List<Book> dorohedoroBookList = new ArrayList<>();

        int dorohedoroVolumeCount = 23; // "Dorohedoro" has 23 volumes
        long dorohedoroStartPublicationDate = 993427200000L; // First volume's publication date (example)
        long dorohedoroInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= dorohedoroVolumeCount; i++) {
            Book book = new Book(new Date(dorohedoroStartPublicationDate + (i - 1) * dorohedoroInterval), false, 200, i, dorohedoroCollection);
            dorohedoroBookList.add(book);
        }

        dorohedoroCollection.setBooks(dorohedoroBookList);

        authorRepository.save(qHayashida);
        collectionRepository.save(dorohedoroCollection);
        bookRepository.saveAll(dorohedoroBookList);
        collectionAuthorRepository.save(qHayashidaCollectionAuthor1);

        //Collection The Girl From The Other Side
        Collection girlFromTheOtherSideCollection = new Collection();
        Author nagabe = new Author();

        CollectionAuthor nagabeCollectionAuthor1 = new CollectionAuthor(girlFromTheOtherSideCollection, nagabe);
        List<CollectionAuthor> nagabeCollectionAuthorList = new ArrayList<>();
        nagabeCollectionAuthorList.add(nagabeCollectionAuthor1);

        nagabe.setName("Nagabe");
        nagabe.setCollections(nagabeCollectionAuthorList);

        girlFromTheOtherSideCollection.setName("The Girl from the Other Side");
        girlFromTheOtherSideCollection.setAuthor(nagabeCollectionAuthorList);
        girlFromTheOtherSideCollection.setPublisher(eccEdiciones);
        girlFromTheOtherSideCollection.setDemography(seinen);
        girlFromTheOtherSideCollection.setPlot("Shiva is a little girl living on the 'outside,' where mysterious and cursed creatures roam. One such creature, Teacher, takes care of her, despite the danger of contact between humans and outsiders. Their relationship grows as they navigate a world divided by fear and prejudice.");
        setCollectionImage(girlFromTheOtherSideCollection, "static/misc/covers/the_girl_from_the_other_side.jpg");

        List<Book> girlFromTheOtherSideBookList = new ArrayList<>();

        int girlFromTheOtherSideVolumeCount = 11; // The Girl from the Other Side has 11 volumes
        long girlFromTheOtherSideStartPublicationDate = 1420070400000L; // First volume's publication date (example)
        long girlFromTheOtherSideInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= girlFromTheOtherSideVolumeCount; i++) {
            Book book = new Book(new Date(girlFromTheOtherSideStartPublicationDate + (i - 1) * girlFromTheOtherSideInterval), false, 200, i, girlFromTheOtherSideCollection);
            girlFromTheOtherSideBookList.add(book);
        }

        girlFromTheOtherSideCollection.setBooks(girlFromTheOtherSideBookList);

        authorRepository.save(nagabe);
        collectionRepository.save(girlFromTheOtherSideCollection);
        bookRepository.saveAll(girlFromTheOtherSideBookList);
        collectionAuthorRepository.save(nagabeCollectionAuthor1);


        // Collection Wizdoms no Kemonotachi
        Collection wizdomsCollection = new Collection();

        CollectionAuthor nagabeCollectionAuthor2 = new CollectionAuthor(wizdomsCollection, nagabe);
        nagabeCollectionAuthorList.add(nagabeCollectionAuthor2);

        wizdomsCollection.setName("The Wize Wize Beasts of the Wizarding Wizdoms");
        wizdomsCollection.setAuthor(nagabeCollectionAuthorList);
        wizdomsCollection.setPublisher(eccEdiciones);
        wizdomsCollection.setDemography(seinen);
        wizdomsCollection.setPlot("The story explores a world where human-animal hybrids coexist, focusing on the struggles, discrimination, and relationships they experience. Through the eyes of these characters, we learn about the complex dynamics and challenges of a society with deeply ingrained prejudice.");
        setCollectionImage(wizdomsCollection, "static/misc/covers/wizdoms.jpg");

        List<Book> wizdomsBookList = new ArrayList<>();

        int wizdomsVolumeCount = 3; // Wizdoms no Kemonotachi has 3 volumes
        long wizdomsStartPublicationDate = 1535760000000L; // First volume's publication date (example)
        long wizdomsInterval = 6 * 30 * 24 * 60 * 60 * 1000L; // Roughly six months between volumes

        for (int i = 1; i <= wizdomsVolumeCount; i++) {
            Book book = new Book(new Date(wizdomsStartPublicationDate + (i - 1) * wizdomsInterval), false, 200, i, wizdomsCollection);
            wizdomsBookList.add(book);
        }

        wizdomsCollection.setBooks(wizdomsBookList);

        collectionRepository.save(wizdomsCollection);
        bookRepository.saveAll(wizdomsBookList);
        collectionAuthorRepository.save(nagabeCollectionAuthor2);

        // Collection Monotone Blue
        Collection monotoneBlueCollection = new Collection();

        CollectionAuthor nagabeCollectionAuthor3 = new CollectionAuthor(monotoneBlueCollection, nagabe);
        nagabeCollectionAuthorList.add(nagabeCollectionAuthor3);

        monotoneBlueCollection.setName("Monotone Blue");
        monotoneBlueCollection.setAuthor(nagabeCollectionAuthorList);
        monotoneBlueCollection.setPublisher(milkyWayEdiciones);
        monotoneBlueCollection.setDemography(seinen);
        monotoneBlueCollection.setPlot("The story revolves around an unexpected friendship between a quiet cat and a curious lizard in a world where animal characters represent various aspects of society. Through their interactions, themes of identity, acceptance, and personal growth are explored, creating a heartfelt narrative.");
        setCollectionImage(monotoneBlueCollection, "static/misc/covers/monotone_blue.jpg");

        List<Book> monotoneBlueBookList = new ArrayList<>();

        Book monotoneBlueBook1 = new Book(new Date(1623715200000L), false, 200, 1, monotoneBlueCollection); // Single volume
        monotoneBlueBookList.add(monotoneBlueBook1);

        monotoneBlueCollection.setBooks(monotoneBlueBookList);

        collectionRepository.save(monotoneBlueCollection);
        bookRepository.saveAll(monotoneBlueBookList);
        collectionAuthorRepository.save(nagabeCollectionAuthor3);

        //Collection Bleach
        Collection bleachCollection = new Collection();
        Author titeKubo = new Author();

        CollectionAuthor titeKuboCollectionAuthor1 = new CollectionAuthor(bleachCollection, titeKubo);
        List<CollectionAuthor> titeKuboCollectionAuthorList = new ArrayList<>();
        titeKuboCollectionAuthorList.add(titeKuboCollectionAuthor1);

        titeKubo.setName("Tite Kubo");
        titeKubo.setCollections(titeKuboCollectionAuthorList);

        bleachCollection.setName("Bleach");
        bleachCollection.setAuthor(titeKuboCollectionAuthorList);
        bleachCollection.setPublisher(paniniManga);
        bleachCollection.setDemography(shonen);
        bleachCollection.setPlot("Ichigo Kurosaki is a high school student with the ability to see ghosts. When he gains the powers of a Soul Reaper from Rukia Kuchiki, he must protect the living world from malevolent spirits known as Hollows. As he navigates the spirit world, Ichigo learns about soul reapers, friendship, and his own role in the battle between good and evil.");
        setCollectionImage(bleachCollection, "static/misc/covers/bleach.jpg");

        List<Book> bleachBookList = new ArrayList<>();

        int bleachVolumeCount = 74; // "Bleach" has 74 volumes
        long bleachStartPublicationDate = 1066348800000L; // First volume's publication date (example)
        long bleachInterval = 3 * 30 * 24 * 60 * 60 * 1000L; // Roughly four months between volumes

        for (int i = 1; i <= bleachVolumeCount; i++) {
            Book book = new Book(new Date(bleachStartPublicationDate + (i - 1) * bleachInterval), false, 200, i, bleachCollection);
            bleachBookList.add(book);
        }

        bleachCollection.setBooks(bleachBookList);

        authorRepository.save(titeKubo);
        collectionRepository.save(bleachCollection);
        bookRepository.saveAll(bleachBookList);
        collectionAuthorRepository.save(titeKuboCollectionAuthor1);
    }

    private void setCollectionImage(Collection collection, String classPathResource) throws IOException {
        ClassPathResource image = new ClassPathResource(classPathResource);
        collection.setBookImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
