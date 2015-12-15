package fil.iagl.iir.dao;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Image;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.outils.SQLCODE;

public class ImageDaoTest extends AbstractDaoTest {

  @Test
  public void sauvegarderTestSucces() throws Exception {
    // Etant donne une image
    Image image = new Image();
    image.setPath("/monPath/2.jpg");

    // Quand on enregistre cette image en base
    imageDao.sauvegarder(image);

    // Alors on verifie que l'ID est bien généré et set par Mybatis
    Assertions.assertThat(image.getId()).isNotNull().isPositive();
  }

  @Test
  public void sauvegarderTestEchec_PathNull() throws Exception {
    // Etant donne une image avec un path null
    Image image = new Image();

    try {
      // Quand on enregistre cette image en base
      imageDao.sauvegarder(image);
      // Alors on verifie que la base renvoie une exception
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException ex) {
      // On verifie que l'exception est bien du au "NOT NULL"
      assertSQLCode(ex, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderTestEchec_PathUnique() throws Exception {
    // Etant donne une image avec un path déjà existant
    Image image = new Image();
    image.setPath("/monPath/0.jpg");
    try {
      // Quand on enregistre cette image en base
      imageDao.sauvegarder(image);
      // Alors on verifie que la base renvoie une exception
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException ex) {
      // On verifie que l'exception est bien du au "UNIQUE"
      assertSQLCode(ex, SQLCODE.UNIQUE_VIOLATION);
    }
  }

  @Test
  public void sauvegarderPourOffreTestSucces() throws Exception {
    Integer idOffre = 1;
    Integer idImage = 1;

    Offre offre = offreDao.getById(idOffre);
    Integer nbImageAvant = offre.getImages().size();

    imageDao.sauvegarderPourOffre(idImage, idOffre);

    Integer nbImageApres = offreDao.getById(idOffre).getImages().size();

    Assertions.assertThat(nbImageApres).isEqualTo(nbImageAvant + 1);

  }

  @Test
  public void sauvegarderPourOffreTestEchec_IdImageNull() throws Exception {
    Integer idImage = null;
    Integer idOffre = 1;

    try {
      this.imageDao.sauvegarderPourOffre(idImage, idOffre);
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException ex) {
      assertSQLCode(ex, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderPourOffreTestEchec_IdOffreNull() throws Exception {
    Integer idImage = 1;
    Integer idOffre = null;

    try {
      this.imageDao.sauvegarderPourOffre(idImage, idOffre);
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException ex) {
      assertSQLCode(ex, SQLCODE.NOT_NULL_VIOLATION);
    }
  }

  @Test
  public void sauvegarderPourOffreTestEchec_IdImageNonExistant() throws Exception {
    Integer idImage = Integer.MAX_VALUE;
    Integer idOffre = 1;

    try {
      this.imageDao.sauvegarderPourOffre(idImage, idOffre);
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException ex) {
      assertSQLCode(ex, SQLCODE.FOREIGN_KEY_VIOLATION);
    }
  }

  @Test
  public void sauvegarderPourOffreTestEchec_IdOffreNonExistant() throws Exception {
    Integer idImage = 1;
    Integer idOffre = Integer.MAX_VALUE;

    try {
      this.imageDao.sauvegarderPourOffre(idImage, idOffre);
      Assertions.fail("Doit soulever une exception");
    } catch (DataIntegrityViolationException ex) {
      assertSQLCode(ex, SQLCODE.FOREIGN_KEY_VIOLATION);
    }
  }

  @Test
  public void getByIdTestSucces() throws Exception {
    // Etant donnée les informations contenu dans la base
    Integer id = 1;
    String path = "/monPath/0.jpg";

    // Quand je recupere l'image d'id 1
    Image image = this.imageDao.getById(id);

    // Alors on verifie que quelque chose est bien retourné
    Assertions.assertThat(image).isNotNull();
    // Alors on verifie que l'id correspond
    Assertions.assertThat(image.getId()).isNotNull().isEqualTo(id);
    // Alors on verifie que le path correspond
    Assertions.assertThat(image.getPath()).isNotNull().isEqualTo(path);
  }

  @Test
  public void getByIdTestEchec() throws Exception {
    // On verifie que la base ne nous renvoi pas d'image lors que l'on demande celle d'id null
    Assertions.assertThat(imageDao.getById(null)).isNull();

    // On verifie que la base ne nous renvoi pas d'image lorsque celle ci n'est pas en base
    Assertions.assertThat(imageDao.getById(Integer.MAX_VALUE)).isNull();
  }

  @Test
  public void supprimerPourOffreTestSucces() throws Exception {
    // Etant donné une offre avec une image dans la table IMAGE_OFFRE
    int idOffre = 1;

    // Quand on demande de supprimer les images de cette offre
    this.imageDao.supprimerPourOffre(idOffre);

    // Alors on verifie que l'offre ne contient plus d'image
    Assertions.assertThat(offreDao.getById(idOffre).getImages()).isEmpty();
  }

}
