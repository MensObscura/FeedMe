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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((authentification == null) ? 0 : authentification.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) { // NOSONAR
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    FeedMeAuthentificationToken other = (FeedMeAuthentificationToken) obj;
    if (authentification == null) {
      if (other.authentification != null)
        return false;
    } else if (!authentification.equals(other.authentification))
      return false;
    return true;
  }

}
