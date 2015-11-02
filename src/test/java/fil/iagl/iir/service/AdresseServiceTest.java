package fil.iagl.iir.service;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.entite.Ville;
import fil.iagl.iir.outils.FeedMeException;

public class AdresseServiceTest extends AbstractServiceTest {
  @Mock
  private Adresse adresse;

  @Mock
  private Ville ville;

  @Test
  public void sauvegarderTestSuccess() throws Exception {
    // Etant donne qu'il existe une adresse qui contient une ville
    Mockito.when(adresse.getVille()).thenReturn(ville);

    // Quand je sauvegarde cette adresse
    this.adresseService.sauvegarder(adresse);

    // Alors tout d'abord la ville est sauvegardee, puis l'adresse
    InOrder inOrder = Mockito.inOrder(villeDao, adresseDao);
    inOrder.verify(villeDao, Mockito.times(1)).sauvegarder(ville);
    inOrder.verify(adresseDao, Mockito.times(1)).sauvegarder(adresse);
  }

  @Test
  public void sauvegarderTestEchecAdresseNull() throws Exception {
    // Etant donne que l'adresse est nulle
    // Quand je sauvegarde cette adresse
    try {
      adresseService.sauvegarder(null);
    } catch (Exception e) {
      Assertions.assertThat(e).isInstanceOf(FeedMeException.class).hasMessage("L'adresse ne peut pas etre null");
    }
    // Alors la fonction de sauvegarde n'est jamais appellée et une exception est levée
    Mockito.verify(adresseDao, Mockito.never()).sauvegarder(Mockito.any());
  }

  @Test
  public void sauvegarderTestEchecVilleNull() throws Exception {
    // Etant donne que la ville associée à l'adresse est nulle
    // Quand je sauvegarde cette adresse
    try {
      adresseService.sauvegarder(adresse);
    } catch (Exception e) {
      Assertions.assertThat(e).isInstanceOf(FeedMeException.class).hasMessage("La ville ne peut pas etre null");
    }
    // Alors la fonction de sauvegarde n'est jamais appellée et une exception est levée
    Mockito.verify(adresseDao, Mockito.never()).sauvegarder(Mockito.any());
  }
}
