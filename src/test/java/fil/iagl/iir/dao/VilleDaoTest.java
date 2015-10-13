package fil.iagl.iir.dao;

import static org.junit.Assert.fail;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.entite.Pays;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.SQLCODE;

public class VilleDaoTest extends AbstractDaoTest {

	private static final int NB_VILLE = 2;

	@Test
	public void testGetByIdSuccess() throws Exception {
		Integer id = 1;
		String nom = "Lille";
		String codePostal = "59000";
		Integer paysId = 1;

		Ville ville = villeDao.getById(id);

		Assertions.assertThat(ville.getId()).isNotNull().isPositive().isEqualTo(id);
		Assertions.assertThat(ville.getNom()).isNotNull().isEqualTo(nom);
		Assertions.assertThat(ville.getCp()).isNotNull().isEqualTo(codePostal);
		Assertions.assertThat(ville.getPays()).isNotNull();
		Assertions.assertThat(ville.getPays().getId()).isNotNull().isPositive().isEqualTo(paysId);
	}

	@Test
	public void testGetByIdFail() throws Exception {

		Assertions.assertThat(villeDao.getById(null)).isNull();
		Assertions.assertThat(villeDao.getById(Integer.MAX_VALUE)).isNull();
	}

	@Test
	public void testSauvegardeVilleSuccess() throws Exception {
		Ville ville = new Ville();
		String nom = "villeName";
		String cp = "59770";
		Pays pays = new Pays();
		Integer idPays = 1;
		pays.setId(idPays);

		ville.setNom(nom);
		ville.setCp(cp);
		ville.setPays(pays);

		Assertions.assertThat(ville.getId()).isNull();

		villeDao.sauvegarder(ville);

		Assertions.assertThat(ville.getId()).isNotNull().isPositive();
		Assertions.assertThat(ville.getNom()).isNotNull().isEqualTo(nom);
		Assertions.assertThat(ville.getCp()).isNotNull().isEqualTo(cp);
		Assertions.assertThat(ville.getPays()).isNotNull();
		Assertions.assertThat(ville.getPays().getId()).isNotNull().isPositive().isEqualTo(idPays);

	}

	@Test
	public void testSauvegardeVilleFailNomNull() throws Exception {
		Ville ville = new Ville();
		String nom = null;
		String cp = "59770";
		Pays pays = new Pays();

		ville.setNom(nom);
		ville.setCp(cp);
		ville.setPays(pays);

		try {
			villeDao.sauvegarder(ville);
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void testSauvegardeVilleFailCpNull() throws Exception {
		Ville ville = new Ville();
		String nom = "villeName";
		String cp = null;
		Pays pays = new Pays();

		ville.setNom(nom);
		ville.setCp(cp);
		ville.setPays(pays);

		try {
			villeDao.sauvegarder(ville);
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void testSauvegardeVilleFailPaysNull() throws Exception {
		Ville ville = new Ville();
		String nom = "villeName";
		String cp = "59770";
		Pays pays = null;

		ville.setNom(nom);
		ville.setCp(cp);
		ville.setPays(pays);

		try {
			villeDao.sauvegarder(ville);
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

}
