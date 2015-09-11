package fil.iagl.iir.dao;

import org.fest.assertions.api.Assertions;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;


public abstract class AbstractDaoTest {
	
	@Autowired
	protected UtilisateurDAO utilisateurDao;
	
	
	protected void assertSQLCode(DataIntegrityViolationException dive, SQLCODE sqlCode){
		Assertions.assertThat(dive.getCause()).isInstanceOf(PSQLException.class);
		Assertions.assertThat(((PSQLException)(dive.getCause())).getSQLState()).isEqualTo(sqlCode.getSqlCode());
	}

}
