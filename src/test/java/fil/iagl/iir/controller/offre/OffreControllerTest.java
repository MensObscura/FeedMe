package fil.iagl.iir.controller.offre;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

  @Override
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
      .andExpect(jsonPath("$.data.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.premium").value(offre.getPremium()))
      .andExpect(jsonPath("$.data.titre").value(offre.getTitre()))
      .andExpect(jsonPath("$.data.prix").value(offre.getPrix()))
      .andExpect(jsonPath("$.data.nombrePersonne").value(offre.getNombrePersonne()))
      .andExpect(jsonPath("$.data.dureeMinute").value(offre.getDureeMinute()))
      .andExpect(jsonPath("$.data.dateRepas").value(offre.getDateRepas().toString()))
      .andExpect(jsonPath("$.data.note").value(offre.getNote()))
      .andExpect(jsonPath("$.data.menu.entree").value(offre.getMenu().getEntree()))
      .andExpect(jsonPath("$.data.menu.plat").value(offre.getMenu().getPlat()))
      .andExpect(jsonPath("$.data.menu.dessert").value(offre.getMenu().getDessert()))
      .andExpect(jsonPath("$.data.menu.boisson").value(offre.getMenu().getBoisson()))
      .andExpect(jsonPath("$.data.animaux").value(offre.getAnimaux()))
      .andExpect(jsonPath("$.data.adresse.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.adresse.ville.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.adresse.ville.pays.id").value(offre.getAdresse().getVille().getPays().getId()))
      .andExpect(jsonPath("$.data.adresse.voie").value(offre.getAdresse().getVoie()))
      .andExpect(jsonPath("$.data.typeCuisine.id").value(offre.getTypeCuisine().getId()))
      .andExpect(jsonPath("$.data.hote.idUtilisateur").value(offre.getHote().getIdUtilisateur()));
  }

  @Test
  public void testModifier() throws Exception {
    Integer idOffre = 1;
    Offre offre = createOffre();
    offre.setId(idOffre);

    JSONObject jo = new JSONObject(offre);

    mockMvc.perform(
      post("/offres/").contentType(FEED_ME_MEDIA_TYPE).content(jo.toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.data.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.premium").value(offre.getPremium()))
      .andExpect(jsonPath("$.data.titre").value(offre.getTitre()))
      .andExpect(jsonPath("$.data.prix").value(offre.getPrix()))
      .andExpect(jsonPath("$.data.nombrePersonne").value(offre.getNombrePersonne()))
      .andExpect(jsonPath("$.data.dureeMinute").value(offre.getDureeMinute()))
      .andExpect(jsonPath("$.data.dateRepas").value(offre.getDateRepas().toString()))
      .andExpect(jsonPath("$.data.note").value(offre.getNote()))
      .andExpect(jsonPath("$.data.menu.entree").value(offre.getMenu().getEntree()))
      .andExpect(jsonPath("$.data.menu.plat").value(offre.getMenu().getPlat()))
      .andExpect(jsonPath("$.data.menu.dessert").value(offre.getMenu().getDessert()))
      .andExpect(jsonPath("$.data.menu.boisson").value(offre.getMenu().getBoisson()))
      .andExpect(jsonPath("$.data.animaux").value(offre.getAnimaux()))
      .andExpect(jsonPath("$.data.adresse.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.adresse.ville.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.adresse.ville.pays.id").value(offre.getAdresse().getVille().getPays().getId()))
      .andExpect(jsonPath("$.data.adresse.voie").value(offre.getAdresse().getVoie()))
      .andExpect(jsonPath("$.data.typeCuisine.id").value(offre.getTypeCuisine().getId()))
      .andExpect(jsonPath("$.data.hote.idUtilisateur").value(offre.getHote().getIdUtilisateur()));
  }

  @Test
  public void testGetOffres() throws Exception {
    mockMvc.perform(get("/offres"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  public void testGetOffresPremiums() throws Exception {
    mockMvc.perform(get("/offres/premium"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  public void testGetOffre() throws Exception {
    Integer id = 1;
    Offre offre = offreDao.getById(id);

    mockMvc.perform(get("/offres/" + id))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.id").value(offre.getId()))
      .andExpect(jsonPath("$.data.premium").value(offre.getPremium()))
      .andExpect(jsonPath("$.data.titre").value(offre.getTitre()))
      .andExpect(jsonPath("$.data.prix").value(offre.getPrix()))
      .andExpect(jsonPath("$.data.nombrePersonne").value(offre.getNombrePersonne()))
      .andExpect(jsonPath("$.data.dureeMinute").value(offre.getDureeMinute()))
      .andExpect(jsonPath("$.data.dateRepas").value(offre.getDateRepas().toString()))
      .andExpect(jsonPath("$.data.note").value(offre.getNote()))
      .andExpect(jsonPath("$.data.menu.entree").value(offre.getMenu().getEntree()))
      .andExpect(jsonPath("$.data.menu.plat").value(offre.getMenu().getPlat()))
      .andExpect(jsonPath("$.data.menu.dessert").value(offre.getMenu().getDessert()))
      .andExpect(jsonPath("$.data.menu.boisson").value(offre.getMenu().getBoisson()))
      .andExpect(jsonPath("$.data.animaux").value(offre.getAnimaux()))
      .andExpect(jsonPath("$.data.adresse.id").value(offre.getAdresse().getId()))
      .andExpect(jsonPath("$.data.adresse.ville.id").value(offre.getAdresse().getVille().getId()))
      .andExpect(jsonPath("$.data.adresse.ville.pays.id").value(offre.getAdresse().getVille().getPays().getId()))
      .andExpect(jsonPath("$.data.adresse.voie").value(offre.getAdresse().getVoie()))
      .andExpect(jsonPath("$.data.typeCuisine.id").value(offre.getTypeCuisine().getId()))
      .andExpect(jsonPath("$.data.hote.idUtilisateur").value(offre.getHote().getIdUtilisateur()));
  }

  @Test
  public void testListerOffresParticipeUserConnecte() throws Exception {
    mockMvc.perform(get("/offres/aParticipe"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").isArray());
  }

  @Test
  public void testListerOffresCreesUserConnecte() throws Exception {
    mockMvc.perform(get("/offres/aCree"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").isArray());
  }
}
