package fil.iagl.iir.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fil.iagl.iir.FeedMeConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FeedMeConfiguration.class })
@WebAppConfiguration 
public class UtilisateurControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void afficherProfilTestSucces() throws Exception {
		mockMvc.perform(get("/utilisateur/particulier/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	public void afficherProfilTestEchec() throws Exception {
		mockMvc.perform(get("/utilisateur/particulier")).andExpect(status().isNotFound());
	}
}
