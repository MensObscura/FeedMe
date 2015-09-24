package fil.iagl.iir.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.Role;
import fil.iagl.iir.outils.SQLCODE;

public class AuthentificationDaoTest extends AbstractDaoTest {

	@Test
	public void getByUsernameTestSucces() throws Exception {
		String password = "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3";
		Authentification authentification = this.authentificationDao.getByUsername(USERNAME_TEST_USER);

		Assertions.assertThat(authentification).isNotNull();
		Assertions.assertThat(authentification.getUtilisateur().getMail()).isNotNull().isEqualTo(USERNAME_TEST_USER);
		Assertions.assertThat(authentification.getPassword()).isNotNull().isEqualTo(password);
		Assertions.assertThat(authentification.getRole()).isNotNull().isEqualTo(Role.PARTICULIER);
	}

	@Test
	public void getByUsernameTestEchec() throws Exception {
		Assertions.assertThat(this.authentificationDao.getByUsername(null)).isNull();
		Assertions.assertThat(this.authentificationDao.getByUsername(RandomStringUtils.random(RANDOM_STRING_SIZE)))
				.isNull();
	}

	@Test
	public void sauvegarderTestSucces() throws Exception {

		Authentification authentification = this.createAuthentification();

		this.authentificationDao.sauvegarder(authentification);

		Assertions.assertThat(this.authentificationDao.getByUsername(authentification.getUtilisateur().getMail()))
				.isNotNull();
	}

	@Test
	public void sauvegarderTestEchec_IdUtilisateurNull() throws Exception {
		Authentification authentification = this.createAuthentification();
		authentification.getUtilisateur().setIdUtilisateur(null);

		try {
			this.authentificationDao.sauvegarder(authentification);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_PasswordNull() throws Exception {
		Authentification authentification = this.createAuthentification();
		authentification.setPassword(null);

		try {
			this.authentificationDao.sauvegarder(authentification);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_RoleNull() throws Exception {
		Authentification authentification = this.createAuthentification();
		authentification.setRole(null);

		try {
			this.authentificationDao.sauvegarder(authentification);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_IdUtilisateurNonExistant() throws Exception {
		Authentification authentification = this.createAuthentification();
		authentification.getUtilisateur().setIdUtilisateur(Integer.MAX_VALUE);

		try {
			this.authentificationDao.sauvegarder(authentification);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.FOREIGN_KEY_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_UsernameUnique() throws Exception {
		Integer idUtilisateur = 1;
		Authentification authentification = this.createAuthentification();
		authentification.getUtilisateur().setIdUtilisateur(idUtilisateur);

		try {
			this.authentificationDao.sauvegarder(authentification);
			Assertions.fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.UNIQUE_VIOLATION);
		}
	}

}
