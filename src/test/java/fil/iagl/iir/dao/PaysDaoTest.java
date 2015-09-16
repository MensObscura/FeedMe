package fil.iagl.iir.dao;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import fil.iagl.iir.entite.Pays;

public class PaysDaoTest extends AbstractDaoTest {

	private static final int NB_PAYS = 2;

	@Test
	public void getAllTest() throws Exception {
		Assertions.assertThat(paysDao.getAll()).isNotEmpty().hasSize(NB_PAYS);
	}

	@Test
	public void getByIdTestSucces() throws Exception {
		Integer id = 1;
		String codePays = "FR";
		String nom = "France";

		Pays pays = this.paysDao.getById(id);

		Assertions.assertThat(pays).isNotNull();
		Assertions.assertThat(pays.getId()).isNotNull().isEqualTo(id);
		Assertions.assertThat(pays.getCodePays()).isNotNull().isEqualTo(codePays);
		Assertions.assertThat(pays.getNom()).isNotNull().isEqualTo(nom);
	}

	@Test
	public void getByIdTestEchec() throws Exception {
		Assertions.assertThat(paysDao.getById(null)).isNull();
		Assertions.assertThat(paysDao.getById(Integer.MAX_VALUE)).isNull();
	}

}
