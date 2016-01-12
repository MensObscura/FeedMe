package fil.iagl.iir.entite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adresse implements Serializable {

  private static final long serialVersionUID = 4181854447935652017L;
  private Integer id;
  private String voie;
  private Ville ville;
}
