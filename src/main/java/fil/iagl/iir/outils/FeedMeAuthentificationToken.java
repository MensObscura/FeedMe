package fil.iagl.iir.outils;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import fil.iagl.iir.entite.Authentification;
import lombok.Getter;

public class FeedMeAuthentificationToken extends UsernamePasswordAuthenticationToken {

	@Getter
	private Authentification authentification;

	public FeedMeAuthentificationToken(Authentification authentification) {
		super(authentification.getUtilisateur().getMail(), authentification.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(authentification.getRole().name())));
		this.authentification = authentification;
	}

}
