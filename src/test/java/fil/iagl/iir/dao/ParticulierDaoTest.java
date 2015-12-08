package fil.iagl.iir.dao;

import java.time.LocalDate;
import java.time.Month;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.core.Condition;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.entite.Image;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.SQLCODE;

public class ParticulierDaoTest extends AbstractDaoTest {

  public static final String PARTICULIER_PRENOM = "titi";
  public static final LocalDate PARTICULIER_DDN = LocalDate.of(2015, Month.JANUARY, 2);
  public static final Integer UTILISATEUR_ID = 1;

  @Test
  public void getByIdTestSucces() throws Exception {
    // Etant donne un particulier enregistre en base avec un ID = 1
    Integer idUtilisateur = 1;
    String nom = "toto";
    String mail = "toto.toto@gmail.com";
    String description = "ceci est la description de toto";

    Integer idParticulier = 1;
    String prenom = "titi";
    LocalDate dateNaissance = LocalDate.of(2015, Month.JANUARY, 31);

    // Quand on recupere le particulier associe a cet ID
    Particulier particulier = particulierDao.getById(idParticulier);

    // Alors les informations du particulier retourne sont bien celles
    // attendues
    Assertions.assertThat(particulier).isNotNull();
    Assertions.assertThat(particulier.getIdUtilisateur()).isNotNull().isEqualTo(idUtilisateur);
    Assertions.assertThat(particulier.getIdParticulier()).isNotNull().isEqualTo(idParticulier);
    Assertions.assertThat(particulier.getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(particulier.getPrenom()).isNotNull().isEqualTo(prenom);
    Assertions.assertThat(particulier.getMail()).isNotNull().isEqualTo(mail);
    Assertions.assertThat(particulier.getDateNaissance()).isNotNull().isEqualTo(dateNaissance);
    Assertions.assertThat(particulier.getAdresse()).isNull();
    Assertions.assertThat(particulier.getDescription()).isNotNull().isEqualTo(description);
    Assertions.assertThat(particulier.getAdresseVisible()).isNotNull().isTrue();
  }

  @Test
  public void modifierTestSucess() throws Exception {
    // Etant donne un particulier enregistre en base avec un ID = 1
    Integer idParticulier = 1;
    Particulier particulier = particulierDao.getById(1);

    // Quand je modifie son adresse
    Integer adresseId = 1;
    String rue = "4 rue guillaume apollinaire";
    String villeName = "Lille";
    String codePostal = "59000";
    Integer villeId = 1;
    Ville ville = new Ville();
    ville.setId(villeId);
    ville.setCp(codePostal);
    ville.setNom(villeName);
    Adresse adresse = new Adresse();
    adresse.setId(adresseId);
    adresse.setVoie(rue);
    adresse.setVille(ville);
    Boolean adresseVisible = false;
    // je modifie sa description
    String description = "ceci est une nouvelle description";
    // et son image de profil
    Image nouvelleImage = new Image();
    nouvelleImage.setId(2);
    nouvelleImage.setPath("/monPath/1.jpg");

    particulier.setAdresse(adresse);
    particulier.setDescription(description);
    particulier.setAdresseVisible(adresseVisible);
    particulier.setImage(nouvelleImage);

    // et que je sauvegarde les modifications
    particulierDao.modifier(particulier);

    Particulier particulierModif = particulierDao.getById(idParticulier);

    // Alors les informations ont bien changées
    Assertions.assertThat(particulierModif).isNotNull();
    Assertions.assertThat(particulierModif.getIdUtilisateur()).isNotNull().isEqualTo(particulier.getIdUtilisateur());
    Assertions.assertThat(particulierModif.getIdParticulier()).isNotNull().isEqualTo(particulier.getIdParticulier());
    Assertions.assertThat(particulierModif.getNom()).isNotNull().isEqualTo(particulier.getNom());
    Assertions.assertThat(particulierModif.getPrenom()).isNotNull().isEqualTo(particulier.getPrenom());
    Assertions.assertThat(particulierModif.getMail()).isNotNull().isEqualTo(particulier.getMail());
    Assertions.assertThat(particulierModif.getDateNaissance()).isNotNull().isEqualTo(particulier.getDateNaissance());
    Assertions.assertThat(particulierModif.getAdresse()).isNotNull();
    Assertions.assertThat(particulierModif.getAdresse().getId()).isEqualTo(adresseId);
    Assertions.assertThat(particulierModif.getAdresse().getVoie()).isEqualTo(rue);
    Assertions.assertThat(particulierModif.getAdresse().getVille().getNom()).isEqualTo(ville.getNom());
    Assertions.assertThat(particulierModif.getAdresse().getVille().getCp()).isEqualTo(codePostal);
    Assertions.assertThat(particulierModif.getDescription()).isNotNull().isEqualTo(description);
    Assertions.assertThat(particulierModif.getAdresseVisible()).isNotNull().isFalse();
    Assertions.assertThat(particulierModif.getImage()).isNotNull().isEqualsToByComparingFields(nouvelleImage);
  }

  @Test
  public void modifierTestSucessAutresInfosIntactes() throws Exception {
    // Etant donne un particulier enregistre en base avec un ID = 1
    Integer idParticulier = 1;
    Particulier particulier = particulierDao.getById(idParticulier);
    String realNom = particulier.getNom();
    String realMail = particulier.getMail();
    String realPrenom = particulier.getPrenom();
    LocalDate realDateNaissance = particulier.getDateNaissance();

    // lorsque je change tous ses champs
    particulier.setDateNaissance(LocalDate.of(2012, Month.FEBRUARY, 10));
    particulier.setNom("fakeNom");
    particulier.setMail("fakeMail");
    particulier.setPrenom("fakePrenom");
    particulier.setPremium(false);
    particulier.setAdresseVisible(false);

    Integer adresseId = 1;
    String rue = "4 rue guillaume apollinaire";
    String villeName = "Lille";
    String codePostal = "59000";
    Integer villeId = 1;
    Ville ville = new Ville();
    ville.setId(villeId);
    ville.setCp(codePostal);
    ville.setNom(villeName);
    Adresse adresse = new Adresse();
    adresse.setId(adresseId);
    adresse.setVoie(rue);
    adresse.setVille(ville);

    String description = "ceci est une nouvelle description";

    particulier.setAdresse(adresse);
    particulier.setDescription(description);

    // lorsque je le sauvegarde
    particulierDao.modifier(particulier);

    // Alors seuls l'adresse et la description du particulier changent
    Particulier particulierModif = particulierDao.getById(idParticulier);

    Assertions.assertThat(particulierModif).isNotNull();
    Assertions.assertThat(particulierModif.getIdUtilisateur()).isNotNull().isEqualTo(particulier.getIdUtilisateur());
    Assertions.assertThat(particulierModif.getIdParticulier()).isNotNull().isEqualTo(particulier.getIdParticulier());
    Assertions.assertThat(particulierModif.getNom()).isNotNull().isEqualTo(realNom);
    Assertions.assertThat(particulierModif.getPrenom()).isNotNull().isEqualTo(realPrenom);
    Assertions.assertThat(particulierModif.getMail()).isNotNull().isEqualTo(realMail);
    Assertions.assertThat(particulierModif.getDateNaissance()).isNotNull().isEqualTo(realDateNaissance);
    Assertions.assertThat(particulierModif.getAdresse()).isNotNull();
    Assertions.assertThat(particulierModif.getAdresse().getId()).isEqualTo(adresseId);
    Assertions.assertThat(particulierModif.getAdresse().getVoie()).isEqualTo(rue);
    Assertions.assertThat(particulierModif.getAdresse().getVille().getNom()).isEqualTo(ville.getNom());
    Assertions.assertThat(particulierModif.getAdresse().getVille().getCp()).isEqualTo(codePostal);
    Assertions.assertThat(particulierModif.getDescription()).isNotNull().isEqualTo(description);
    Assertions.assertThat(particulierModif.getAdresseVisible()).isNotNull().isFalse();
  }

  @Test
  public void testGetAdresseSuccess() throws Exception {
    Utilisateur utilisateur = new Utilisateur();
    int idUtilisateur = 4;
    String nom = "hall";
    String mail = "kolick@gmail.com";
    String rue = "4 rue guillaume apollinaire";
    String description = "ceci est la description de hall";

    Integer adresseId = 1;
    String villeName = "Lille";
    String codePostal = "59000";
    Integer villeId = 1;
    Ville ville = new Ville();
    ville.setId(villeId);
    ville.setCp(codePostal);
    ville.setNom(villeName);
    Adresse adresse = new Adresse();
    adresse.setId(adresseId);
    adresse.setVoie(rue);
    adresse.setVille(ville);

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setAdresse(adresse);

    Integer idParticulier = 2;
    String prenom = "toto";
    LocalDate dateNaissance = LocalDate.of(2015, Month.JANUARY, 31);

    Particulier particulier = particulierDao.getById(idParticulier);

    Assertions.assertThat(particulier).isNotNull();
    Assertions.assertThat(particulier.getIdUtilisateur()).isNotNull().isEqualTo(idUtilisateur);
    Assertions.assertThat(particulier.getIdParticulier()).isNotNull().isEqualTo(idParticulier);
    Assertions.assertThat(particulier.getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(particulier.getPrenom()).isNotNull().isEqualTo(prenom);
    Assertions.assertThat(particulier.getMail()).isNotNull().isEqualTo(mail);
    Assertions.assertThat(particulier.getDateNaissance()).isNotNull().isEqualTo(dateNaissance);
    Assertions.assertThat(particulier.getAdresse()).isNotNull();
    Assertions.assertThat(particulier.getAdresse().getId()).isEqualTo(adresseId);
    Assertions.assertThat(particulier.getAdresse().getVoie()).isEqualTo(rue);
    Assertions.assertThat(particulier.getAdresse().getVille().getNom()).isEqualTo(ville.getNom());
    Assertions.assertThat(particulier.getAdresse().getVille().getCp()).isEqualTo(codePostal);
    Assertions.assertThat(particulier.getDescription()).isNotNull().isEqualTo(description);
    Assertions.assertThat(particulier.getAdresseVisible()).isNotNull().isFalse();
  }

  @Test
  public void getByIdTestEchec() throws Exception {
    // Etant donne qu'aucun particulier n'est enregistre avec un ID nul
    // Quand on tente de recuperer un particuler avec un ID nul
    // Alors le particulier retourne est nul
    Assertions.assertThat(particulierDao.getById(null)).isNull();

    // Etant donne qu'aucun particulier n'est enregistre avec cet ID
    // Quand on tente de recuperer le particulier associe a cet ID
    // Alors le particulier retourne est nul
    Assertions.assertThat(particulierDao.getById(Integer.MAX_VALUE)).isNull();
  }

  @Test
  public void getParticulierByUtilisateurIdTestSuccess() throws Exception {
    // Etant donne un particulier associe a un utilisateur
    Integer idUtilisateur = 1;
    String nom = "toto";
    String mail = "toto.toto@gmail.com";
    String description = "ceci est la description de toto";

    Integer idParticulier = 1;
    String prenom = "titi";
    LocalDate dateNaissance = LocalDate.of(2015, Month.JANUARY, 31);

    // Quand on recupere le particulier a partir de l'ID de l'utilisateur
    // associe
    Particulier p = particulierDao.getParticulierByUtilisateurId(idUtilisateur);

    // Alors les informations du particulier recupere sont celles attendues
    Assertions.assertThat(p).isNotNull();
    Assertions.assertThat(p.getIdParticulier()).isPositive().isNotNull().isEqualTo(idParticulier);
    Assertions.assertThat(p.getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(p.getMail()).isNotNull().isEqualTo(mail);
    Assertions.assertThat(p.getIdParticulier()).isNotNull().isPositive().isEqualTo(idParticulier);
    Assertions.assertThat(p.getPrenom()).isNotNull().isEqualTo(prenom);
    Assertions.assertThat(p.getDateNaissance()).isNotNull().isEqualTo(dateNaissance);
    Assertions.assertThat(p.getDescription()).isNotNull().isEqualTo(description);
  }

  @Test
  public void getParticulierByUtilisateurIdTestFail() throws Exception {
    // Etant donne qu'aucun utilisateur n'est enregistre avec un ID nul
    // Quand on tente de recuperer un particulier associe a un utilisateur
    // par son ID
    // Alors le particulier recupere est nul
    Assertions.assertThat(particulierDao.getParticulierByUtilisateurId(null)).isNull();

    // Etant donne qu'aucun utilisateur n'est enregistre avec cet ID
    // Quand on tente de recuperer un particulier associe a un utilisateur
    // par cet ID
    // Alors le particulier recupere est nul
    Assertions.assertThat(particulierDao.getParticulierByUtilisateurId(Integer.MAX_VALUE)).isNull();
  }

  @Test
  public void sauvegarderTestSucces() throws Exception {
    // Etant donne un particulier associe a un utilisateur
    // n'ayant pas d'ID particulier encore defini
    Particulier particulier = buildParticulier();

    // Quand on enregiste ce particulier en base
    particulierDao.sauvegarder(particulier);

    // Alors l'ID de ce particulier a bien ete genere
    Assertions.assertThat(particulier.getIdParticulier()).isNotNull().isPositive();
    Assertions.assertThat(particulier.getPrenom()).isNotNull().isEqualTo(PARTICULIER_PRENOM);
    Assertions.assertThat(particulier.getDateNaissance()).isNotNull().isEqualTo(PARTICULIER_DDN);
    Assertions.assertThat(particulier.getIdUtilisateur()).isNotNull().isEqualTo(UTILISATEUR_ID);
  }

  @Test
  public void sauvegarderTestEchec_PrenomNull() throws Exception {
    // Etant donne un particulier n'ayant pas de prenom
    Particulier particulier = buildParticulier();
    particulier.setPrenom(null);

    try {
      // Quand on enregistre ce particulier en base
      particulierDao.sauvegarder(particulier);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_DateNaissanceNull() throws Exception {
    // Etant donne un particulier n'ayant pas de date de naissance
    Particulier particulier = buildParticulier();
    particulier.setDateNaissance(null);

    try {
      // Quand on enregistre ce particulier en base
      particulierDao.sauvegarder(particulier);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_IdUtilisateurNull() throws Exception {
    // Etant donne un particulier n'ayant pas d'ID utilisateur pour
    // l'associer a un utilisateur
    Particulier particulier = buildParticulier();
    particulier.setIdUtilisateur(null);

    try {
      // Quand on enegistre ce particulier en base
      particulierDao.sauvegarder(particulier);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_IdUtilisateurNonExistant() throws Exception {
    // Etant donne un particulier associe a un utilisateur inexistant
    Particulier particulier = buildParticulier();
    particulier.setIdUtilisateur(Integer.MAX_VALUE);

    try {
      // Quand on enregistre ce particulier en base
      particulierDao.sauvegarder(particulier);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
    }
  }

  @Test
  public void getAllParticulierTestSucces() throws Exception {
    int nbPremium = 2;
    Assertions.assertThat(this.particulierDao.getAllPremium()).isNotEmpty().hasSize(nbPremium).are(new Condition<Particulier>() {
      @Override
      public boolean matches(Particulier particulier) {
        return particulier.getPremium();
      }

    });

  }

  /* *********************** BUILDS ************************/

  public Particulier buildParticulier() {
    Particulier particulier = new Particulier();
    particulier.setPrenom(PARTICULIER_PRENOM);
    particulier.setDateNaissance(PARTICULIER_DDN);
    particulier.setIdUtilisateur(UTILISATEUR_ID);

    return particulier;
  }
}
