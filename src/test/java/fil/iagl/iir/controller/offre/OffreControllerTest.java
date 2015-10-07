package fil.iagl.iir.controller.offre;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Offre;

public class OffreControllerTest extends AbstractControllerTest {

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

		mockMvc.perform(
				put("/offres/").contentType(FEED_ME_MEDIA_TYPE).content(jo.toString()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
				.andExpect(jsonPath("$.titre").value(offre.getTitre()))
				.andExpect(jsonPath("$.prix").value(offre.getPrix()))
				.andExpect(jsonPath("$.nombrePersonne").value(offre.getNombrePersonne()))
				.andExpect(jsonPath("$.dureeMinute").value(offre.getDureeMinute()))
				// TODO : Trouver pourquoi le serializer JSON vire les secondes
				// lorsqu'elles sont null
				// .andExpect(jsonPath("$.dateRepas").value(offre.getDateRepas()
				// .format(
				// new DateTimeFormatterBuilder()
				// .appendText(ChronoField.YEAR).appendLiteral("-")
				// .appendText(ChronoField.MONTH_OF_YEAR).appendLiteral("-")
				// .appendText(ChronoField.DAY_OF_MONTH).appendLiteral("T")
				// .appendText(ChronoField.HOUR_OF_DAY).appendLiteral(":")
				// .appendText(ChronoField.MINUTE_OF_HOUR).optionalStart().appendLiteral(":")
				// .appendText(ChronoField.SECOND_OF_MINUTE,
				// TextStyle.SHORT).optionalEnd()
				// .toFormatter()
				// )))
				.andExpect(jsonPath("$.menu").value(offre.getMenu()))
				.andExpect(jsonPath("$.animaux").value(offre.getAnimaux()))
				.andExpect(jsonPath("$.adresse.id").value(offre.getAdresse().getId()))
				.andExpect(jsonPath("$.adresse.ville.id").value(offre.getAdresse().getVille().getId()))
				.andExpect(jsonPath("$.adresse.ville.pays.id").value(offre.getAdresse().getVille().getPays().getId()))
				.andExpect(jsonPath("$.adresse.voie").value(offre.getAdresse().getVoie()))
				.andExpect(jsonPath("$.typeCuisine.id").value(offre.getTypeCuisine().getId()))
				.andExpect(jsonPath("$.hote.idUtilisateur").value(offre.getHote().getIdUtilisateur()));
	}
	/*
	 * @Test public void testGetI() { } s
	 */
}
