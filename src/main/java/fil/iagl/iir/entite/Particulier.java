package fil.iagl.iir.entite;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Particulier extends Utilisateur {

  private static final long serialVersionUID = 4888546925876818875L;

  private Integer idParticulier;

  private String prenom;

  private LocalDate dateNaissance;

}
