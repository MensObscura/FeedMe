package fil.iagl.iir.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.entite.Vote;
import fil.iagl.iir.outils.FeedMeException;

public class UtilisateurServiceTest extends AbstractServiceTest {

  @Mock
  private AdresseService adresseService;

  @Mock
  private OffreService offreService;

  @Mock
  private VoteService voteService;

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
    Assertions.assertThat(utilisateur.getPremium()).isTrue();
    Assertions.assertThat(utilisateur.getDescription()).isNotNull().isEqualTo(mockUtilisateur.getDescription());
    Assertions.assertThat(utilisateur.getAdresseVisible()).isNotNull().isTrue();
    Assertions.assertThat(utilisateur.getImage()).isNotNull().isEqualTo(mockUtilisateur.getImage());

    Mockito.verify(utilisateurDao, Mockito.times(1)).getById(id);

  }

  @Test(expected = FeedMeException.class)
  public void getByIdTestEchec() throws Exception {
    utilisateurService.getById(null);
    Mockito.verify(utilisateurDao, Mockito.never()).getById(Mockito.any());
  }

  @Test
  public void getParticulierByUtilisateurIdTestSucces() throws Exception {
    Integer idUtilisateur = 1;

    Particulier mockParticulier = this.createParticulier();
    Mockito.when(particulierDao.getParticulierByUtilisateurId(idUtilisateur)).thenReturn(mockParticulier);

    Particulier particulier = utilisateurService.getParticulierByUtilisisateurId(idUtilisateur);

    Assertions.assertThat(particulier).isNotNull();
    Assertions.assertThat(particulier.getIdUtilisateur()).isNotNull().isEqualTo(mockParticulier.getIdUtilisateur());
    Assertions.assertThat(particulier.getIdParticulier()).isNotNull().isEqualTo(mockParticulier.getIdParticulier());
    Assertions.assertThat(particulier.getPremium()).isTrue();
    Assertions.assertThat(particulier.getNom()).isNotNull().isEqualTo(mockParticulier.getNom());
    Assertions.assertThat(particulier.getPrenom()).isNotNull().isEqualTo(mockParticulier.getPrenom());
    Assertions.assertThat(particulier.getMail()).isNotNull().isEqualTo(mockParticulier.getMail());
    Assertions.assertThat(particulier.getDateNaissance()).isNotNull().isEqualsToByComparingFields(LocalDate.now().minusYears(20));
    Assertions.assertThat(particulier.getDescription()).isNotNull().isEqualTo(mockParticulier.getDescription());
    Assertions.assertThat(particulier.getAdresseVisible()).isNotNull().isTrue();

    Mockito.verify(particulierDao, Mockito.times(1)).getParticulierByUtilisateurId(idUtilisateur);
  }

  @Test(expected = FeedMeException.class)
  public void getParticulierByUtilisisateurIdTestEchec() throws Exception {
    utilisateurService.getParticulierByUtilisisateurId(null);
    Mockito.verify(utilisateurDao, Mockito.never()).getById(Mockito.anyInt());
  }

  @Test
  public void modifierProfilTestSucces() throws Exception {
    // Etant donné un particulier avec une adresse (voie) modifiée et une image
    Particulier particulier = this.createParticulier();

    // Quand on appelle le service de modification de profil particulier
    utilisateurService.modifierProfil(particulier);

    // Alors on vérifie que l'utilisateurDao, ImageDao ont bien été appelés
    Mockito.verify(particulierDao, Mockito.times(1)).modifier(particulier);
    Mockito.verify(adresseService, Mockito.times(1)).sauvegarder(particulier.getAdresse());
  }

  @Test(expected = FeedMeException.class)
  public void modifierProfilTestEchec() throws Exception {
    // Etant donné un particulier nul
    // Quand on appelle le service de modification de profil particulier
    utilisateurService.modifierProfil(null);
    // Alors une FeedMeException est lancée
    // et on vérifie que l'utilisateurDao n'est jamais appelé
    Mockito.verify(particulierDao, Mockito.never()).modifier(Mockito.any(Particulier.class));
  }

  @Test
  public void getAllPremiumTestSucces() throws Exception {
    // Quand on appel le service pour recuperer tous les premium
    utilisateurService.getAllPremium();
    // On verifie que ce service appel la dao adéquate
    Mockito.verify(particulierDao, Mockito.times(1)).getAllPremium();
  }

  @Test
  public void devenirPreniumTestSucces() {
    Utilisateur utilisateur = this.createUtilisateur();
    utilisateur.setPremium(false);
    utilisateurService.devenirPrenium(utilisateur);
    Mockito.verify(utilisateurDao, Mockito.times(1)).devenirPrenium(utilisateur);
  }

  @Test(expected = FeedMeException.class)
  public void devenirPreniumTestEchec_dejaPrenium() {
    Utilisateur utilisateur = this.createUtilisateur();
    try {
      utilisateurService.devenirPrenium(utilisateur);
    } catch (FeedMeException fme) {
      Mockito.verify(utilisateurDao, Mockito.never()).devenirPrenium(utilisateur);
      ;
      throw fme;
    }
  }

  @Test(expected = FeedMeException.class)
  public void devenirPreniumTestEchec_null() {
    try {
      utilisateurService.devenirPrenium(null);
    } catch (FeedMeException fme) {
      Mockito.verify(utilisateurDao, Mockito.never()).devenirPrenium(null);
      ;
      throw fme;
    }
  }

  @Test
  public void getNoteTestSucces() throws Exception {
    // Mock de l'hote
    Integer idUtilisateur = 1;
    Mockito.when(utilisateurDao.getById(idUtilisateur)).thenReturn(createUtilisateur());

    // Mock des offres
    Mockito.when(this.offreService.getAllOffresByHote(idUtilisateur)).thenReturn(buildListeOffres());

    // Mock des votes
    Mockito.when(this.voteService.getVotesByOffre(Mockito.anyInt())).thenReturn(buildListeVotes());

    // Quand on appelle le service pour récupèrer un utilisateur
    Utilisateur utilisateur = utilisateurService.getById(idUtilisateur);

    // Alors on vérifie que l'utilisateur a bien une note moyenne (2 et 5 => 3.5 => 35)
    Assertions.assertThat(utilisateur.getNote()).isNotNull().isPositive().isEqualTo(35);
    Mockito.verify(utilisateurDao, Mockito.times(1)).getById(idUtilisateur);
    Mockito.verify(this.offreService, Mockito.times(1)).getAllOffresByHote(idUtilisateur);
    Mockito.verify(this.voteService, Mockito.times(2)).getVotesByOffre(Mockito.anyInt());
  }

  @Test
  public void getNoteTestSucces_plusieursOffresAucuneNote() throws Exception {
    // Mock de l'hote
    Integer idUtilisateur = 1;
    Mockito.when(utilisateurDao.getById(idUtilisateur)).thenReturn(createUtilisateur());

    // Mock des offres
    Mockito.when(this.offreService.getAllOffresByHote(idUtilisateur)).thenReturn(buildListeOffres());

    // Mock des votes
    Mockito.when(this.voteService.getVotesByOffre(Mockito.anyInt())).thenReturn(new ArrayList<Vote>());

    // Quand on appelle le service pour récupèrer un utilisateur
    Utilisateur utilisateur = utilisateurService.getById(idUtilisateur);

    // Alors on vérifie que l'utilisateur a bien une note moyenne valant 0
    Assertions.assertThat(utilisateur.getNote()).isNotNull().isEqualTo(0);
    Mockito.verify(utilisateurDao, Mockito.times(1)).getById(idUtilisateur);
    Mockito.verify(this.offreService, Mockito.times(1)).getAllOffresByHote(idUtilisateur);
    Mockito.verify(this.voteService, Mockito.times(2)).getVotesByOffre(Mockito.anyInt());
  }

  @Test
  public void getNoteTestSucces_aucuneOffre() throws Exception {
    // Mock de l'hote
    Integer idUtilisateur = 1;
    Mockito.when(utilisateurDao.getById(idUtilisateur)).thenReturn(createUtilisateur());

    // Mock des offres
    Mockito.when(this.offreService.getAllOffresByHote(idUtilisateur)).thenReturn(new ArrayList<Offre>());

    // Quand on appelle le service pour récupèrer un utilisateur
    Utilisateur utilisateur = utilisateurService.getById(idUtilisateur);

    // Alors on vérifie que l'utilisateur a bien une note moyenne valant 0
    Assertions.assertThat(utilisateur.getNote()).isNotNull().isEqualTo(0);
    Mockito.verify(utilisateurDao, Mockito.times(1)).getById(idUtilisateur);
    Mockito.verify(this.offreService, Mockito.times(1)).getAllOffresByHote(idUtilisateur);
    Mockito.verify(this.voteService, Mockito.never()).getVotesByOffre(Mockito.anyInt());
  }

  /* ***************************************** BUILDS ******************************************/

  private List<Offre> buildListeOffres() {
    List<Offre> offres = new ArrayList<Offre>();
    Offre offre1 = createOffre();
    Offre offre2 = createOffre();
    offre1.setId(1);
    offre2.setId(2);
    offres.add(offre1);
    offres.add(offre2);
    return offres;
  }

  private List<Vote> buildListeVotes() {
    List<Vote> votes = new ArrayList<Vote>();
    Vote vote1 = createVote();
    Vote vote2 = createVote();
    vote1.setNote(2);
    vote2.setNote(5);
    votes.add(vote1);
    votes.add(vote2);
    return votes;
  }

}
