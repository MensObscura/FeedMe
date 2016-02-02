package fil.iagl.iir.entite;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vote {

  private Integer id;

  private Utilisateur utilisateur; // L'utilisateur qui a donné une note

  private Offre offre; // L'offre qui est notée

  private Integer note;
}
