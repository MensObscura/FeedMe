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

		// Le mot de passe ne doit pas etre retourné
		assertThat(user.getPassword()).isNull();
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
		String password = "monpassword";
		String mail = "monmail@mail.com";

		utilisateur.setMail(mail);
		utilisateur.setNom(nom);
		utilisateur.setPassword(password);

		Assertions.assertThat(utilisateur.getIdUtilisateur()).isNull();

		utilisateurDao.sauvegarder(utilisateur);

		assertThat(utilisateur.getIdUtilisateur()).isNotNull().isPositive();
		assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mail);
		assertThat(utilisateur.getNom()).isNotNull().isEqualTo(nom);
		assertThat(utilisateur.getPassword()).isNotNull().isEqualTo(password);
	}

	@Test
	public void sauvegarderTestEchec_NomNull() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		String nom = null;
		String password = "monpassword";
		String mail = "monmail@mail.com";

		utilisateur.setMail(mail);
		utilisateur.setNom(nom);
		utilisateur.setPassword(password);

		try {
			utilisateurDao.sauvegarder(utilisateur);
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {

			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderTestEchec_PasswordNull() throws Exception {
		Utilisateur utilisateur = new Utilisateur();
		String nom = "monnom";
		String password = null;
		String mail = "monmail@mail.com";

		utilisateur.setMail(mail);
		utilisateur.setNom(nom);
		utilisateur.setPassword(password);

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
		String password = "monpassword";
		String mail = null;

		utilisateur.setMail(mail);
		utilisateur.setNom(nom);
		utilisateur.setPassword(password);

		try {
			utilisateurDao.sauvegarder(utilisateur);
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

}
