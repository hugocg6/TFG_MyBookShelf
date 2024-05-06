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
        Author innocentAuthor = new Author();

        List<CollectionAuthor> innocentCollectionAuthorList = new ArrayList<>();
        CollectionAuthor innocentCollectionAuthor = new CollectionAuthor(innocentCollection, innocentAuthor);
        innocentCollectionAuthorList.add(innocentCollectionAuthor);

        innocentAuthor.setName("Shin'ichi Sakamoto");
        innocentAuthor.setCollections(innocentCollectionAuthorList);

        innocentCollection.setName("Innocent");
        innocentCollection.setAuthor(innocentCollectionAuthorList);
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

        authorRepository.save(innocentAuthor);
        collectionRepository.save(innocentCollection);
        bookRepository.saveAll(innocentBookList);
        collectionAuthorRepository.save(innocentCollectionAuthor);

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

    }

    private void setCollectionImage(Collection collection, String classPathResource) throws IOException {
        ClassPathResource image = new ClassPathResource(classPathResource);
        collection.setBookImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
