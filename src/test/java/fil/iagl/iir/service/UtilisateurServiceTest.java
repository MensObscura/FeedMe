package fil.iagl.iir.service;

import java.time.LocalDate;

import org.fest.assertions.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeException;

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
    Assertions.assertThat(utilisateur.getPremium()).isTrue();
    Assertions.assertThat(utilisateur.getDescription()).isNotNull().isEqualTo(mockUtilisateur.getDescription());
    Assertions.assertThat(utilisateur.getAdresseVisible()).isNotNull().isTrue();

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
    // Etant donné un particulier avec une adresse (voie) modifiée
    Particulier particulier = this.createParticulier();

    // Quand on appelle le service de modification de profil particulier
    utilisateurService.modifierProfil(particulier);

    // Alors on vérifie que l'utilisateurDao a bien été appelé
    Mockito.verify(particulierDao, Mockito.times(1)).modifier(particulier);
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
}
