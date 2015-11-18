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
    Offre offre = createOffre();
    JSONObject jo = new JSONObject(offre);

    offre.setId(null);
    offre.getAdresse().setId(null);
    offre.getAdresse().getVille().setId(null);

    mockMvc.perform(
      put("/offres/").contentType(FEED_ME_MEDIA_TYPE).content(jo.toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.offrePremium").value(offre.isOffrePremium()))
      .andExpect(jsonPath("$.titre").value(offre.getTitre()))
      .andExpect(jsonPath("$.prix").value(offre.getPrix()))
      .andExpect(jsonPath("$.nombrePersonne").value(offre.getNombrePersonne()))
      .andExpect(jsonPath("$.dureeMinute").value(offre.getDureeMinute()))
      .andExpect(jsonPath("$.dateRepas").value(offre.getDateRepas().toString()))
      .andExpect(jsonPath("$.note").value(offre.getNote()))
      .andExpect(jsonPath("$.menu.entree").value(offre.getMenu().getEntree()))
      .andExpect(jsonPath("$.menu.plat").value(offre.getMenu().getPlat()))
      .andExpect(jsonPath("$.menu.dessert").value(offre.getMenu().getDessert()))
      .andExpect(jsonPath("$.menu.boisson").value(offre.getMenu().getBoisson()))
      .andExpect(jsonPath("$.animaux").value(offre.getAnimaux()))
      .andExpect(jsonPath("$.adresse.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.adresse.ville.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.adresse.ville.pays.id").value(offre.getAdresse().getVille().getPays().getId()))
      .andExpect(jsonPath("$.adresse.voie").value(offre.getAdresse().getVoie()))
      .andExpect(jsonPath("$.typeCuisine.id").value(offre.getTypeCuisine().getId()))
      .andExpect(jsonPath("$.hote.idUtilisateur").value(offre.getHote().getIdUtilisateur()));
  }

  @Test
  public void testGetOffres() throws Exception {
    mockMvc.perform(get("/offres"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$").isArray());
  }

  @Test
  public void testGetOffre() throws Exception {
    Integer id = 1;
    Offre offre = offreDao.getById(id);

    mockMvc.perform(get("/offres/" + id))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(offre.getId()))
      .andExpect(jsonPath("$.offrePremium").value(offre.isOffrePremium()))
      .andExpect(jsonPath("$.titre").value(offre.getTitre()))
      .andExpect(jsonPath("$.prix").value(offre.getPrix()))
      .andExpect(jsonPath("$.nombrePersonne").value(offre.getNombrePersonne()))
      .andExpect(jsonPath("$.dureeMinute").value(offre.getDureeMinute()))
      .andExpect(jsonPath("$.dateRepas").value(offre.getDateRepas().toString()))
      .andExpect(jsonPath("$.note").value(offre.getNote()))
      .andExpect(jsonPath("$.menu.entree").value(offre.getMenu().getEntree()))
      .andExpect(jsonPath("$.menu.plat").value(offre.getMenu().getPlat()))
      .andExpect(jsonPath("$.menu.dessert").value(offre.getMenu().getDessert()))
      .andExpect(jsonPath("$.menu.boisson").value(offre.getMenu().getBoisson()))
      .andExpect(jsonPath("$.animaux").value(offre.getAnimaux()))
      .andExpect(jsonPath("$.adresse.id").value(offre.getAdresse().getId()))
      .andExpect(jsonPath("$.adresse.ville.id").value(offre.getAdresse().getVille().getId()))
      .andExpect(jsonPath("$.adresse.ville.pays.id").value(offre.getAdresse().getVille().getPays().getId()))
      .andExpect(jsonPath("$.adresse.voie").value(offre.getAdresse().getVoie()))
      .andExpect(jsonPath("$.typeCuisine.id").value(offre.getTypeCuisine().getId()))
      .andExpect(jsonPath("$.hote.idUtilisateur").value(offre.getHote().getIdUtilisateur()));
  }
}
