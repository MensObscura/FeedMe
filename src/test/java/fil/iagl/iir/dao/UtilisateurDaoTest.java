package fil.iagl.iir.dao;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.SQLCODE;

public class UtilisateurDaoTest extends AbstractDaoTest {

  @Test
  public void getByIdTestSucces() throws Exception {
    // Etant donne un utilisateur avec un ID = 1
    Integer id = 1;
    String nom = "toto";
    String email = "toto.toto@gmail.com";

    // Quand on recupere l'utilisateur associe a cet ID
    Utilisateur user = utilisateurDao.getById(id);

    // Alors on verifie que les informations retournees sont celles
    // attendues
    assertThat(user.getIdUtilisateur()).isNotNull().isPositive().isEqualTo(id);
    assertThat(user.getMail()).isNotNull().isEqualTo(email);
    assertThat(user.getNom()).isNotNull().isEqualTo(nom);
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
  public void sauvegarderTestSucces() throws Exception {
    // Etant donne un utilisateur
    Utilisateur utilisateur = new Utilisateur();
    String nom = "monnom";
    String mail = "monmail@mail.com";

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);

    // n'ayant pas encore son ID renseigne
    Assertions.assertThat(utilisateur.getIdUtilisateur()).isNull();

    // Quand on sauvegarde cet utilisateur en base
    utilisateurDao.sauvegarder(utilisateur);

    // Alors on verifie que son ID a bien ete genere
    assertThat(utilisateur.getIdUtilisateur()).isNotNull().isPositive();
    assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mail);
    assertThat(utilisateur.getNom()).isNotNull().isEqualTo(nom);
  }

  @Test
  public void sauvegarderTestEchec_NomNull() throws Exception {
    // Etant donne un utilisateur n'ayant pas de nom
    Utilisateur utilisateur = new Utilisateur();
    String nom = null;
    String mail = "monmail@mail.com";

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);

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
  public void sauvegarderTestEchec_MailNull() throws Exception {
    // Etant donne un utilisateur n'ayant pas de mail renseigne
    Utilisateur utilisateur = new Utilisateur();
    String nom = "monnom";
    String mail = null;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);

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

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);

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
