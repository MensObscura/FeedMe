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

public class UtilisateurControllerTest extends AbstractControllerTest {

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
      .build();
  }

  @Test
  public void afficherProfilTestSucces() throws Exception {
    // Etant donne qu'il existe un particulier
    Particulier particulier = createParticulier();

    // Quand on accede au profil d'un particulier par la route /utilisateur/particulier/{id} avec le verbe GET
    mockMvc.perform(get("/utilisateur/particulier/{id}", particulier.getIdUtilisateur()))
      // Alors on s'assure que le code de retour vaut 200 (OK)
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      // et que les informations recuperees au format JSON sont bien celles attendues
      .andExpect(jsonPath("$.idUtilisateur").value(particulier.getIdUtilisateur()))
      .andExpect(jsonPath("$.idParticulier").value(particulier.getIdParticulier()))
      .andExpect(jsonPath("$.nom").value(particulier.getNom()))
      .andExpect(jsonPath("$.prenom").value(particulier.getPrenom()))
      .andExpect(jsonPath("$.mail").value(particulier.getMail()))
      .andExpect(jsonPath("$.dateNaissance").value(particulier.getDateNaissance().format(DateTimeFormatter.ISO_DATE)));

  }

  @Test
  public void afficherProfilTestEchec() throws Exception {
    // Quand on accede a la route /utilisateur/particulier avec le verbe GET
    // sans passer l'ID d'un utilisateur en parametre
    mockMvc.perform(get("/utilisateur/particulier"))
      // Alors on s'assure que le code de retour vaut 405
      .andExpect(status().isMethodNotAllowed());
  }

  @Test
  public void inscriptionTestSucces() throws Exception {
    // Etant donne un particulier a sauvegarder
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

    // Quand on sauvegarde ce particulier par la route /utilisateur/particulier avec le verbe PUT
    mockMvc.perform(
      put("/utilisateur/particulier").contentType(FEED_ME_MEDIA_TYPE).content(json.toString()))
      .andDo(print())
      // Alors on s'assure que le code de retour vaut 200(OK)
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      // et que les informations retournees apres la sauvegarde au format JSON sont celles que l'on souhaitait sauvegarder.
      .andExpect(jsonPath("$.idUtilisateur").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.idParticulier").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.nom").value(nom))
      .andExpect(jsonPath("$.prenom").value(prenom))
      .andExpect(jsonPath("$.mail").value(mail))
      .andExpect(jsonPath("$.dateNaissance").value(dateNaissance.format(DateTimeFormatter.ISO_DATE)));
  }

  @Test
  public void inscriptionTestEchec() throws Exception {
    // Quand on sauvegarde un particulier par la route /utilisateur/particulier avec el verbe PUT
    // sans qu'aucun particulier ne soit passe en parametre
    mockMvc.perform(put("/utilisateur/particulier"))
      // Alors on s'assure que le code de retour vaut 400 (Mauvaise syntaxe de la requete)
      .andExpect(status().isBadRequest());
  }

  @Test
  public void afficherProfilEnSessionSuccess() throws Exception {
    // Etant donne un particulier
    Particulier particulier = createParticulier();

    // Quand on accede au profil d'un particulier en session par la route /utilisateur/particulier/profil avec le verbe GET
    mockMvc.perform(get("/utilisateur/particulier/profil"))
      // Alors on s'assure que le code de retour vaut 200 (OK)
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.nom").value(particulier.getNom()))
      .andExpect(jsonPath("$.mail").value(particulier.getMail()))
      .andExpect(jsonPath("$.idUtilisateur").value(particulier.getIdUtilisateur()))
      .andExpect(jsonPath("$.prenom").value(particulier.getPrenom()))
      .andExpect(jsonPath("$.dateNaissance").value(particulier.getDateNaissance().toString()));
  }

}
