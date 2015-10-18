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
		// Etant donne une ville avec un ID = 1
		Integer id = 1;
		String nom = "Lille";
		String codePostal = "59000";
		Integer paysId = 1;

		// Quand on recupere la ville associee a cet ID
		Ville ville = villeDao.getById(id);

		// Alors on verifie que les informations retournees sont celles
		// attendues
		Assertions.assertThat(ville.getId()).isNotNull().isPositive().isEqualTo(id);
		Assertions.assertThat(ville.getNom()).isNotNull().isEqualTo(nom);
		Assertions.assertThat(ville.getCp()).isNotNull().isEqualTo(codePostal);
		Assertions.assertThat(ville.getPays()).isNotNull();
		Assertions.assertThat(ville.getPays().getId()).isNotNull().isPositive().isEqualTo(paysId);
	}

	@Test
	public void testGetByIdFail() throws Exception {
		// Etant donne qu'aucune ville n'est enregistree avec un ID nul
		// Quand on recupere une ville avec un ID nul
		// Alors la ville retournee est nulle
		Assertions.assertThat(villeDao.getById(null)).isNull();

		// Etant donne qu'aucune ville n'est enregistree avec cet ID
		// Quand on recupere une ville associee a cet ID
		// Alors la ville retrounee est nulle
		Assertions.assertThat(villeDao.getById(Integer.MAX_VALUE)).isNull();
	}

	@Test
	public void testSauvegardeVilleSuccess() throws Exception {
		// Etant donne une ville avec un pays associe
		Ville ville = new Ville();
		String nom = "villeName";
		String cp = "59770";
		Pays pays = new Pays();
		Integer idPays = 1;
		pays.setId(idPays);

		ville.setNom(nom);
		ville.setCp(cp);
		ville.setPays(pays);

		// n'ayant pas son ID encore defini
		Assertions.assertThat(ville.getId()).isNull();

		// Quand on enregistre la ville en base
		villeDao.sauvegarder(ville);

		// Alors on verifie que l'ID de la ville a bien ete genere
		Assertions.assertThat(ville.getId()).isNotNull().isPositive();
		Assertions.assertThat(ville.getNom()).isNotNull().isEqualTo(nom);
		Assertions.assertThat(ville.getCp()).isNotNull().isEqualTo(cp);
		Assertions.assertThat(ville.getPays()).isNotNull();
		Assertions.assertThat(ville.getPays().getId()).isNotNull().isPositive().isEqualTo(idPays);

	}

	@Test
	public void testSauvegardeVilleFailNomNull() throws Exception {
		// Etant donne une ville n'ayant pas de nom
		Ville ville = new Ville();
		String nom = null;
		String cp = "59770";
		Pays pays = new Pays();

		ville.setNom(nom);
		ville.setCp(cp);
		ville.setPays(pays);

		try {
			// Quand on enregistre cette ville en base
			villeDao.sauvegarder(ville);

			// Alors on attend a ce qu'une exception soit lancee
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void testSauvegardeVilleFailCpNull() throws Exception {
		// Etant donne une ville n'ayant pas de code postal
		Ville ville = new Ville();
		String nom = "villeName";
		String cp = null;
		Pays pays = new Pays();

		ville.setNom(nom);
		ville.setCp(cp);
		ville.setPays(pays);

		try {
			// Quand on enregistre cette ville en base
			villeDao.sauvegarder(ville);

			// Alors on attend a ce qu'une exception soit lancee
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void testSauvegardeVilleFailPaysNull() throws Exception {
		// Etant donne une ville n'ayant pas de pays associe
		Ville ville = new Ville();
		String nom = "villeName";
		String cp = "59770";
		Pays pays = null;

		ville.setNom(nom);
		ville.setCp(cp);
		ville.setPays(pays);

		try {
			// Quand on enegistre cette ville en base
			villeDao.sauvegarder(ville);

			// Alors on attend a ce qu'une exception soit lancee
			fail("Doit soulever une exception");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

}
