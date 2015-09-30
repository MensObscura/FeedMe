package fil.iagl.iir.entite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authentification<T extends Utilisateur> implements Serializable {

	private T utilisateur;

	private String password;

	private Role role;

}
