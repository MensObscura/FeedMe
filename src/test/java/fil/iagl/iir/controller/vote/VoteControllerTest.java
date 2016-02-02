package fil.iagl.iir.controller.vote;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Vote;
import org.hamcrest.core.IsNull;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VoteControllerTest extends AbstractControllerTest {

  @Override
  @Before
  public void setUp() {
    super.setUp();
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void testSauvegarder() throws Exception {
    Vote vote = createVote();
    JSONObject jo = new JSONObject(vote);

    vote.setId(null);

    mockMvc.perform(
      put("/vote/").contentType(FEED_ME_MEDIA_TYPE).content(jo.toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.data.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.utilisateur.idUtilisateur").value(vote.getUtilisateur().getIdUtilisateur()))
      .andExpect(jsonPath("$.data.offre.id").value(vote.getOffre().getId()))
      .andExpect(jsonPath("$.data.note").value(vote.getNote()));
  }

  @Test
  public void testADejaVote() throws Exception {
    Integer id = 1;

    mockMvc.perform(get("/vote/aDejaVote/{id}", id))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").exists())
      .andExpect(jsonPath("$.data").value(true));
  }

  @Test
  public void testADejaVote_voteInexistant() throws Exception {
    Integer id = 1;
    mockMvc.perform(get("/vote/aDejaVote/{id}", id))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").exists())
      .andExpect(jsonPath("$.data").value(true));
  }
}
