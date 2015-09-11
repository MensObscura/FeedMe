package fil.iagl.iir.service.utilisateur;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

import fil.iagl.iir.service.AbstractServiceTest;


public class UtilisateurServiceTest extends AbstractServiceTest {
	
	
	
	@Test
	public void getByIdTestSucces() throws Exception {
		Integer id = 1;
		Assertions.assertThat(utilisateurService.getById(id)).isNotNull();
	}
	
	@Test(expected=RuntimeException.class)
	public void getByIdTestEchec() throws Exception {
		utilisateurService.getById(null);
	}
}
