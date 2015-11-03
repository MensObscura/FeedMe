package fil.iagl.iir.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.SQLCODE;

public class OffreDaoTest extends AbstractDaoTest {

  private static final int NB_OFFRES = 4;

  @Test
  public void getAllTestSucces() throws Exception {
    // Etant donne qu'il existe NB_OFFRES en base
    // Quand on recupere la liste des offres existantes
    // Alors on veut une liste non vide avec le bon nombre d'offres
    Assertions.assertThat(offreDao.getAll()).isNotEmpty().hasSize(NB_OFFRES);
  }

  @Test
  public void getByIdTestSucces() throws Exception {
    // Etant donne les informations d'une offre enregistree en base avec
    // l'ID =1
    Integer idOffre = 1;
    LocalDate dateCreation = LocalDate.of(2015, 1, 1);
    String titre = "MonTitre";
    Integer prix = 999;
    Integer nombrePersonne = 5;
    Integer dureeMinute = 120;
    LocalDateTime dateRepas = LocalDateTime.of(2015, 2, 1, 19, 45, 0);
    String menu = "DescriptionDuMenu";
    Boolean animaux = Boolean.FALSE;
    String note = "Note";
    Integer ageMin = 20;
    Integer ageMax = 30;

    // les informations de l'adresse associee a cette offre
    Integer idAdresse = 1;
    String voie = "4 rue guillaume apollinaire";
    Integer idVille = 1;
    String cp = "59000";
    String ville = "Lille";
    Integer idPays = 1;
    String pays = "France";
    String codePays = "FR";

    // les informations sur le type de cuisine de cette offre
    Integer idTypeCuisine = 3;
    String type = "Steak House";

    // les informations sur l'hote de cette offre
    Integer idUtilisateur = 1;
    String nom = "toto";
    String mail = "toto.toto@gmail.com";

    // Quand on recupere l'offre associee a cet ID
    Offre offre = offreDao.getById(idOffre);

    // Alors on verifie que les informations de l'offre recuperee sont
    // celles attendues
    Assertions.assertThat(offre).isNotNull();
    Assertions.assertThat(offre.getId()).isNotNull().isEqualTo(idOffre);
    Assertions.assertThat(offre.getDateCreation()).isNotNull().isEqualTo(dateCreation);
    Assertions.assertThat(offre.getTitre()).isNotNull().isEqualTo(titre);
    Assertions.assertThat(offre.getPrix()).isNotNull().isEqualTo(prix);
    Assertions.assertThat(offre.getNombrePersonne()).isNotNull().isEqualTo(nombrePersonne);
    Assertions.assertThat(offre.getDureeMinute()).isNotNull().isEqualTo(dureeMinute);
    Assertions.assertThat(offre.getDateRepas()).isNotNull().isEqualTo(dateRepas);
    Assertions.assertThat(offre.getMenu()).isNotNull().isEqualTo(menu);
    Assertions.assertThat(offre.getAnimaux()).isNotNull().isEqualTo(animaux);
    Assertions.assertThat(offre.getNote()).isNotNull().isEqualTo(note);
    Assertions.assertThat(offre.getAgeMin()).isNotNull().isEqualTo(ageMin);
    Assertions.assertThat(offre.getAgeMax()).isNotNull().isEqualTo(ageMax);

    // que les informations de l'adresse associee a l'offre sont celles
    // attendues
    Assertions.assertThat(offre.getAdresse()).isNotNull();
    Assertions.assertThat(offre.getAdresse().getVille()).isNotNull();
    Assertions.assertThat(offre.getAdresse().getVille().getPays()).isNotNull();

    Assertions.assertThat(offre.getAdresse().getId()).isNotNull().isEqualTo(idAdresse);
    Assertions.assertThat(offre.getAdresse().getVoie()).isNotNull().isEqualTo(voie);

    Assertions.assertThat(offre.getAdresse().getVille().getId()).isNotNull().isEqualTo(idVille);
    Assertions.assertThat(offre.getAdresse().getVille().getCp()).isNotNull().isEqualTo(cp);
    Assertions.assertThat(offre.getAdresse().getVille().getNom()).isNotNull().isEqualTo(ville);

    Assertions.assertThat(offre.getAdresse().getVille().getPays().getId()).isNotNull().isEqualTo(idPays);
    Assertions.assertThat(offre.getAdresse().getVille().getPays().getNom()).isNotNull().isEqualTo(pays);
    Assertions.assertThat(offre.getAdresse().getVille().getPays().getCodePays()).isNotNull().isEqualTo(codePays);

    // que les informations sur le type de cuisine associe a l'offre sont
    // celles attendues
    Assertions.assertThat(offre.getTypeCuisine()).isNotNull();
    Assertions.assertThat(offre.getTypeCuisine().getId()).isNotNull().isEqualTo(idTypeCuisine);
    Assertions.assertThat(offre.getTypeCuisine().getType()).isNotNull().isEqualTo(type);

    // que les informations sur l'hote de l'offre sont celles attendues
    Assertions.assertThat(offre.getHote()).isNotNull();
    Assertions.assertThat(offre.getHote().getIdUtilisateur()).isNotNull().isEqualTo(idUtilisateur);
    Assertions.assertThat(offre.getHote().getNom()).isNotNull().isEqualTo(nom);
    Assertions.assertThat(offre.getHote().getMail()).isNotNull().isEqualTo(mail);

  }

  @Test
  public void getByIdTestEchec() throws Exception {
    // Etant donne qu'aucune offre n'est enregistree avec un ID nul
    // Quand on recupere une offre avec un ID null
    // Alors l'offre retournee est nulle
    Assertions.assertThat(offreDao.getById(null)).isNull();

    // Etant donne qu'aucune offre n'est enregistree avec cet ID
    // Quand on recupere l'offre associee a cet ID
    // Alors l'offre retournee est nulle
    Assertions.assertThat(offreDao.getById(Integer.MAX_VALUE)).isNull();
  }

  @Test
  public void sauvegarderTestSucces() throws Exception {
    // Etant donne une offre
    Offre offre = this.createOffre();

    // Quand on enregistre cette offre en base
    offreDao.sauvegarder(offre);

    // Alors on verifie que l'ID est bien généré et set par Mybatis
    Assertions.assertThat(offre.getId()).isNotNull().isPositive();

    // et que la date de création de l'offre est mise à la date du jour lors
    // de la création
    Assertions.assertThat(offreDao.getById(offre.getId()).getDateCreation()).isEqualTo(LocalDate.now());
  }

  @Test
  public void sauvegarderTestEchec_TitreNull() throws Exception {
    // Etant donne une offre n'ayant pas de titre
    Offre offre = this.createOffre();
    offre.setTitre(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_PrixNull() throws Exception {
    // Etant donne une offre ayant un prix nul
    Offre offre = this.createOffre();
    offre.setPrix(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_NombrePersonneEgalZero() throws Exception {
    // Etant donne une offre ayant un nombre d'invite a zero
    Offre offre = createOffre();
    offre.setNombrePersonne(0);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);
    } catch (Exception e) {
      // Alors on s'attend a ce qu'une exception soit lancee
      Assertions.assertThat(e).isInstanceOf(FeedMeException.class).hasMessage("Nombre de convive pour l'offre ne doit pas être égal à 0");
    }
  }

  @Test
  public void sauvegarderTestEchec_NombrePersonneNull() throws Exception {
    // Etant donne une offre ayant son nombre de convives a nul
    Offre offre = this.createOffre();
    offre.setNombrePersonne(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_DureeMinuteNull() throws Exception {
    // Etant donne une offre ayant une duree nulle
    Offre offre = this.createOffre();
    offre.setDureeMinute(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_DateRepasNull() throws Exception {
    // Etant donne une offre ayant une date de repas nulle
    Offre offre = this.createOffre();
    offre.setDateRepas(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_MenuNull() throws Exception {
    // Etant donne une offre ayant son menu nul
    Offre offre = this.createOffre();
    offre.setMenu(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_AnimauxNull() throws Exception {
    // Etant donne une offre ayant son information sur la presence d'animaux
    // nulle
    Offre offre = this.createOffre();
    offre.setAnimaux(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_AdresseIdNull() throws Exception {
    // Etant donne une offre ayant son adresse nulle
    Offre offre = this.createOffre();
    offre.getAdresse().setId(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_TypeCuisineIdNull() throws Exception {
    // Etant donne une offre ayant son type de cuisine nul
    Offre offre = this.createOffre();
    offre.getTypeCuisine().setId(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_UtilisateurIdNull() throws Exception {
    // Etant donne une offre ayant son hote nul
    Offre offre = this.createOffre();
    offre.getHote().setIdUtilisateur(null);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_AdresseNonExistant() throws Exception {
    // Etant donne une offre ayant une adresse n'existant pas
    Offre offre = this.createOffre();
    offre.getAdresse().setId(Integer.MAX_VALUE);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_TypeCuisineNonExistant() throws Exception {
    // Etant donne une offre ayant un type de cuisine n'existant pas
    Offre offre = this.createOffre();
    offre.getTypeCuisine().setId(Integer.MAX_VALUE);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_UtilisateurNonExistant() throws Exception {
    // Etant donne une offre ayant un hote n'existant pas en base
    Offre offre = this.createOffre();
    offre.getHote().setIdUtilisateur(Integer.MAX_VALUE);

    try {
      // Quand on enregistre cette offre
      offreDao.sauvegarder(offre);

      // Alors on attend a ce qu'une exception soit lancee
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException dive) {
      this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
    }
  }

}
