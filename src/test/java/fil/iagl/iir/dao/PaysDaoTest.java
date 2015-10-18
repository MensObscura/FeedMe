package fil.iagl.iir.dao;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import fil.iagl.iir.entite.Pays;

public class PaysDaoTest extends AbstractDaoTest {

	private static final int NB_PAYS = 2;

	@Test
	public void getAllTest() throws Exception {
		// Etant donne qu'il existe NB_PAYS enregistres en base
		// Quand on recupere la liste des pays sauvegardes
		// Alors la liste retournee est de taille NB_PAYS
		Assertions.assertThat(paysDao.getAll()).isNotEmpty().hasSize(NB_PAYS);
	}

	@Test
	public void getByIdTestSucces() throws Exception {
		// Etant donne un pays avec un ID =1
		Integer id = 1;
		String codePays = "FR";
		String nom = "France";

		// Quand on recupere le pays avec cet ID
		Pays pays = this.paysDao.getById(id);

		// Alors les informations recuperees sont celles attendues
		Assertions.assertThat(pays).isNotNull();
		Assertions.assertThat(pays.getId()).isNotNull().isEqualTo(id);
		Assertions.assertThat(pays.getCodePays()).isNotNull().isEqualTo(codePays);
		Assertions.assertThat(pays.getNom()).isNotNull().isEqualTo(nom);
	}

	@Test
	public void getByIdTestEchec() throws Exception {
		// Etant donne qu'aucun pays n'est enregistre avec un ID nul
		// Quand on recupere un pays avec un ID nul
		// Alors le pays recupere est nul
		Assertions.assertThat(paysDao.getById(null)).isNull();

		// Etant donne qu'aucun pays n'est enregistre avec cet ID
		// Quand on recupere un pays avec cet ID
		// Alors le pays recupere est nul
		Assertions.assertThat(paysDao.getById(Integer.MAX_VALUE)).isNull();
	}

}
