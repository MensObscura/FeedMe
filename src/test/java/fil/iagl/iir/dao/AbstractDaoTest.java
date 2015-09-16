package fil.iagl.iir.dao;

import org.fest.assertions.api.Assertions;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import fil.iagl.iir.AbstractFeedMeTest;
import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.outils.SQLCODE;

public abstract class AbstractDaoTest extends AbstractFeedMeTest {

	@Autowired
	protected UtilisateurDao utilisateurDao;

	@Autowired
	protected ParticulierDao particulierDao;

	protected void assertSQLCode(DataIntegrityViolationException dive, SQLCODE sqlCode) {
		Assertions.assertThat(dive.getCause()).isInstanceOf(PSQLException.class);
		Assertions.assertThat(((PSQLException) (dive.getCause())).getSQLState()).isEqualTo(sqlCode.getSqlCode());
	}

}
