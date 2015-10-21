package fil.iagl.iir.controller.offre;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNull;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.entite.Offre;

public class OffreControllerTest extends AbstractControllerTest {

  @Autowired
  private OffreDao offreDao;

  @Before
  public void setUp() {
    super.setUp();
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void testSauvegarder() throws Exception {
    // Etant donne une offre
    Offre offre = createOffre();
    JSONObject jo = new JSONObject(offre);

    // qui n'a pas encore d'ID defini
    offre.setId(null);
    // dont l'adresse et la ville n'ont pas encore d'ID definis
    offre.getAdresse().setId(null);
    offre.getAdresse().getVille().setId(null);

    // Quand sauvegarde une offre par la route /offres avec le verbe PUT
    mockMvc.perform(
      put("/offres/").contentType(FEED_ME_MEDIA_TYPE).content(jo.toString()))
      // Alors le code de retour vaut 200 (OK),
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      // l'ID de l'offre a bien ete genere,
      .andExpect(jsonPath("$.id").value(IsNull.notNullValue()))
      // les informations recuperees au format JSON sont bien celles de l'offre que l'on souhaitait creer
      .andExpect(jsonPath("$.titre").value(offre.getTitre()))
      .andExpect(jsonPath("$.prix").value(offre.getPrix()))
      .andExpect(jsonPath("$.nombrePersonne").value(offre.getNombrePersonne()))
      .andExpect(jsonPath("$.dureeMinute").value(offre.getDureeMinute()))
      .andExpect(jsonPath("$.dateRepas").value(offre.getDateRepas().toString()))
      .andExpect(jsonPath("$.note").value(offre.getNote()))
      .andExpect(jsonPath("$.menu").value(offre.getMenu()))
      .andExpect(jsonPath("$.animaux").value(offre.getAnimaux()))
      .andExpect(jsonPath("$.typeCuisine.id").value(offre.getTypeCuisine().getId()))
      .andExpect(jsonPath("$.hote.idUtilisateur").value(offre.getHote().getIdUtilisateur()))
      // l'ID de l'adresse a bien ete genere,
      .andExpect(jsonPath("$.adresse.id").value(IsNull.notNullValue()))
      // les informations de l'adresse recuperees au format JSON apres sauvegarde sont bien celles que l'on souhaitait creer,
      .andExpect(jsonPath("$.adresse.voie").value(offre.getAdresse().getVoie()))
      // l'ID de la ville a bien ete genere,
      .andExpect(jsonPath("$.adresse.ville.id").value(IsNull.notNullValue()))
      // les informations de la ville recuperees au format JSON apres sauvegarde sont bien celles que l'on souhaitait creer,
      .andExpect(jsonPath("$.adresse.ville.nom").value(offre.getAdresse().getVille().getNom()))
      .andExpect(jsonPath("$.adresse.ville.cp").value(offre.getAdresse().getVille().getCp()))
      // et les informations du pays recuperees au format JSON apres sauvegarde sont bien celles que l'on souhaitait creer.
      .andExpect(jsonPath("$.adresse.ville.pays.id").value(offre.getAdresse().getVille().getPays().getId()))
      .andExpect(jsonPath("$.adresse.ville.pays.nom").value(offre.getAdresse().getVille().getPays().getNom()));
  }

  @Test
  public void testGetOffres() throws Exception {
    // Quand on accede aux offres par la route /offres avec le verbe GET
    mockMvc.perform(get("/offres"))
      // Alors on s'assure que le code de retour vaut 200 (OK)
      .andExpect(status().isOk())
      // et que l'on recupere un tableau d'offres au format JSON
      .andExpect(jsonPath("$").isArray());
  }

  @Test
  public void testGetOffre() throws Exception {
   // Etant donne une offre
    Integer id = 1;
    Offre offre = offreDao.getById(id);

    // Quand on recupere cette offre par la oute /offres/{id} avec le verbe GET
    mockMvc.perform(get("/offres/" + id))
      // Alors on s'assure que le code de retour vaut 200 (OK)
      .andExpect(status().isOk())
      // et que les informations recuperees au format JSON sont celles attendues
      .andExpect(jsonPath("$.id").value(offre.getId()))
      .andExpect(jsonPath("$.titre").value(offre.getTitre()))
      .andExpect(jsonPath("$.prix").value(offre.getPrix()))
      .andExpect(jsonPath("$.nombrePersonne").value(offre.getNombrePersonne()))
      .andExpect(jsonPath("$.dureeMinute").value(offre.getDureeMinute()))
      .andExpect(jsonPath("$.dateRepas").value(offre.getDateRepas().toString()))
      .andExpect(jsonPath("$.note").value(offre.getNote()))
      .andExpect(jsonPath("$.menu").value(offre.getMenu()))
      .andExpect(jsonPath("$.animaux").value(offre.getAnimaux()))
      .andExpect(jsonPath("$.adresse.id").value(offre.getAdresse().getId()))
      .andExpect(jsonPath("$.adresse.ville.id").value(offre.getAdresse().getVille().getId()))
      .andExpect(jsonPath("$.adresse.ville.pays.id").value(offre.getAdresse().getVille().getPays().getId()))
      .andExpect(jsonPath("$.adresse.voie").value(offre.getAdresse().getVoie()))
      .andExpect(jsonPath("$.typeCuisine.id").value(offre.getTypeCuisine().getId()))
      .andExpect(jsonPath("$.hote.idUtilisateur").value(offre.getHote().getIdUtilisateur()));
  }
}
