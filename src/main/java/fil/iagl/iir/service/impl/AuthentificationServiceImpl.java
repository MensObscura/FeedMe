package fil.iagl.iir.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fil.iagl.iir.dao.authentification.AuthentificationDao;
import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.service.AuthentificationService;

public class AuthentificationServiceImpl implements AuthentificationService {

	@Autowired
	private AuthentificationDao authentificationDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Authentification auth = authentificationDao.getByUsername(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(auth.getRole().name());

		UserDetails userDetails = new User(auth.getUtilisateur().getMail(), auth.getPassword(),
				Arrays.asList(authority));

		return userDetails;
	}

}
