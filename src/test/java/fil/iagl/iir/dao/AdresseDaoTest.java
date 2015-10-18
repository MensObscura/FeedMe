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
		// Etant donne une adresse enregistree avec l'ID = 2
		String voie = "4 rue guillaume apollinaire";
		String ville = "Lille";
		String cp = "59000";
		String pays = "France";
		String code_pays = "FR";

		// Quand on recupere l'adresse associee a cet ID
		Adresse adresse = adresseDao.getById(ID_ADRESSE);

		// Alors les donnees de l'adresse sont celles attendues
		assertThat(adresse.getId()).isEqualTo(ID_ADRESSE);
		assertThat(adresse.getVoie()).isEqualTo(voie);
		// les donnees de la ville sont celles attendues
		assertThat(adresse.getVille().getNom()).isEqualTo(ville);
		assertThat(adresse.getVille().getCp()).isEqualTo(cp);
		// les donnees du pays sont celles attendues
		assertThat(adresse.getVille().getPays().getCodePays()).isEqualTo(code_pays);
		assertThat(adresse.getVille().getPays().getNom()).isEqualTo(pays);
	}

	@Test
	public void getByIdTestEchec() throws Exception {
		// Etant donne qu'aucune adresse n'est enregistree avec un ID nul
		// Quand on recupere une adesse ayant un ID nul
		// Alors l'adresse recuperee est nulle
		assertThat(adresseDao.getById(null)).isNull();

		// Etant donne qu'aucune adresse n'est enegistree avec l'ID = 0
		// Quand on recupere une adresse avec cet ID
		// Alors l'adresse recuperee est nulle
		assertThat(adresseDao.getById(ID_ADRESSE_INUTILISE)).isNull();
	}

	@Test
	public void sauvegarderTestSucces() throws Exception {
		// Etant donne une adresse construite a partir d'un pays et d'une ville
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

		// et ayant son ID nul avant insertion
		assertThat(adresse.getId()).isNull();

		// Quand on enregistre cette adresse
		// Alors le nombre de lignes inserees en base est positif
		assertThat(adresseDao.sauvegarder(adresse)).isPositive();
	}

	@Test
	public void sauvegarderAdresseTestEchec_adresseNulle() throws Exception {
		try {
			// Etant donne une adresse nulle
			// Quand on enregistre cette adresse en base
			adresseDao.sauvegarder(null);

			// Alors on attend a ce qu'une exception soit lancee
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderAdresseTestEchec_voieNulle() throws Exception {
		try {
			// Etant donne une adresse n'ayant pas de voie
			Adresse adresse = new Adresse();

			// Quand on enregistre cette adresse
			adresseDao.sauvegarder(adresse);

			// Alors on attend a ce qu'une exception soit lancee
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderAdresseTestEchec_villeNulle() throws Exception {
		try {
			// Etant donne une adresse ayant une ville nulle
			Adresse adresse = new Adresse();
			adresse.setVoie("4 rue guillaume apollinaire");

			// Quand on enregistre cette adresse
			adresseDao.sauvegarder(adresse);

			// Alors on attend a ce qu'une exception soit lancee
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderAdresseTestEchec_CodePostalNul() throws Exception {
		try {
			// Etant donne une adresse ayant un code postal nul
			Adresse adresse = new Adresse();
			adresse.setVoie("4 rue guillaume apollinaire");

			Ville ville = new Ville();
			ville.setNom("Lille");
			adresse.setVille(ville);

			// Quand on enregistre cette adresse
			adresseDao.sauvegarder(adresse);

			// Alors on attend a ce qu'une exception soit lancee
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}

	@Test
	public void sauvegarderAdresseTestEchec_nomVilleNul() throws Exception {
		try {
			// Etant donne une adresse ayant un nom de ville nul
			Adresse adresse = new Adresse();
			adresse.setVoie("4 rue guillaume apollinaire");

			Ville ville = new Ville();
			ville.setCp("59000");
			adresse.setVille(ville);

			// Quand on enregistre cette ville
			adresseDao.sauvegarder(adresse);

			// Alors on attend a ce qu'une exception soit lancee
			fail("Exception - L'adresse est nulle");
		} catch (DataIntegrityViolationException dive) {
			this.assertSQLCode(dive, SQLCODE.NOT_NULL_VIOLATION);
		}
	}
}
