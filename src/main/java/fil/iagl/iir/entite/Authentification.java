package fil.iagl.iir.entite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authentification {

	private Utilisateur utilisateur;

	private String password;

	private Role role;

}
