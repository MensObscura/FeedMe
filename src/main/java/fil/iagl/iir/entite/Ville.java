package fil.iagl.iir.entite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ville {

	private Integer id;
	private String nom;
	private String cp;
	private Pays pays;
}
