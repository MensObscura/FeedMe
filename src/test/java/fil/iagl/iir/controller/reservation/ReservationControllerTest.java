package fil.iagl.iir.controller.reservation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.entite.Utilisateur;


public class ReservationControllerTest extends AbstractControllerTest{
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.reservationController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

	}
	
	@Test
	public void enregistrerReservationTestSuccess() throws Exception{
		Reservation r = this.createReservation();		
		mockMvc.perform(put("/reservation").contentType(FEED_ME_MEDIA_TYPE).content("{ \"convive\" : {\"nom\" : \"toto\", \"mail\" : \"monemail@toto.fr\" }}"))
			    .andDo(print())
			    .andExpect(status().isOk());
	}
	
	@Test
	public void enregistrerReservationTestFail() throws Exception {
		Reservation r = this.createReservation();
//		mockMvc.perform(put("/reservation").contentType(FEED_ME_MEDIA_TYPE).content());
	}

}
