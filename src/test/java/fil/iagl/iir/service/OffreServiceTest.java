package fil.iagl.iir.service;

import java.util.Arrays;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.entite.Image;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.outils.FeedMeSession;

public class OffreServiceTest extends AbstractServiceTest {

  @Mock
  private Offre offre;

  @Mock
  private Adresse adresse;

  @Mock
  private Ville ville;

  @Test
  public void sauvegarderTestSucces() throws Exception {
    Image image1 = new Image();
    Image image2 = new Image();
    image1.setId(1);
    image2.setId(2);

    Mockito.when(offre.getNombrePersonne()).thenReturn(1);
    Mockito.when(offre.getAdresse()).thenReturn(adresse);
    Mockito.when(adresse.getVille()).thenReturn(ville);
    Mockito.when(offre.getNombrePersonne()).thenReturn(2);
    Mockito.when(offre.getPremium()).thenReturn(Boolean.TRUE);
    Mockito.when(offre.getImages()).thenReturn(Arrays.asList(image1, image2));

    this.offreService.sauvegarder(offre);

    InOrder order = Mockito.inOrder(adresseServiceMock, offreDao, imageDao);
    order.verify(adresseServiceMock, Mockito.times(1)).sauvegarder(adresse);
    order.verify(offreDao, Mockito.times(1)).sauvegarder(offre);
    order.verify(imageDao, Mockito.times(1)).sauvegarderPourOffre(Mockito.eq(image1.getId()), Mockito.any(Integer.class));
    order.verify(imageDao, Mockito.times(1)).sauvegarderPourOffre(Mockito.eq(image2.getId()), Mockito.any(Integer.class));

  }

  @Test(expected = FeedMeException.class)
  public void sauvegarderTestEchec_PlusieurImageEtNonPremium() throws Exception {
    Image image1 = new Image();
    Image image2 = new Image();
    image1.setId(1);
    image2.setId(2);

    Mockito.when(offre.getNombrePersonne()).thenReturn(1);
    Mockito.when(offre.getPremium()).thenReturn(Boolean.FALSE);
    Mockito.when(offre.getImages()).thenReturn(Arrays.asList(image1, image2));

    this.offreService.sauvegarder(offre);

    Mockito.verify(offreDao, Mockito.never()).sauvegarder(Mockito.any());
    Mockito.verify(imageDao, Mockito.never()).sauvegarderPourOffre(Mockito.any(), Mockito.any());
    Mockito.verify(adresseServiceMock, Mockito.never()).sauvegarder(Mockito.any());
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

  @Test
  public void listerOffresPremiumTestSucces() throws Exception {
    offre.setPremium(Boolean.TRUE);
    List<Offre> list = Arrays.asList(offre, offre, offre, offre);
    Mockito.when(offreDao.getOffresPremium()).thenReturn(list);

    Assertions.assertThat(offreService.listerOffresPremium()).isEqualTo(list);

    Mockito.verify(offreDao, Mockito.times(1)).getOffresPremium();
  }

  @Test
  public void listerOffresParticipeUserConnecteTestSuccess() throws Exception {
    List<Offre> list = Arrays.asList(offre, offre);
    Mockito.when(offreDao.getOffresParticipeUserCourant(FeedMeSession.getIdUtilisateurConnecte())).thenReturn(list);

    Assertions.assertThat(offreService.listerOffresParticipeUserConnecte()).isEqualTo(list);
    Mockito.verify(offreDao, Mockito.times(1)).getOffresParticipeUserCourant(FeedMeSession.getIdUtilisateurConnecte());
  }

}
