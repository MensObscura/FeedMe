package fil.iagl.iir;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fil.iagl.iir.conf.FeedMeConfiguration;
import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.AuthentificationParticulier;
import fil.iagl.iir.entite.Menu;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Pays;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Role;
import fil.iagl.iir.entite.TypeCuisine;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.FeedMeAuthentificationToken;
import fil.iagl.iir.outils.FeedMeException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FeedMeConfiguration.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles("test")
public abstract class AbstractFeedMeTest {

  protected static final int RANDOM_STRING_SIZE = 60;

  protected static final String USERNAME_TEST_USER = "toto.toto@gmail.com";

  protected static final MediaType FEED_ME_MEDIA_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  private static Boolean hasBeenReset = Boolean.FALSE;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private AuthentificationDao authentificationDao;

  @Before
  public void setUp() {
    if (!hasBeenReset) {
      Resource r = new ClassPathResource("test_script.sql");
      ScriptRunner runner = null;
      try {
        File reset = r.getFile();
        runner = new ScriptRunner(dataSource.getConnection());
        runner.runScript(new FileReader(reset));
      } catch (IOException | SQLException e) {
        throw new FeedMeException(e);
      } finally {
        runner.closeConnection();
        hasBeenReset = Boolean.TRUE;
      }
    }

    MockitoAnnotations.initMocks(this);

    this.fausseConnection(USERNAME_TEST_USER, Optional.empty());

  }

  private void fausseConnection(String username, Optional<Role> role) {
    Authentification<? extends Utilisateur> auth = authentificationDao.getByUsername(username);
    if (role.isPresent()) {
      auth.setRole(role.get());
    }
    FeedMeAuthentificationToken authToken = new FeedMeAuthentificationToken(auth);
    SecurityContextHolder.getContext().setAuthentication(authToken);
  }

  protected void changerRole(Role role) {
    fausseConnection(USERNAME_TEST_USER, Optional.of(role));
  }

  protected Utilisateur createUtilisateur() {
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setIdUtilisateur(1);
    utilisateur.setPremium(Boolean.TRUE);
    utilisateur.setMail("toto.toto@gmail.com");
    utilisateur.setNom("toto");
    utilisateur.setDescription("ceci est la description de toto");
    utilisateur.setAdresseVisible(Boolean.TRUE);
    return utilisateur;
  }

  protected Particulier createParticulier() {
    Particulier particulier = new Particulier();
    particulier.setIdUtilisateur(1);
    particulier.setIdParticulier(1);
    particulier.setPremium(Boolean.TRUE);
    particulier.setNom("toto");
    particulier.setPrenom("tata");
    particulier.setMail("mail@gmail.com");
    particulier.setDateNaissance(LocalDate.now().minusYears(20));
    particulier.setDescription("ceci est la description de toto");
    particulier.setAdresseVisible(Boolean.TRUE);
    return particulier;
  }

  protected Reservation createReservation() {
    Integer idOffre = 2;
    Integer idUtilisateur = 2;
    Integer nb_places = 3;

    Utilisateur convive = new Utilisateur();
    convive.setIdUtilisateur(idUtilisateur);
    convive.setNom("bob");

    Offre offre = new Offre();
    offre.setId(idOffre);

    LocalDate dateReservation = LocalDate.now();

    Reservation reservation = new Reservation();
    reservation.setConvive(convive);
    reservation.setDateReservation(dateReservation);
    reservation.setOffre(offre);
    reservation.setNb_places(nb_places);

    return reservation;
  }

  protected Offre createOffre() {
    String titre = "MonTitre";
    String note = "Note";
    Integer prix = 999;
    Integer nombrePersonne = 5;
    Integer dureeMinute = 120;
    LocalDateTime dateRepas = LocalDateTime.of(2015, 2, 1, 19, 45, 0);
    Boolean animaux = Boolean.FALSE;
    Boolean premium = Boolean.TRUE;

    Integer idAdresse = 1;
    Integer idVille = 1;
    String nomVille = "FeedMeTown";
    String cp = "45678";
    Integer idPays = 1;

    Integer idTypeCuisine = 3;

    Integer idUtilisateur = 1;

    Pays pays = new Pays();
    pays.setId(idPays);

    Ville ville = new Ville();
    ville.setId(idVille);
    ville.setPays(pays);
    ville.setNom(nomVille);
    ville.setCp(cp);

    Adresse adresse = new Adresse();
    adresse.setId(idAdresse);
    adresse.setVille(ville);
    adresse.setVoie("Voie");

    TypeCuisine typeCuisine = new TypeCuisine();
    typeCuisine.setId(idTypeCuisine);

    Utilisateur hote = new Utilisateur();
    hote.setIdUtilisateur(idUtilisateur);

    Menu menu = new Menu();
    String boisson = "MaBoisson";
    String dessert = "MonDessert";
    String entree = "MonEntree";
    String plat = "MonPlat";
    menu.setBoisson(boisson);
    menu.setDessert(dessert);
    menu.setEntree(entree);
    menu.setPlat(plat);

    Offre offre = new Offre();

    offre.setTitre(titre);
    offre.setPrix(prix);
    offre.setNombrePersonne(nombrePersonne);
    offre.setDureeMinute(dureeMinute);
    offre.setDateRepas(dateRepas);
    offre.setMenu(menu);
    offre.setAnimaux(animaux);
    offre.setAdresse(adresse);
    offre.setTypeCuisine(typeCuisine);
    offre.setHote(hote);
    offre.setNote(note);
    offre.setPremium(premium);

    return offre;
  }

  protected Authentification<Particulier> createAuthentificationParticulier() {
    Integer idUtilisateur = 2;
    Integer idParticulier = 2;
    String mail = "foo.bar@gmail.com";
    String nom = "foo";
    String prenom = "bar";
    LocalDate dateNaissance = LocalDate.now().minusYears(20);

    String password = RandomStringUtils.random(RANDOM_STRING_SIZE);
    Role role = Role.PARTICULIER;

    Particulier utilisateur = new Particulier();

    utilisateur.setIdUtilisateur(idUtilisateur);
    utilisateur.setIdParticulier(idParticulier);
    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPrenom(prenom);
    utilisateur.setDateNaissance(dateNaissance);

    Authentification<Particulier> authentification = new AuthentificationParticulier();
    authentification.setUtilisateur(utilisateur);
    authentification.setPassword(password);
    authentification.setRole(role);

    return authentification;
  }
}
