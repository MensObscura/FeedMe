package fil.iagl.iir;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FeedMeConfiguration.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles("test")
public abstract class AbstractFeedMeTest {

	private static Boolean hasBeenReset = Boolean.FALSE;

	@Autowired
	private DataSource dataSource;

	@Before
	public void setUp() {
		if (!hasBeenReset) {
			Resource r = new ClassPathResource("test_script.sql");
			ScriptRunner runner = null;
			try {
				File reset = r.getFile();
				runner = new ScriptRunner(dataSource.getConnection());
				runner.runScript(new FileReader(reset));
			} catch (IOException | SQLException e) {
				throw new RuntimeException(e);
			} finally {
				runner.closeConnection();
				hasBeenReset = Boolean.TRUE;
			}
		}
	}

}
