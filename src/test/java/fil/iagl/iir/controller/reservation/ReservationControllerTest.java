package fil.iagl.iir.controller.reservation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import fil.iagl.iir.controller.AbstractControllerTest;


public class ReservationControllerTest extends AbstractControllerTest{
	
	@Test
	public void enregistrerReservationTestSuccess() throws Exception{
		mockMvc.perform(put("/reservation").contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk());
			    
	}

}
