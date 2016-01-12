package fil.iagl.iir.controller.vote;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNull;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Vote;

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
}
