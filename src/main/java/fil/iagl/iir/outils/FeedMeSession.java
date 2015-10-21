package fil.iagl.iir.outils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fil.iagl.iir.dao.authentification.AuthentificationDao;

@Component
public class FeedMeSession {

  private static AuthentificationDao authentificationDao;

  @Autowired
  public FeedMeSession(AuthentificationDao authentificationDao) { // NOSONAR
    FeedMeSession.authentificationDao = authentificationDao;
  }

  public static Integer getIdUtilisateurConnecte() {
    String username = SecurityContextHolder.getContext()
      .getAuthentication().getName();
    return authentificationDao.getByUsername(username).getUtilisateur().getIdUtilisateur();
  }

}
