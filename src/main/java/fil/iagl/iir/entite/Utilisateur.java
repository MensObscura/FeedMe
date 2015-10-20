
package fil.iagl.iir.entite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Utilisateur {

  public Utilisateur(Integer idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  public Utilisateur() {

  }

  private Integer idUtilisateur;

  private String nom;

  private String mail;

}
