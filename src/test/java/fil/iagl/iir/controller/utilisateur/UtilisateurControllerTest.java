package fil.iagl.iir.controller.utilisateur;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import fil.iagl.iir.controller.AbstractControllerTest;


public class UtilisateurControllerTest extends AbstractControllerTest{

	@Test
	public void afficherProfilTestSucces() throws Exception {
		mockMvc.perform(get("/utilisateur/particulier/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	public void afficherProfilTestEchec() throws Exception {
		mockMvc.perform(get("/utilisateur/particulier")).andExpect(status().isNotFound());
	}
}
