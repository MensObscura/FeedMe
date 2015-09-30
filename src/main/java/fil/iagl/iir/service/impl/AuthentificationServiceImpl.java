package fil.iagl.iir.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.service.AuthentificationService;

@Service
public class AuthentificationServiceImpl implements AuthentificationService {

	@Autowired
	private AuthentificationDao authentificationDao;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private ParticulierDao particulierDao;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Authentification auth = this.authentificationDao.getByUsername(username);
		if (auth == null) {
			throw new UsernameNotFoundException("Username non existant");
		}
		final GrantedAuthority authority = new SimpleGrantedAuthority(auth.getRole().name());

		final UserDetails userDetails = new User(auth.getUtilisateur().getMail(), auth.getPassword(),
				Arrays.asList(authority));

		return userDetails;
	}

	@Override
	public void inscription(Authentification<Particulier> authentification) {
		if (authentification == null) {
			throw new RuntimeException("Parametre null");
		}
		utilisateurDao.sauvegarder(authentification.getUtilisateur());
		particulierDao.sauvegarder(authentification.getUtilisateur());
		authentificationDao.sauvegarder(authentification);
	}

}
