package fil.iagl.iir.controller.reservation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.hamcrest.core.IsNull;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Reservation;

public class ReservationControllerTest extends AbstractControllerTest {

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.reservationController)
      .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
      .build();
  }

  @Test
  public void enregistrerReservationTestSuccess() throws Exception {
    // Etant donne une reservation que l'on souhaite sauvegarder et qui n'a pas encore d'ID
    Reservation r = this.createReservation();
    JSONObject json = new JSONObject(r);
    
    // Quand on sauvegarde la reservation par la route /reservation avec le verbe PUT
    mockMvc.perform(put("/reservation").contentType(FEED_ME_MEDIA_TYPE).content(json.toString()))
      .andDo(print())
      // Alors on s'assure que le code de retour vaut 200 (OK)
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      // que l'ID de la reservation a bien ete genere
      .andExpect(jsonPath("$.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.offre.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.dateReservation").value(LocalDate.now().toString()))
      .andExpect(jsonPath("$.convive.idUtilisateur").value(1));
  }

  @Test
  public void enregistrerReservationTestFail() throws Exception {
    // Quand on accede a la route /reservation avec le verbe PUT 
    // sans aucun parametre de type reservation
    // Alors on s'assure que le code de retour vaut 400
    mockMvc.perform(put("/reservation")).andExpect(status().isBadRequest());
  }

}
