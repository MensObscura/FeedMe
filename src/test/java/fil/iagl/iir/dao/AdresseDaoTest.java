package fil.iagl.iir.dao;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.dao.adresse.AdresseDao;
import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.entite.Pays;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.SQLCODE;

public class AdresseDaoTest extends AbstractDaoTest {

	@Autowired
	private AdresseDao adresseDao;

	private static final Integer ID_ADRESSE = 2;
	private static final Integer ID_ADRESSE_INUTILISE = 0;

	@Test
	public void getByIdTestSuccess() throws Exception {
		// Donnees de l'adresse a recuperer
		String voie = "4 rue guillaume apollinaire";
		String ville = "Lille";
		String cp = "59000";
		String pays = "France";
		String code_pays = "FR";

		Adresse adresse = adresseDao.getById(ID_ADRESSE);

		// Verificatiion de l'adresse
		assertThat(adresse.getId()).isEqualTo(ID_ADRESSE);
		assertThat(adresse.getVoie()).isEqualTo(voie);
		// Verification de la ville
		assertThat(adresse.getVille().getNom()).isEqualTo(ville);
		assertThat(adresse.getVille().getCp()).isEqualTo(cp);
		// Verification du pays
		assertThat(adresse.getVille().getPays().getCodePays()).isEqualTo(code_pays);
		assertThat(adresse.getVille().getPays().getNom()).isEqualTo(pays);
	}

	@Test
	public void getByIdTestEchec() throws Exception {
		assertThat(adresseDao.getById(null)).isNull();
		assertThat(adresseDao.getById(ID_ADRESSE_INUTILISE)).isNull();
	}

	@Test
	public void sauvegarderTestSucces() throws Exception {
		Pays pays = new Pays();
		pays.setCodePays("FR");
		pays.setNom("France");

		Ville ville = new Ville();
		ville.setId(1);
		ville.setCp("59000");
		ville.setNom("Lille");
		ville.setPays(pays);

		Adresse adresse = new Adresse();
		adresse.setVoie("12 rue du marche");
		adresse.setVille(ville);
		
		
		assertThat(adresse.getId()).isNull();

		assertThat(adresseDao.sauvegarder(adresse)).isPositive();
	}

	@Test
	public void sauvegarderAdresseTestEchec_adresseNulle() throws Exception {
		try {
			adresseDao.sauvegarder(null);
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderAdresseTestEchec_voieNulle() throws Exception {
		try {
			Adresse adresse = new Adresse();

			adresseDao.sauvegarder(adresse);
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderAdresseTestEchec_villeNulle() throws Exception {
		try {
			Adresse adresse = new Adresse();
			adresse.setVoie("4 rue guillaume apollinaire");

			adresseDao.sauvegarder(adresse);
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderAdresseTestEchec_CodePostalNul() throws Exception {
		try {
			Adresse adresse = new Adresse();
			adresse.setVoie("4 rue guillaume apollinaire");
			
			Ville ville = new Ville();
			ville.setNom("Lille");
			adresse.setVille(ville);

			adresseDao.sauvegarder(adresse);
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}
	
	@Test
	public void sauvegarderAdresseTestEchec_nomVilleNul() throws Exception {
		try {
			Adresse adresse = new Adresse();
			adresse.setVoie("4 rue guillaume apollinaire");
			
			Ville ville = new Ville();
			ville.setCp("59000");
			adresse.setVille(ville);

			adresseDao.sauvegarder(adresse);
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}
}
