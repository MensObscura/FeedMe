package fil.iagl.iir.dao;

import java.time.LocalDateTime;

import org.fest.assertions.api.Assertions;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.pays.PaysDao;
import fil.iagl.iir.dao.reservation.ReservationDao;
import fil.iagl.iir.dao.typeCuisine.TypeCuisineDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Pays;
import fil.iagl.iir.entite.TypeCuisine;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.SQLCODE;

public abstract class AbstractDaoTest extends AbstractFeedMeTest {

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
	protected OffreDao offreDao;

	protected void assertSQLCode(DataIntegrityViolationException dive, SQLCODE sqlCode) {
		Assertions.assertThat(dive.getCause()).isInstanceOf(PSQLException.class);
		Assertions.assertThat(((PSQLException) (dive.getCause())).getSQLState()).isEqualTo(sqlCode.getSqlCode());
	}

	protected Offre createOffre() {
		String titre = "MonTitre";
		Integer prix = 999;
		Integer nombrePersonne = 5;
		Integer dureeMinute = 120;
		LocalDateTime dateRepas = LocalDateTime.of(2015, 2, 1, 19, 45, 00);
		String menu = "DescriptionDuMenu";
		Boolean animaux = Boolean.FALSE;

		Integer idAdresse = 1;
		Integer idVille = 1;
		Integer idPays = 1;

		Integer idTypeCuisine = 3;

		Integer idUtilisateur = 1;

		Pays pays = new Pays();
		pays.setId(idPays);

		Ville ville = new Ville();
		ville.setId(idVille);
		ville.setPays(pays);

		Adresse adresse = new Adresse();
		adresse.setId(idAdresse);
		adresse.setVille(ville);

		TypeCuisine typeCuisine = new TypeCuisine();
		typeCuisine.setId(idTypeCuisine);

		Utilisateur hote = new Utilisateur();
		hote.setIdUtilisateur(idUtilisateur);

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

		return offre;
	}

}
