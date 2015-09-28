package fil.iagl.iir.service;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Utilisateur;

public class UtilisateurServiceTest extends AbstractServiceTest {

	@Test
	public void getByIdTestSucces() throws Exception {
		Integer id = 1;

		Utilisateur mockUtilisateur = this.createUtilisateur();
		Mockito.when(utilisateurDao.getById(id)).thenReturn(mockUtilisateur);

		Utilisateur utilisateur = utilisateurService.getById(id);

		Assertions.assertThat(utilisateur).isNotNull();
		Assertions.assertThat(utilisateur.getIdUtilisateur()).isNotNull().isEqualTo(mockUtilisateur.getIdUtilisateur());
		Assertions.assertThat(utilisateur.getNom()).isNotNull().isEqualTo(mockUtilisateur.getNom());
		Assertions.assertThat(utilisateur.getMail()).isNotNull().isEqualTo(mockUtilisateur.getMail());

		Mockito.verify(utilisateurDao, Mockito.times(1)).getById(id);

	}

	@Test(expected = RuntimeException.class)
	public void getByIdTestEchec() throws Exception {
		utilisateurService.getById(null);
		Mockito.verify(utilisateurDao, Mockito.never()).getById(Mockito.any());
	}

}
