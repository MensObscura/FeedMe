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

    mockMvc.perform(get("/utilisateur/particulier/{id}", id))
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.idUtilisateur").value(id))
      .andExpect(jsonPath("$.nom").value(nom)).andExpect(jsonPath("$.mail").value(mail));

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

    String mail = RandomStringUtils.random(10);
    String nom = RandomStringUtils.random(8);
    String prenom = RandomStringUtils.random(6);
    LocalDate dateNaissance = LocalDate.now().minusYears(RandomUtils.nextInt(20, 30));
    Role role = Role.PARTICULIER;
    String password = RandomStringUtils.random(30);

    utilisateur.setMail(mail);
    utilisateur.setNom(nom);
    utilisateur.setPrenom(prenom);
    utilisateur.setDateNaissance(dateNaissance);

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
      .andExpect(jsonPath("$.dateNaissance").value(dateNaissance.format(DateTimeFormatter.ISO_DATE)));

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
    LocalDate dateNaissance = LocalDate.of(2015, 01, 31);
    particulier.setDateNaissance(dateNaissance);
    particulier.setIdParticulier(1);
    particulier.setIdUtilisateur(utilisateur.getIdUtilisateur());
    particulier.setMail(utilisateur.getMail());
    particulier.setNom(utilisateur.getNom());
    particulier.setPrenom(prenom);

    mockMvc.perform(get("/utilisateur/particulier/profil")).andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.nom").value(utilisateur.getNom()))
      .andExpect(jsonPath("$.mail").value(utilisateur.getMail()))
      .andExpect(jsonPath("$.idUtilisateur").value(utilisateur.getIdUtilisateur()))
      .andExpect(jsonPath("$.prenom").value(prenom))
      .andExpect(jsonPath("$.dateNaissance").value(dateNaissance.toString()));
  }

}
