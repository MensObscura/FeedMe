package fil.iagl.iir.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.constante.CONSTANTE;
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
  private Offre offreOriginal;

  @Mock
  private Adresse adresse;

  @Mock
  private Ville ville;

  @Mock
  private Image imageExistante;

  @Mock
  private Image imageNonExistante;

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
    order.verify(adresseServiceMock, Mockito.times(1)).sauvegarder(offre.getAdresse());
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
    Mockito.when(offreDao.getOffresParticipeUserConnecte(FeedMeSession.getIdUtilisateurConnecte())).thenReturn(list);

    Assertions.assertThat(offreService.listerOffresParticipeUserConnecte()).isEqualTo(list);
    Mockito.verify(offreDao, Mockito.times(1)).getOffresParticipeUserConnecte(FeedMeSession.getIdUtilisateurConnecte());
  }

  @Test
  public void listerOffresCreesUserConnecteTestSuccess() throws Exception {
    List<Offre> list = Arrays.asList(offre, offre);
    Mockito.when(offreDao.getAllOffresByHote(FeedMeSession.getIdUtilisateurConnecte())).thenReturn(list);

    Assertions.assertThat(offreService.listerOffresCreesUserConnecte()).isEqualTo(list);
    Mockito.verify(offreDao, Mockito.times(1)).getAllOffresByHote(FeedMeSession.getIdUtilisateurConnecte());
  }

  @Test
  public void getAllOffresByHoteTestSucces() throws Exception {
    // Etant donné une liste d'offres créées par un utilisateur (ID=1)
    Integer idUtilisateur = 1;
    List<Offre> list = Arrays.asList(offre, offre);
    Mockito.when(offreDao.getAllOffresByHote(idUtilisateur)).thenReturn(list);

    // Quand on récupère la liste des offres dont cet utilisateur est l'hôte
    List<Offre> listeOffres = offreService.getAllOffresByHote(idUtilisateur);

    // Alors on vérifie que la liste est correcte
    Assertions.assertThat(listeOffres).isNotNull().isEqualTo(list);
    // Et que la couche DAO a bien été appelée
    Mockito.verify(offreDao, Mockito.times(1)).getAllOffresByHote(idUtilisateur);
  }

  @Test
  public void listerOffresEnCoursByHoteTestSuccess_moins2heures() throws Exception {
    List<Offre> list = offreService.listerOffresEnCoursByHote(FeedMeSession.getIdUtilisateurConnecte());

    Assertions.assertThat(list).isNotNull();

    Offre o = offre;
    o.setDateRepas(LocalDateTime.now().minusHours(2));
    offreDao.sauvegarder(o);
    // Normalement, l'offre ajoute est perimee... Par consequent, elle ne sera pas comptee.
    Mockito.when(offreService.listerOffresEnCoursByHote(FeedMeSession.getIdUtilisateurConnecte())).thenReturn(list);
  }

  @Test
  public void listerOffresEnCoursByHoteTestSuccess_plus2jours() throws Exception {
    List<Offre> list = offreService.listerOffresEnCoursByHote(FeedMeSession.getIdUtilisateurConnecte());

    Assertions.assertThat(list).isNotNull();

    Offre o = offre;
    o.setDateRepas(LocalDateTime.now().plusDays(2));
    offreDao.sauvegarder(o);
    // Normalement, l'offre n'est pas perimee donc il y en a une de plus...
    list.add(o);
    Mockito.when(offreService.listerOffresEnCoursByHote(FeedMeSession.getIdUtilisateurConnecte())).thenReturn(list);
  }

  @Test
  public void listerOffresEnCoursByHoteTestEchec() throws Exception {
    // Quand on appelle le service d'Offre pour récupèrer les offres en cours d'un hôte
    // avec un ID d'utilisateur null ou inconnu
    // Alors on obtient une liste d'offres vide
	  Mockito.when(offreService.listerOffresEnCoursByHote(null)).thenReturn(new LinkedList<Offre>());
	  Mockito.when(offreService.listerOffresEnCoursByHote(-1)).thenReturn(new LinkedList<Offre>());
  }

  @Test
  public void modifierOffreTestSucces() throws Exception {
    Integer idOffre = 1;
    Integer idImageExistante = 1;
    Integer idImageNonExistante = 2;
    Integer nbConvive = 8;
    Mockito.when(imageExistante.getId()).thenReturn(idImageExistante);
    Mockito.when(imageNonExistante.getId()).thenReturn(idImageNonExistante);
    Mockito.when(offre.getId()).thenReturn(idOffre);
    Mockito.when(offre.getNombrePersonne()).thenReturn(nbConvive);
    Mockito.when(offre.getAdresse()).thenReturn(adresse);
    Mockito.when(offre.getPremium()).thenReturn(Boolean.TRUE);
    Mockito.when(offre.getImages()).thenReturn(Arrays.asList(imageNonExistante, imageExistante));
    Mockito.when(offreDao.getById(idOffre)).thenReturn(offreOriginal);
    Mockito.when(offreOriginal.getDateRepas()).thenReturn(LocalDateTime.now().plusHours(CONSTANTE.NB_HEURE_POUR_CHANGER_OFFRE * 2));

    this.offreService.modifier(offre);

    InOrder order = Mockito.inOrder(offreDao, adresseServiceMock, imageDao);
    order.verify(adresseServiceMock, Mockito.times(1)).sauvegarder(adresse);
    order.verify(imageDao, Mockito.times(1)).supprimerPourOffre(idOffre);
    order.verify(imageDao, Mockito.times(1)).sauvegarderPourOffre(idImageNonExistante, idOffre);
    order.verify(imageDao, Mockito.times(1)).sauvegarderPourOffre(idImageExistante, idOffre);
    order.verify(offreDao, Mockito.times(1)).modifier(offre);
  }

  @Test(expected = FeedMeException.class)
  public void modifierOffreTestEchec_OffreNull() throws Exception {
    this.offreService.modifier(null);
  }

  @Test(expected = FeedMeException.class)
  public void modifierOffreTestEchec_NbNombrePersonneNull() throws Exception {
    Mockito.when(offre.getNombrePersonne()).thenReturn(0);
    this.offreService.modifier(offre);
  }

  @Test(expected = FeedMeException.class)
  public void modifierOffreTestEchec_PlusQueUneImageEtNonPremium() throws Exception {
    Image image1 = new Image();
    Image image2 = new Image();
    image1.setId(1);
    image2.setId(2);

    Mockito.when(offre.getNombrePersonne()).thenReturn(1);
    Mockito.when(offre.getPremium()).thenReturn(Boolean.FALSE);
    Mockito.when(offre.getImages()).thenReturn(Arrays.asList(image1, image2));

    this.offreService.modifier(offre);
  }

  @Test(expected = FeedMeException.class)
  public void modifierOffreTestEchec_TropTard() throws Exception {
    Integer idOffre = 1;
    Integer nbConvive = 8;
    Mockito.when(offre.getNombrePersonne()).thenReturn(nbConvive);
    Mockito.when(offre.getId()).thenReturn(idOffre);
    Mockito.when(offreDao.getById(idOffre)).thenReturn(offreOriginal);
    Mockito.when(offreOriginal.getDateRepas()).thenReturn(LocalDateTime.now().plusHours(CONSTANTE.NB_HEURE_POUR_CHANGER_OFFRE - 1));

    this.offreService.modifier(offre);
  }

}
