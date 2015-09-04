package fil.iagl.iir;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import fil.iagl.iir.FeedMeApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FeedMeApplication.class)
@WebAppConfiguration
public class FeedMeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
