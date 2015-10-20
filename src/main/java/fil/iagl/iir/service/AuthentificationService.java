package fil.iagl.iir.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.Particulier;

public interface AuthentificationService extends UserDetailsService {

  public void inscription(Authentification<Particulier> authentification);

}
