package fil.iagl.iir.service;

import java.util.Arrays;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeSession;

public class OffreServiceTest extends AbstractServiceTest {

	@Mock
	private Offre offre;

	@Mock
	private Utilisateur hote;

	@Test
	public void sauvegarderTestSucces() throws Exception {
		Mockito.when(offre.getHote()).thenReturn(hote);

		this.offreService.sauvegarder(offre);

		Mockito.verify(hote, Mockito.times(1)).setIdUtilisateur(FeedMeSession.getIdUtilisateurConnecte());
		Mockito.verify(offreDao, Mockito.times(1)).sauvegarder(offre);
	}

	@Test(expected = RuntimeException.class)
	public void sauvegarderTestEchec() throws Exception {
		offreService.sauvegarder(null);
		Mockito.verify(offreDao, Mockito.times(0)).sauvegarder(null);
	}

	@Test
	public void afficherTestSucces() throws Exception {
		Integer idOffre = 1;

		Mockito.when(offreDao.getById(idOffre)).thenReturn(offre);

		Assertions.assertThat(this.offreService.afficher(idOffre)).isEqualTo(offre);

	}

	@Test(expected = RuntimeException.class)
	public void afficherTestEchec() throws Exception {
		offreService.afficher(null);
		Mockito.verify(offreDao, Mockito.times(0)).getById(null);
	}

	@Test
	public void listerTestSucces() throws Exception {
		List<Offre> list = Arrays.asList(offre, offre, offre, offre);
		Mockito.when(offreDao.getAll()).thenReturn(list);

		Assertions.assertThat(offreService.lister()).isEqualTo(list);

		Mockito.verify(offreDao, Mockito.times(1)).getAll();
	}

}
