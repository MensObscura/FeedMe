package fil.iagl.iir.entite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authentification<T extends Utilisateur> implements Serializable {

  private static final long serialVersionUID = 4123900201049227912L;

  private T utilisateur;

  private String password;

  private Role role;

}
