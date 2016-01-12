package fil.iagl.iir.entite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ville implements Serializable {

  private static final long serialVersionUID = -7401302602482954641L;
  private Integer id;
  private String nom;
  private String cp;
  private Pays pays;
}
