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
    Reservation r = this.createReservation();
    JSONObject json = new JSONObject(r);
    mockMvc.perform(put("/reservation").contentType(FEED_ME_MEDIA_TYPE).content(json.toString()))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
      .andExpect(jsonPath("$.data.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.offre.id").value(IsNull.notNullValue()))
      .andExpect(jsonPath("$.data.dateReservation").value(LocalDate.now().toString()))
      .andExpect(jsonPath("$.data.convive.idUtilisateur").value(1));
  }

  @Test
  public void enregistrerReservationTestFail() throws Exception {
    mockMvc.perform(put("/reservation")).andExpect(status().isBadRequest());
  }

}
