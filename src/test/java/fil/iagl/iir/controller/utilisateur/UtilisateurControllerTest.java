package fil.iagl.iir.controller.utilisateur;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Utilisateur;

public class UtilisateurControllerTest extends AbstractControllerTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.utilisateurController).build();
	}

	@Test
	public void afficherProfilTestSucces() throws Exception {
		Utilisateur utilisateur = createUtilisateur();
		int id = utilisateur.getIdUtilisateur();
		String nom = utilisateur.getNom();
		String mail = utilisateur.getMail();

		Mockito.when(this.utilisateurService.getById(id)).thenReturn(utilisateur);

		mockMvc.perform(get("/utilisateur/particulier/{id}", id))
				.andExpect(status().isOk())
				.andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
				.andExpect(jsonPath("$.idUtilisateur").value(id))
				.andExpect(jsonPath("$.nom").value(nom)).andExpect(jsonPath("$.mail").value(mail));

		Mockito.verify(this.utilisateurService).getById(id);
	}

	@Test
	public void afficherProfilTestEchec() throws Exception {
		mockMvc.perform(get("/utilisateur/particulier")).andExpect(status().isNotFound());
	}
}
