package fil.iagl.iir.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import fil.iagl.iir.entite.Authentification;

public interface AuthentificationService extends UserDetailsService {

	public void inscription(Authentification authentification);

}
