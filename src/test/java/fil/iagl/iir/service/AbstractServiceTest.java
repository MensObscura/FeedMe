package fil.iagl.iir.service;

import org.springframework.beans.factory.annotation.Autowired;

import fil.iagl.iir.AbstractFeedMeTest;

public abstract class AbstractServiceTest extends AbstractFeedMeTest{

	@Autowired
	protected UtilisateurService utilisateurService;
	
}
