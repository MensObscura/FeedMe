
package fil.iagl.iir.entite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Utilisateur implements Serializable {

  private static final long serialVersionUID = -1993291509496155748L;

  private Integer idUtilisateur;

  private String nom;

  private String mail;

  public Utilisateur(Integer idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  public Utilisateur() {

  }

}
