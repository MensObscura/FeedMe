package fil.iagl.iir.controller.accueil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;

public class AccueilControllerTest extends AbstractControllerTest {

  @Before
  public void setUp() {
    super.setUp();
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void testHome() throws Exception {
    mockMvc.perform(get("/"))
      .andExpect(status().isFound()) // Redirection
      .andExpect(redirectedUrl("index.html"));
  }
}
