package fil.iagl.iir.dao.utilisateur;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.dao.AbstractDaoTest;
import fil.iagl.iir.dao.SQLCODE;
import fil.iagl.iir.entite.Utilisateur;



public class UtilisateurDAOTest extends AbstractDaoTest{

	
	
	@Test
	public void getByIdTestSucces() throws Exception {
		Integer id = 1;
		String nom = "toto";
		String password = "tata";
		String email = "toto.toto@gmail.com";
		Utilisateur user = utilisateurDao.getById(id);
	
		Assertions.assertThat(user.getId()).isNotNull().isPositive().isEqualTo(id);
		Assertions.assertThat(user.getMail()).isNotNull().isEqualTo(email);
		Assertions.assertThat(user.getNom()).isNotNull().isEqualTo(nom);
		Assertions.assertThat(user.getPassword()).isNotNull().isEqualTo(password);
	}
	
	@Test
	public void getByIdTestEchec() throws Exception {
		Assertions.assertThat(utilisateurDao.getById(null)).isNull();
		Assertions.assertThat(utilisateurDao.getById(Integer.MAX_VALUE)).isNull();
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
		
		Assertions.assertThat(utilisateur.getId()).isNull();
		
		utilisateurDao.sauvegarder(utilisateur);
		
		Assertions.assertThat(utilisateur.getId()).isNotNull().isPositive();
		Assertions.assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mail);
		Assertions.assertThat(utilisateur.getNom()).isNotNull().isEqualTo(nom);
		Assertions.assertThat(utilisateur.getPassword()).isNotNull().isEqualTo(password);
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
		
		try{
			utilisateurDao.sauvegarder(utilisateur);
			Assertions.fail("Doit soulever une exception");
		}catch(DataIntegrityViolationException dive ){
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
		
		try{
			utilisateurDao.sauvegarder(utilisateur);
			Assertions.fail("Doit soulever une exception");
		}catch(DataIntegrityViolationException dive ){
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
		
		try{
			utilisateurDao.sauvegarder(utilisateur);
			Assertions.fail("Doit soulever une exception");
		}catch(DataIntegrityViolationException dive ){
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}
	
}
