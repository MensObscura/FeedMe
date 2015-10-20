package fil.iagl.iir.outils;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.Utilisateur;
import lombok.Getter;

public class FeedMeAuthentificationToken extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = -2623091003191330984L;

  @Getter
  private Authentification<? extends Utilisateur> authentification;

  public FeedMeAuthentificationToken(Authentification<? extends Utilisateur> authentification) {
    super(authentification.getUtilisateur().getMail(), authentification.getPassword(),
      Arrays.asList(new SimpleGrantedAuthority(authentification.getRole().name())));
    this.authentification = authentification;
  }

}
