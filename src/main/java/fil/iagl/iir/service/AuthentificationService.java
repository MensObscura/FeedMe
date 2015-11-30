package fil.iagl.iir.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.Particulier;

/**
 * @author RMS
 *
 * Service permettant l'inscription d'un utilisateur
 */
public interface AuthentificationService extends UserDetailsService {

  /**
   * Inscription d'un utilisateur particulier
   * 
   * @param authentification la connection d'un particulier
   */
  public void inscription(Authentification<Particulier> authentification);

  /**
   * Deconnecte la personne actuellement connecté
   */
  public void logout();
}
