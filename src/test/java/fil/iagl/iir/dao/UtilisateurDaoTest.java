package fil.iagl.iir.dao;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.SQLCODE;

public class UtilisateurDaoTest extends AbstractDaoTest {

  @Test
  public void getByIdTestSucces() throws Exception {
    // Etant donne un utilisateur avec un ID = 1
    Integer id = 1;
    String nom = "toto";
    String email = "toto.toto@gmail.com";
    String description = "ceci est la description de toto";

    // Quand on recupere l'utilisateur associe a cet ID
    Utilisateur user = utilisateurDao.getById(id);

    // Alors on verifie que les informations retournees sont celles
    // attendues
    assertThat(user.getIdUtilisateur()).isNotNull().isPositive().isEqualTo(id);
    assertThat(user.getMail()).isNotNull().isEqualTo(email);
    assertThat(user.getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(user.getAdresse()).isNull();
    Assertions.assertThat(user.getPremium()).isNotNull().isTrue();
    assertThat(user.getDescription()).isNotNull().isEqualTo(description);
    assertThat(user.getAdresseVisible()).isNotNull().isTrue();
  }

  @Test
  public void testGetUtilisateurAdresseSucess() throws Exception {
    // Etant donne un utilisateur
    int idUtilisateur = 4;
    Utilisateur utilisateur = new Utilisateur();
    String nom = "hall";
    String mail = "kolick@gmail.com";
    String description = "ceci est la description de hall";
    String rue = "4 rue guillaume apollinaire";
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

    utilisateur.setAdresseVisible(Boolean.FALSE);
    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setAdresse(adresse);

    // n'ayant pas encore son ID
    Assertions.assertThat(utilisateur.getIdUtilisateur()).isNull();

    Utilisateur utilisateurToCheck = utilisateurDao.getById(idUtilisateur);
    assertThat(utilisateurToCheck.getIdUtilisateur()).isNotNull().isPositive().isEqualTo(idUtilisateur);
    assertThat(utilisateurToCheck.getMail()).isNotNull().isEqualTo(mail);
    assertThat(utilisateurToCheck.getNom()).isNotNull().isEqualTo(nom);
    assertThat(utilisateurToCheck.getAdresse()).isNotNull();
    assertThat(utilisateurToCheck.getDescription()).isNotNull().isEqualTo(description);
    assertThat(utilisateurToCheck.getAdresseVisible()).isNotNull().isFalse();
    // Alors les donnees de l'adresse sont celles attendues
    assertThat(utilisateurToCheck.getAdresse().getId()).isEqualTo(adresseId);
    assertThat(utilisateurToCheck.getAdresse().getVoie()).isEqualTo(rue);
    // les donnees de la ville sont celles attendues
    assertThat(utilisateurToCheck.getAdresse().getVille().getNom()).isEqualTo(ville.getNom());
    assertThat(utilisateurToCheck.getAdresse().getVille().getCp()).isEqualTo(codePostal);
  }

  @Test
  public void getByIdTestEchec() throws Exception {
    // Etant donne qu'aucun utilisateur n'est enregistre avec un ID nul
    // Quand on recupere un utilisateur avec un ID nul
    // Alors l'utilisateur retourne est nul
    assertThat(utilisateurDao.getById(null)).isNull();

    // Etant donne qu'aucun utilisateur n'est enregistre avec cet ID
    // Quand on recupere un utilisateur avec cet ID
    // Alors l'utilisateur retourne est nul
    assertThat(utilisateurDao.getById(Integer.MAX_VALUE)).isNull();
  }

  @Test
  public void sauvegarderTestSuccesSansAdresse() throws Exception {
    // Etant donne un utilisateur
    Utilisateur utilisateur = new Utilisateur();
    String nom = "monnom";
    String mail = "monmail@mail.com";
    Boolean premium = true;
    Boolean adresseVisible = Boolean.TRUE;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPremium(premium);
    utilisateur.setAdresseVisible(adresseVisible);

    // n'ayant pas encore son ID renseigne ni son adresse
    Assertions.assertThat(utilisateur.getIdUtilisateur()).isNull();
    Assertions.assertThat(utilisateur.getAdresse()).isNull();

    // Quand on sauvegarde cet utilisateur en base
    utilisateurDao.sauvegarder(utilisateur);

    // Alors on verifie que son ID a bien ete genere
    assertThat(utilisateur.getIdUtilisateur()).isNotNull().isPositive();
    assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mail);
    assertThat(utilisateur.getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(utilisateur.getPremium()).isNotNull().isTrue();
    assertThat(utilisateur.getDescription()).isNull();
    assertThat(utilisateur.getAdresseVisible()).isNotNull().isTrue();

    // et que son adresse est nulle
    assertThat(utilisateur.getAdresse()).isNull();
  }

  @Test
  public void sauvegarderTestSuccesSansDescription() throws Exception {
    // Etant donne un utilisateur
    Utilisateur utilisateur = new Utilisateur();
    String nom = "monnom";
    String mail = "monmail@mail.com";
    Boolean premium = true;
    Boolean adresseVisible = true;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPremium(premium);
    utilisateur.setAdresseVisible(adresseVisible);

    // n'ayant pas encore son ID renseigne ni son adresse
    Assertions.assertThat(utilisateur.getIdUtilisateur()).isNull();
    Assertions.assertThat(utilisateur.getAdresse()).isNull();

    // Quand on sauvegarde cet utilisateur en base
    utilisateurDao.sauvegarder(utilisateur);

    // Alors on verifie que son ID a bien ete genere
    assertThat(utilisateur.getIdUtilisateur()).isNotNull().isPositive();
    assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mail);
    assertThat(utilisateur.getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(utilisateur.getPremium()).isNotNull().isTrue();
    assertThat(utilisateur.getAdresse()).isNull();
    assertThat(utilisateur.getAdresseVisible()).isNotNull().isTrue();

    // et que sa description est nulle
    assertThat(utilisateur.getDescription()).isNull();
  }

  @Test
  public void sauvegarderTestSuccesAvecAdresse() throws Exception {
    // Etant donne un utilisateur
    Utilisateur utilisateur = new Utilisateur();
    String nom = "hall";
    String mail = "kolick@mail.com";
    Boolean premium = true;
    String description = "ceci est la description de hall";
    Boolean adresseVisible = false;

    String rue = "4 rue guillaume apollinaire";
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
    utilisateur.setPremium(premium);
    utilisateur.setAdresse(adresse);
    utilisateur.setDescription(description);
    utilisateur.setAdresseVisible(adresseVisible);

    // n'ayant pas encore son ID
    Assertions.assertThat(utilisateur.getIdUtilisateur()).isNull();

    // Quand on sauvegarde cet utilisateur en base
    utilisateurDao.sauvegarder(utilisateur);

    // Alors on verifie que son ID a bien ete genere
    assertThat(utilisateur.getIdUtilisateur()).isNotNull().isPositive();
    assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mail);
    assertThat(utilisateur.getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(utilisateur.getPremium()).isNotNull().isTrue();
    assertThat(utilisateur.getAdresse()).isNotNull();
    assertThat(utilisateur.getAdresse().getId()).isNotNull().isPositive().isEqualTo(adresseId);
    assertThat(utilisateur.getAdresse().getVoie()).isNotNull().isEqualTo(rue);
    assertThat(utilisateur.getAdresse().getVille()).isNotNull();
    assertThat(utilisateur.getAdresse().getVille().getId()).isNotNull().isPositive().isEqualTo(villeId);
    assertThat(utilisateur.getAdresse().getVille().getCp()).isNotNull().isEqualTo(codePostal);
    assertThat(utilisateur.getAdresse().getVille().getNom()).isNotNull().isEqualTo(villeName);
    assertThat(utilisateur.getDescription()).isNotNull().isEqualTo(description);
    assertThat(utilisateur.getAdresseVisible()).isNotNull().isFalse();

  }

  @Test
  public void sauvegarderTestSucessAdresseVisibleDefault() throws Exception {
    // Etant donne un utilisateur n'ayant pas le champs adresseVisible renseigné
    Utilisateur utilisateur = new Utilisateur();
    String nom = "hall";
    String mail = "kolick@mail.com";
    Boolean premium = true;
    String description = "ceci est la description de hall";

    String rue = "4 rue guillaume apollinaire";
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
    utilisateur.setPremium(premium);
    utilisateur.setAdresse(adresse);
    utilisateur.setDescription(description);

    // n'ayant pas encore son ID
    Assertions.assertThat(utilisateur.getIdUtilisateur()).isNull();

    // Quand on sauvegarde cet utilisateur en base
    utilisateurDao.sauvegarder(utilisateur);

    // Alors on verifie que les informations sont correctes et que la visibilité de son adresse est à false
    assertThat(utilisateur.getIdUtilisateur()).isNotNull().isPositive();

    utilisateur = utilisateurDao.getById(utilisateur.getIdUtilisateur());

    assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mail);
    assertThat(utilisateur.getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(utilisateur.getPremium()).isNotNull().isTrue();
    assertThat(utilisateur.getAdresse()).isNotNull();
    assertThat(utilisateur.getAdresse().getId()).isNotNull().isPositive().isEqualTo(adresseId);
    assertThat(utilisateur.getAdresse().getVoie()).isNotNull().isEqualTo(rue);
    assertThat(utilisateur.getAdresse().getVille()).isNotNull();
    assertThat(utilisateur.getAdresse().getVille().getId()).isNotNull().isPositive().isEqualTo(villeId);
    assertThat(utilisateur.getAdresse().getVille().getCp()).isNotNull().isEqualTo(codePostal);
    assertThat(utilisateur.getAdresse().getVille().getNom()).isNotNull().isEqualTo(villeName);
    assertThat(utilisateur.getDescription()).isNotNull().isEqualTo(description);
    assertThat(utilisateur.getAdresseVisible()).isNotNull().isFalse();
  }

  @Test
  public void sauvegarderTestEchec_NomNull() throws Exception {
    // Etant donne un utilisateur n'ayant pas de nom
    Utilisateur utilisateur = new Utilisateur();
    String nom = null;
    String mail = "monmail@mail.com";
    Boolean premium = true;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPremium(premium);

    try {
      // Quand on enregistre cet utilisateur en base
      utilisateurDao.sauvegarder(utilisateur);

      // Alors on attend a ce qu'une exception soit lancee
      fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {

      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_PremiumNull() throws Exception {
    // Etant donne un utilisateur n'ayant pas de status premium définis
    Utilisateur utilisateur = new Utilisateur();
    String nom = "monnom";
    String mail = "monmail@mail.com";
    Boolean premium = null;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPremium(premium);

    try {
      // Quand on enregistre cet utilisateur
      utilisateurDao.sauvegarder(utilisateur);

      // Alors on s'attend à ce qu'une exception soit levée
      fail("Doit lever une exception");
    } catch (DataIntegrityViolationException dive) {

      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_MailNull() throws Exception {
    // Etant donne un utilisateur n'ayant pas de mail renseigne
    Utilisateur utilisateur = new Utilisateur();
    String nom = "monnom";
    String mail = null;
    Boolean premium = true;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPremium(premium);

    try {
      // Quand on enregistre cet utilisateur en base
      utilisateurDao.sauvegarder(utilisateur);

      // Alors on attend a ce qu'une exception soit lancee
      fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_MailUnique() throws Exception {
    // Etant donne un utilisateur
    Utilisateur utilisateur = new Utilisateur();
    String nom = "monnom";
    // ayant un email deja enregistre en base
    String mail = USERNAME_TEST_USER;
    Boolean premium = true;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPremium(premium);

    try {
      // Quand on enegistre cet utilisateur en base
      utilisateurDao.sauvegarder(utilisateur);

      // Alors on attend a ce qu'une exception soit lancee
      fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.UNIQUE_VIOLATION);
    }
  }

}
