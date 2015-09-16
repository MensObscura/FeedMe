package fil.iagl.iir.dao;

import java.time.LocalDate;
import java.time.Month;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.outils.SQLCODE;

public class ParticulierDaoTest extends AbstractDaoTest {

	@Test
	public void getByIdTestSucces() throws Exception {
		Integer idUtilisateur = 1;
		String nom = "toto";
		String password = "tata";
		String mail = "toto.toto@gmail.com";

		Integer idParticulier = 1;
		String prenom = "titi";
		LocalDate dateNaissance = LocalDate.of(2015, Month.JANUARY, 31);

		Particulier particulier = particulierDao.getById(idParticulier);

		Assertions.assertThat(particulier).isNotNull();
		Assertions.assertThat(particulier.getIdUtilisateur()).isNotNull().isEqualTo(idUtilisateur);
		Assertions.assertThat(particulier.getNom()).isNotNull().isEqualTo(nom);
		Assertions.assertThat(particulier.getPassword()).isNotNull().isEqualTo(password);
		Assertions.assertThat(particulier.getMail()).isNotNull().isEqualTo(mail);

		Assertions.assertThat(particulier.getIdParticulier()).isNotNull().isEqualTo(idParticulier);
		Assertions.assertThat(particulier.getPrenom()).isNotNull().isEqualTo(prenom);
		Assertions.assertThat(particulier.getDateNaissance()).isNotNull().isEqualTo(dateNaissance);
	}

	@Test
	public void getByIdTestEchec() throws Exception {
		Assertions.assertThat(particulierDao.getById(null)).isNull();
		Assertions.assertThat(particulierDao.getById(Integer.MAX_VALUE)).isNull();
	}

	@Test
	public void sauvegarderTestSucces() throws Exception {
		Particulier particulier = new Particulier();
		String prenom = "titi";
		LocalDate dateNaissance = LocalDate.of(2015, Month.JANUARY, 2);
		Integer idUtilisateur = 1;

		particulier.setPrenom(prenom);
		particulier.setDateNaissance(dateNaissance);
		particulier.setIdUtilisateur(idUtilisateur);

		particulierDao.sauvegarder(particulier);

		Assertions.assertThat(particulier.getIdParticulier()).isNotNull().isPositive();
		Assertions.assertThat(particulier.getPrenom()).isNotNull().isEqualTo(prenom);
		Assertions.assertThat(particulier.getDateNaissance()).isNotNull().isEqualTo(dateNaissance);
		Assertions.assertThat(particulier.getIdUtilisateur()).isNotNull().isEqualTo(idUtilisateur);

	}

	@Test
	public void sauvegarderTestEchec_PrenomNull() throws Exception {
		Particulier particulier = new Particulier();
		String prenom = null;
		LocalDate dateNaissance = LocalDate.of(2015, Month.JANUARY, 2);
		Integer idUtilisateur = 1;

		particulier.setPrenom(prenom);
		particulier.setDateNaissance(dateNaissance);
		particulier.setIdUtilisateur(idUtilisateur);

		try {
			particulierDao.sauvegarder(particulier);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_DateNaissanceNull() throws Exception {
		Particulier particulier = new Particulier();
		String prenom = "titi";
		LocalDate dateNaissance = null;
		Integer idUtilisateur = 1;

		particulier.setPrenom(prenom);
		particulier.setDateNaissance(dateNaissance);
		particulier.setIdUtilisateur(idUtilisateur);

		try {
			particulierDao.sauvegarder(particulier);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_IdUtilisateurNull() throws Exception {
		Particulier particulier = new Particulier();
		String prenom = "titi";
		LocalDate dateNaissance = LocalDate.of(2015, Month.JANUARY, 2);
		Integer idUtilisateur = null;

		particulier.setPrenom(prenom);
		particulier.setDateNaissance(dateNaissance);
		particulier.setIdUtilisateur(idUtilisateur);

		try {
			particulierDao.sauvegarder(particulier);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_IdUtilisateurNonExistant() throws Exception {
		Particulier particulier = new Particulier();
		String prenom = "titi";
		LocalDate dateNaissance = LocalDate.of(2015, Month.JANUARY, 2);
		Integer idUtilisateur = Integer.MAX_VALUE;

		particulier.setPrenom(prenom);
		particulier.setDateNaissance(dateNaissance);
		particulier.setIdUtilisateur(idUtilisateur);

		try {
			particulierDao.sauvegarder(particulier);
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

}
