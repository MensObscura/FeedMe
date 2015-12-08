package fil.iagl.iir.outils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fil.iagl.iir.dao.authentification.AuthentificationDao;

/**
 * @author RMS
 * 
 * Reprensente une session utilisateur
 */
@Component
public class FeedMeSession {

  private static AuthentificationDao authentificationDao;

  @Autowired
  public FeedMeSession(AuthentificationDao authentificationDao) { // NOSONAR
    FeedMeSession.authentificationDao = authentificationDao;
  }

  public static Integer getIdUtilisateurConnecte() {
    Authentication auth = SecurityContextHolder.getContext()
      .getAuthentication();
    if (auth == null) {
      return null;
    }
    String username = auth.getName();
    return authentificationDao.getByUsername(username).getUtilisateur().getIdUtilisateur();
  }

}
