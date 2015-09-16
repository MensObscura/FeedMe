package fil.iagl.iir.dao;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fil.iagl.iir.dao.adresse.AdresseDao;
import fil.iagl.iir.entite.Adresse;

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
}
