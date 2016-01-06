package fil.iagl.iir.controller.utilisateur;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
import fil.iagl.iir.entite.Image;
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
      .andExpect(jsonPath("$.data.idUtilisateur").value(id))
      .andExpect(jsonPath("$.data.premium").value(premium))
      .andExpect(jsonPath("$.data.nom").value(nom))
      .andExpect(jsonPath("$.data.mail").value(mail))
      .andExpect(jsonPath("$.data.description").value(description))
      .andExpect(jsonPath("$.data.adresseVisible").value(adresseVisible));

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
      .andExpect(jsonPath("$.data.idUtilisateur").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.idParticulier").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.nom").value(nom))
      .andExpect(jsonPath("$.data.prenom").value(prenom))
      .andExpect(jsonPath("$.data.mail").value(mail))
      .andExpect(jsonPath("$.data.premium").value(premium))
      .andExpect(jsonPath("$.data.dateNaissance").value(dateNaissance.format(DateTimeFormatter.ISO_DATE)))
      .andExpect(jsonPath("$.data.description").value(description))
      .andExpect(jsonPath("$.data.adresseVisible").value(adresseVisible));

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
      .andExpect(jsonPath("$.data.idUtilisateur").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.idParticulier").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.nom").value(nom))
      .andExpect(jsonPath("$.data.prenom").value(prenom))
      .andExpect(jsonPath("$.data.mail").value(mail))
      .andExpect(jsonPath("$.data.premium").value(premium))
      .andExpect(jsonPath("$.data.dateNaissance").value(dateNaissance.format(DateTimeFormatter.ISO_DATE)))
      .andExpect(jsonPath("$.data.description").value(description))
      .andExpect(jsonPath("$.data.adresseVisible").value(adresseVisible));

  }

  @Test
  public void inscriptionTestEchec() throws Exception {
    mockMvc.perform(put("/utilisateur/particulier")).andExpect(status().isBadRequest());
  }

  @Test
  public void afficherProfilEnSessionSuccess() throws Exception {
    this.fausseConnection(USERNAME_TEST_USER, Optional.empty());

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
      .andExpect(jsonPath("$.data.nom").value(utilisateur.getNom()))
      .andExpect(jsonPath("$.data.mail").value(utilisateur.getMail()))
      .andExpect(jsonPath("$.data.idUtilisateur").value(utilisateur.getIdUtilisateur()))
      .andExpect(jsonPath("$.data.premium").value(utilisateur.getPremium()))
      .andExpect(jsonPath("$.data.prenom").value(prenom))
      .andExpect(jsonPath("$.data.dateNaissance").value(dateNaissance.toString()))
      .andExpect(jsonPath("$.data.description").value(utilisateur.getDescription()))
      .andExpect(jsonPath("$.data.adresseVisible").value(utilisateur.getAdresseVisible()));
  }

  @Test
  public void modifierSonProfil() throws Exception {
    Particulier particulier = createParticulier();

    LocalDate dateNaissance = LocalDate.now().minusYears(2).minusDays(3).minusMonths(4);
    String voie = "Rue de Toto";
    String description = "Titi";
    Image image = new Image();
    image.setPath("mon/path/image.jpg");

    particulier.setDateNaissance(dateNaissance);
    particulier.setDescription(description);
    particulier.getAdresse().setVoie(voie);
    particulier.setImage(image);

    JSONObject jsonParticulier = new JSONObject(particulier);

    mockMvc.perform(put("/utilisateur/particulier/profil").contentType(FEED_ME_MEDIA_TYPE).content(jsonParticulier.toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.data.dateNaissance").value(dateNaissance.toString()))
      .andExpect(jsonPath("$.data.description").value(description))
      .andExpect(jsonPath("$.data.adresse.voie").value(voie))
      .andExpect(jsonPath("$.data.image.path").value(image.getPath()));
  }

  @Test
  public void getAllPremiumTestSucces() throws Exception {
    mockMvc.perform(get("/utilisateur/particulier/premium"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").isArray());
  }
  @Test
  public void devenirPreniumTestSucces() throws Exception {
	Particulier p = createParticulier();
	p.setPremium(false);
    JSONObject jsonParticulier = new JSONObject(p);
    assertFalse(p.getPremium());
	utilisateurDao.sauvegarder(p);
	mockMvc.perform(get("/utilisateur/particulier/devenirPrenium").contentType(FEED_ME_MEDIA_TYPE).content(jsonParticulier.toString()))
	.andExpect(status().isOk())
    .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
    .andExpect(jsonPath("$.data.premium").value(true));
  }
  
  @Test
  public void devenirPreniumTestEchec() throws Exception {
	  // Sans données de type particulier
	  mockMvc.perform(get("/utilisateur/particulier/devenirPrenium")).andExpect(status().isBadRequest());
  }
}
