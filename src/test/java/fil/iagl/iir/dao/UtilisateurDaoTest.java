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
		Integer id = 1;
		String nom = "toto";
		String email = "toto.toto@gmail.com";
		Utilisateur user = utilisateurDao.getById(id);

		assertThat(user.getIdUtilisateur()).isNotNull().isPositive().isEqualTo(id);
		assertThat(user.getMail()).isNotNull().isEqualTo(email);
		assertThat(user.getNom()).isNotNull().isEqualTo(nom);
	}

	@Test
	public void getByIdTestEchec() throws Exception {
		assertThat(utilisateurDao.getById(null)).isNull();
		assertThat(utilisateurDao.getById(Integer.MAX_VALUE)).isNull();
	}

	@Test
	public void sauvegarderTestSucces() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		String nom = "monnom";
		String mail = "monmail@mail.com";

		utilisateur.setMail(mail);
		utilisateur.setNom(nom);

		Assertions.assertThat(utilisateur.getIdUtilisateur()).isNull();

		utilisateurDao.sauvegarder(utilisateur);

		assertThat(utilisateur.getIdUtilisateur()).isNotNull().isPositive();
		assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mail);
		assertThat(utilisateur.getNom()).isNotNull().isEqualTo(nom);
	}

	@Test
	public void sauvegarderTestEchec_NomNull() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		String nom = null;
		String mail = "monmail@mail.com";

		utilisateur.setMail(mail);
		utilisateur.setNom(nom);

		try {
			utilisateurDao.sauvegarder(utilisateur);
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {

			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_MailNull() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		String nom = "monnom";
		String mail = null;

		utilisateur.setMail(mail);
		utilisateur.setNom(nom);

		try {
			utilisateurDao.sauvegarder(utilisateur);
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_MailUnique() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		String nom = "monnom";
		String mail = USERNAME_TEST_USER;

		utilisateur.setMail(mail);
		utilisateur.setNom(nom);

		try {
			utilisateurDao.sauvegarder(utilisateur);
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.UNIQUE_VIOLATION);
		}
	}

}
