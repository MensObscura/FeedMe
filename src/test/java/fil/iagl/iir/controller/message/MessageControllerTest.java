package fil.iagl.iir.controller.message;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import fil.iagl.iir.controller.AbstractControllerTest;
import fil.iagl.iir.entite.Message;

public class MessageControllerTest extends AbstractControllerTest {
	
	@Override
	@Before
	public void setUp() {
	    super.setUp();
	    MockitoAnnotations.initMocks(this);
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	 
	@Test
	public void nonLusTestSucces() throws Exception {
		Integer id = 2;
		mockMvc.perform(get("/msg/{id}/nonLus",id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data",Matchers.hasSize(2)));
	}
	
	@Test
	public void getMessagesTestSucces() throws Exception {
		Integer id = 2;
		mockMvc.perform(get("/msg/{id}/",id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(FEED_ME_MEDIA_TYPE))
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data",Matchers.hasSize(3)));
	}
	
	@Test
	public void supprimerMessageTestSucces() throws Exception {
		Integer id = 3;
		mockMvc.perform(delete("/msg/{id}/",id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(FEED_ME_MEDIA_TYPE));
	}
	
	@Test
	public void nonLusTestEchecIdMsgNull() throws Exception {
		Integer id = null;
		mockMvc.perform(get("/msg/{id}/nonLus",id))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getMessagesTestEchecIdUserNull() throws Exception {
		Integer id = null;
		mockMvc.perform(get("/msg/{id}",id))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void supprimerMessageTestEchecIdNull() throws Exception {
		Integer id = null;
		mockMvc.perform(delete("/msg/{id}",id))
		.andExpect(status().isMethodNotAllowed());
	}
}
