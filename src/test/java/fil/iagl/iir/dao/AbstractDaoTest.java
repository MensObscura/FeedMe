package fil.iagl.iir.dao;

import org.fest.assertions.api.Assertions;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.dao.image.ImageDao;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.pays.PaysDao;
import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.dao.typeCuisine.TypeCuisineDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.dao.ville.VilleDao;
import fil.iagl.iir.outils.SQLCODE;

public abstract class AbstractDaoTest extends AbstractFeedMeTest {

  @Autowired
  protected AuthentificationDao authentificationDao;

  @Autowired
  protected UtilisateurDao utilisateurDao;

  @Autowired
  protected ParticulierDao particulierDao;

  @Autowired
  protected ReservationDao reservationDao;

  @Autowired
  protected PaysDao paysDao;

  @Autowired
  protected TypeCuisineDao typeCuisineDao;

  @Autowired
  protected VilleDao villeDao;

  @Autowired
  protected OffreDao offreDao;

  @Autowired
  protected ImageDao imageDao;

  protected void assertSQLCode(DataIntegrityViolationException dive, SQLCODE sqlCode) {
    Assertions.assertThat(dive.getCause()).isInstanceOf(PSQLException.class);
    Assertions.assertThat(((PSQLException) (dive.getCause())).getSQLState()).isEqualTo(sqlCode.getSqlCode());
  }

}
