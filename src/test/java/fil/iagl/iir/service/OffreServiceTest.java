package fil.iagl.iir.service;

import java.util.Arrays;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.FeedMeException;

public class OffreServiceTest extends AbstractServiceTest {

  @Mock
  private Offre offre;

  @Mock
  private Adresse adresse;

  @Mock
  private Ville ville;

  @Test
  public void sauvegarderTestSucces() throws Exception {
    Mockito.when(offre.getNombrePersonne()).thenReturn(1);
    Mockito.when(offre.getAdresse()).thenReturn(adresse);
    Mockito.when(adresse.getVille()).thenReturn(ville);

    this.offreService.sauvegarder(offre);

    InOrder order = Mockito.inOrder(villeDao, adresseDao, offreDao);
    order.verify(villeDao, Mockito.times(1)).sauvegarder(ville);
    order.verify(adresseDao, Mockito.times(1)).sauvegarder(adresse);
    order.verify(offreDao, Mockito.times(1)).sauvegarder(offre);

  }

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec() throws Exception {
    offreService.sauvegarder(null);
    Mockito.verify(offreDao, Mockito.never()).sauvegarder(Mockito.any());
  }

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec_NombreConvivesZero() throws Exception {
    Mockito.when(offre.getNombrePersonne()).thenReturn(0);

    this.offreService.sauvegarder(offre);

    Mockito.verify(offreDao, Mockito.never()).sauvegarder(Mockito.any());
  }

  @Test
  public void afficherTestSucces() throws Exception {
    Integer idOffre = 1;

    Mockito.when(offreDao.getById(idOffre)).thenReturn(offre);

    Assertions.assertThat(this.offreService.afficher(idOffre)).isEqualTo(offre);

  }

  @Test(expected = FeedMeException.class)
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
