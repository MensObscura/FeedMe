package fil.iagl.iir.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.outils.SQLCODE;

public class OffreDaoTest extends AbstractDaoTest {

	private static final int NB_OFFRE = 3;

	@Test
	public void getAllTestSucces() throws Exception {
		Assertions.assertThat(offreDao.getAll()).isNotEmpty().hasSize(NB_OFFRE);
	}

	@Test
	public void getByIdTestSucces() throws Exception {
		Integer idOffre = 1;
		LocalDate dateCreation = LocalDate.of(2015, 1, 1);
		String titre = "MonTitre";
		Integer prix = 999;
		Integer nombrePersonne = 5;
		Integer dureeMinute = 120;
		LocalDateTime dateRepas = LocalDateTime.of(2015, 2, 1, 19, 45, 00);
		String menu = "DescriptionDuMenu";
		Boolean animaux = Boolean.FALSE;
		Integer note = 0;
		Integer ageMin = 20;
		Integer ageMax = 30;

		Integer idAdresse = 1;
		String voie = "4 rue guillaume apollinaire";
		Integer idVille = 1;
		String cp = "59000";
		String ville = "Lille";
		Integer idPays = 1;
		String pays = "France";
		String codePays = "FR";

		Integer idTypeCuisine = 3;
		String typeCuisine = "Steak House";

		Integer idUtilisateur = 1;
		String nom = "toto";
		String mail = "toto.toto@gmail.com";

		Offre offre = offreDao.getById(idOffre);

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

		Assertions.assertThat(offre.getTypeCuisine()).isNotNull();
		Assertions.assertThat(offre.getTypeCuisine().getId()).isNotNull().isEqualTo(idTypeCuisine);
		Assertions.assertThat(offre.getTypeCuisine().getTypeCuisine()).isNotNull().isEqualTo(typeCuisine);

		Assertions.assertThat(offre.getHote()).isNotNull();
		Assertions.assertThat(offre.getHote().getIdUtilisateur()).isNotNull().isEqualTo(idUtilisateur);
		Assertions.assertThat(offre.getHote().getNom()).isNotNull().isEqualTo(nom);
		Assertions.assertThat(offre.getHote().getMail()).isNotNull().isEqualTo(mail);

	}

	@Test
	public void getByIdTestEchec() throws Exception {
		Assertions.assertThat(offreDao.getById(null)).isNull();
		Assertions.assertThat(offreDao.getById(Integer.MAX_VALUE)).isNull();
	}

	@Test
	public void sauvegarderTestSucces() throws Exception {

		Offre offre = this.createOffre();

		offreDao.sauvegarder(offre);

		// Id bien généré et set par Mybatis
		Assertions.assertThat(offre.getId()).isNotNull().isPositive();

		// Date de création est mis à la date du jour lors de la création
		Assertions.assertThat(offreDao.getById(offre.getId()).getDateCreation()).isEqualTo(LocalDate.now());
	}

	@Test
	public void sauvegarderTestEchec_TitreNull() throws Exception {
		Offre offre = this.createOffre();
		offre.setTitre(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_PrixNull() throws Exception {
		Offre offre = this.createOffre();
		offre.setPrix(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_NombrePersonneNull() throws Exception {
		Offre offre = this.createOffre();
		offre.setNombrePersonne(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_DureeMinuteNull() throws Exception {
		Offre offre = this.createOffre();
		offre.setDureeMinute(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_DateRepasNull() throws Exception {
		Offre offre = this.createOffre();
		offre.setDateRepas(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_MenuNull() throws Exception {
		Offre offre = this.createOffre();
		offre.setMenu(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_AnimauxNull() throws Exception {
		Offre offre = this.createOffre();
		offre.setAnimaux(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_AdresseIdNull() throws Exception {
		Offre offre = this.createOffre();
		offre.getAdresse().setId(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_TypeCuisineIdNull() throws Exception {
		Offre offre = this.createOffre();
		offre.getTypeCuisine().setId(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_UtilisateurIdNull() throws Exception {
		Offre offre = this.createOffre();
		offre.getHote().setIdUtilisateur(null);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_AdresseNonExistant() throws Exception {
		Offre offre = this.createOffre();
		offre.getAdresse().setId(Integer.MAX_VALUE);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_TypeCuisineNonExistant() throws Exception {
		Offre offre = this.createOffre();
		offre.getTypeCuisine().setId(Integer.MAX_VALUE);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_UtilisateurNonExistant() throws Exception {
		Offre offre = this.createOffre();
		offre.getHote().setIdUtilisateur(Integer.MAX_VALUE);

		try {
			offreDao.sauvegarder(offre);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

}
