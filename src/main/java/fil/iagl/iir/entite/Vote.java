package fil.iagl.iir.entite;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vote {

  public Integer id;

  public Utilisateur utilisateur; // L'utilisateur qui a donné une note

  public Offre offre; // L'offre qui est notée

  public Integer note;
}
