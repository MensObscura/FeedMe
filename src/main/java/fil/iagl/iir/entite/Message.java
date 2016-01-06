package fil.iagl.iir.entite;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message implements Serializable {

  private static final long serialVersionUID = -1396027905000070780L;

  private Integer id;
  private Utilisateur expediteur;
  private Utilisateur destinataire;
  private LocalDateTime date;
  private Boolean lu;
  private String objet;
  private String texte;

}
