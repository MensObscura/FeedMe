package fil.iagl.iir.controller.parametrage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fil.iagl.iir.controller.AbstractControllerTest;

public class ParametrageControllerTest extends AbstractControllerTest {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.parametrageController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getVillesTestSucces() throws Exception {
		mockMvc.perform(get("/settings/pays")).andExpect(status().isOk())
				.andExpect(content().contentType(FEED_ME_MEDIA_TYPE)).andExpect(jsonPath("$").isArray());
	}

	@Test
	public void getTypesCuisinesTestSucces() throws Exception {
		mockMvc.perform(get("/settings/typescuisines")).andExpect(status().isOk())
				.andExpect(content().contentType(FEED_ME_MEDIA_TYPE)).andExpect(jsonPath("$").isArray());
	}

}
