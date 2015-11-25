package fil.iagl.iir.controller.utilisateur;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.core.IsNull;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.AuthentificationParticulier;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Role;
import fil.iagl.iir.entite.Utilisateur;

public class UtilisateurControllerTest extends AbstractControllerTest {

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
      .build();
  }

  @Test
  public void afficherProfilTestSucces() throws Exception {
    Utilisateur utilisateur = createUtilisateur();
    int id = utilisateur.getIdUtilisateur();
    String nom = utilisateur.getNom();
    String mail = utilisateur.getMail();
    Boolean premium = utilisateur.getPremium();
    String description = utilisateur.getDescription();
    Boolean adresseVisible = utilisateur.getAdresseVisible();

    mockMvc.perform(get("/utilisateur/particulier/{id}", id))
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.idUtilisateur").value(id))
      .andExpect(jsonPath("$.premium").value(premium))
      .andExpect(jsonPath("$.nom").value(nom))
      .andExpect(jsonPath("$.mail").value(mail))
      .andExpect(jsonPath("$.description").value(description))
      .andExpect(jsonPath("$.adresseVisible").value(adresseVisible));

  }

  @Test
  public void afficherProfilTestEchec() throws Exception {
    mockMvc.perform(get("/utilisateur/particulier"))
      .andExpect(status().isMethodNotAllowed()); // Correspond à la
    // route PUT mais en
    // GET sans json
  }

  @Test
  public void inscriptionTestSucces() throws Exception {
    Authentification<Particulier> auth = new AuthentificationParticulier();
    Particulier utilisateur = new Particulier();

    String mail = RandomStringUtils.randomAlphanumeric(10);
    String nom = RandomStringUtils.randomAlphanumeric(8);
    String prenom = RandomStringUtils.randomAlphanumeric(6);
    LocalDate dateNaissance = LocalDate.now().minusYears(RandomUtils.nextInt(20, 30));
    Role role = Role.PARTICULIER;
    String password = RandomStringUtils.randomAlphanumeric(30);
    Boolean premium = true;
    String description = RandomStringUtils.randomAlphanumeric(300);
    Boolean adresseVisible = true;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPrenom(prenom);
    utilisateur.setDateNaissance(dateNaissance);
    utilisateur.setPremium(premium);
    utilisateur.setDescription(description);
    utilisateur.setAdresseVisible(adresseVisible);

    auth.setUtilisateur(utilisateur);
    auth.setRole(role);
    auth.setPassword(password);

    JSONObject json = new JSONObject(auth);

    mockMvc.perform(
      put("/utilisateur/particulier").contentType(FEED_ME_MEDIA_TYPE).content(json.toString()))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.idUtilisateur").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.idParticulier").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.nom").value(nom))
      .andExpect(jsonPath("$.prenom").value(prenom))
      .andExpect(jsonPath("$.mail").value(mail))
      .andExpect(jsonPath("$.premium").value(premium))
      .andExpect(jsonPath("$.dateNaissance").value(dateNaissance.format(DateTimeFormatter.ISO_DATE)))
      .andExpect(jsonPath("$.description").value(description))
      .andExpect(jsonPath("$.adresseVisible").value(adresseVisible));

  }

  @Test
  public void inscriptionTestSuccesDescriptionNulle() throws Exception {
    Authentification<Particulier> auth = new AuthentificationParticulier();
    Particulier utilisateur = new Particulier();

    String mail = RandomStringUtils.randomAlphanumeric(10);
    String nom = RandomStringUtils.randomAlphanumeric(8);
    String prenom = RandomStringUtils.randomAlphanumeric(6);
    LocalDate dateNaissance = LocalDate.now().minusYears(RandomUtils.nextInt(20, 30));
    Role role = Role.PARTICULIER;
    String password = RandomStringUtils.randomAlphanumeric(30);
    Boolean premium = true;
    String description = null;
    Boolean adresseVisible = false;

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPrenom(prenom);
    utilisateur.setDateNaissance(dateNaissance);
    utilisateur.setPremium(premium);
    utilisateur.setDescription(description);
    utilisateur.setAdresseVisible(adresseVisible);

    auth.setUtilisateur(utilisateur);
    auth.setRole(role);
    auth.setPassword(password);

    JSONObject json = new JSONObject(auth);

    mockMvc.perform(
      put("/utilisateur/particulier").contentType(FEED_ME_MEDIA_TYPE).content(json.toString()))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.idUtilisateur").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.idParticulier").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.nom").value(nom))
      .andExpect(jsonPath("$.prenom").value(prenom))
      .andExpect(jsonPath("$.mail").value(mail))
      .andExpect(jsonPath("$.premium").value(premium))
      .andExpect(jsonPath("$.dateNaissance").value(dateNaissance.format(DateTimeFormatter.ISO_DATE)))
      .andExpect(jsonPath("$.description").value(description))
      .andExpect(jsonPath("$.adresseVisible").value(adresseVisible));

  }

  @Test
  public void inscriptionTestEchec() throws Exception {
    mockMvc.perform(put("/utilisateur/particulier")).andExpect(status().isBadRequest());
  }

  @Test
  public void afficherProfilEnSessionSuccess() throws Exception {
    Utilisateur utilisateur = createUtilisateur();
    Particulier particulier = new Particulier();
    String prenom = "titi";
    LocalDate dateNaissance = LocalDate.of(2015, 1, 31);
    Boolean adresseVisible = true;

    particulier.setDateNaissance(dateNaissance);
    particulier.setIdParticulier(1);
    particulier.setIdUtilisateur(utilisateur.getIdUtilisateur());
    particulier.setMail(utilisateur.getMail());
    particulier.setNom(utilisateur.getNom());
    particulier.setPrenom(prenom);
    particulier.setPremium(utilisateur.getPremium());
    particulier.setDescription(utilisateur.getDescription());
    particulier.setAdresseVisible(adresseVisible);

    mockMvc.perform(get("/utilisateur/particulier/profil")).andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.nom").value(utilisateur.getNom()))
      .andExpect(jsonPath("$.mail").value(utilisateur.getMail()))
      .andExpect(jsonPath("$.idUtilisateur").value(utilisateur.getIdUtilisateur()))
      .andExpect(jsonPath("$.premium").value(utilisateur.getPremium()))
      .andExpect(jsonPath("$.prenom").value(prenom))
      .andExpect(jsonPath("$.dateNaissance").value(dateNaissance.toString()))
      .andExpect(jsonPath("$.description").value(utilisateur.getDescription()))
      .andExpect(jsonPath("$.adresseVisible").value(utilisateur.getAdresseVisible()));
  }

  @Test
  public void modifierSonProfil() throws Exception {
    AuthentificationParticulier authentificationParticulier = (AuthentificationParticulier) createAuthentificationParticulier();

    LocalDate dateNaissance = LocalDate.now().minusYears(2).minusDays(3).minusMonths(4);
    String voie = "Rue de Toto";
    String description = "Titi";
    String mail = "toto@titi.fr";
    String nom = "Durand";
    String prenom = "David";
    Boolean prenium = !authentificationParticulier.getUtilisateur().getPremium();

    authentificationParticulier.getUtilisateur().setDateNaissance(dateNaissance);
    authentificationParticulier.getUtilisateur().setDescription(description);
    authentificationParticulier.getUtilisateur().setMail(mail);
    authentificationParticulier.getUtilisateur().setNom(nom);
    authentificationParticulier.getUtilisateur().setPrenom(prenom);
    authentificationParticulier.getUtilisateur().setPremium(prenium);
    authentificationParticulier.getUtilisateur().getAdresse().setVoie(voie);

    JSONObject jsonAuthenficationParticulier = new JSONObject(authentificationParticulier);

    mockMvc.perform(put("/utilisateur/particulier/profil").contentType(FEED_ME_MEDIA_TYPE).content(jsonAuthenficationParticulier.toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.dateNaissance").value(dateNaissance.toString()))
      .andExpect(jsonPath("$.description").value(description))
      .andExpect(jsonPath("$.mail").value(mail))
      .andExpect(jsonPath("$.nom").value(nom))
      .andExpect(jsonPath("$.prenom").value(prenom))
      .andExpect(jsonPath("$.premium").value(prenium))
      .andExpect(jsonPath("$.adresse.voie").value(voie));
  }
}
