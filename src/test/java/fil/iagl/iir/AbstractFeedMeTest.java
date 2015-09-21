package fil.iagl.iir;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.Role;
import fil.iagl.iir.outils.FeedMeAuthentificationToken;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FeedMeConfiguration.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles("test")
public abstract class AbstractFeedMeTest {

	protected static final int RANDOM_STRING_SIZE = 60;

	protected static final String USERNAME_TEST_USER = "toto.toto@gmail.com";

	private static Boolean hasBeenReset = Boolean.FALSE;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthentificationDao authentificationDao;

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

		this.fausseConnection(USERNAME_TEST_USER, Optional.empty());
	}

	private void fausseConnection(String username, Optional<Role> role) {
		Authentification auth = authentificationDao.getByUsername(username);
		if (role.isPresent()) {
			auth.setRole(role.get());
		}
		FeedMeAuthentificationToken authToken = new FeedMeAuthentificationToken(auth);
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

	protected void changerRole(Role role) {
		fausseConnection(USERNAME_TEST_USER, Optional.of(role));
	}

}
