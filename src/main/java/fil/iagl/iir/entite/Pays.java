package fil.iagl.iir.entite;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pays implements Serializable {

  private static final long serialVersionUID = 2488636559646165113L;

  private Integer id;

  private String codePays;

  private String nom;

}
