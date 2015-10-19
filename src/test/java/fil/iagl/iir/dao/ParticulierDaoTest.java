package fil.iagl.iir.dao;

import java.time.LocalDate;
import java.time.Month;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Particulier;
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

	/* *********************** BUILDS ************************/

	public Particulier buildParticulier() {
		Particulier particulier = new Particulier();
		particulier.setPrenom(PARTICULIER_PRENOM);
		particulier.setDateNaissance(PARTICULIER_DDN);
		particulier.setIdUtilisateur(UTILISATEUR_ID);

		return particulier;
	}
}
