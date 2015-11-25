
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

  private Adresse adresse;

  private Boolean premium;

  private String description;

  private Boolean adresseVisible;

  public Utilisateur(Integer idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  public Utilisateur() {
    // Constructeur par default utilisé par MyBatis
  }

}
