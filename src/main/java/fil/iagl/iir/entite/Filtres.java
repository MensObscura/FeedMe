package fil.iagl.iir.entite;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filtres implements Serializable {

  private static final long serialVersionUID = -5246388091834567246L;

  private Integer idUtilisateurConnecte;

  private String global;
  private String villeOuCP;

  private Integer idTypeCuisine;
  private Integer noteMinimal;
  private Integer nbPlaceRestanteMinimum;
  private Integer prixMin;
  private Integer prixMax;
  private Integer dureeMin;
  private Integer dureeMax;

  private LocalDate date;

  private Boolean premium;
  private Boolean ageValide;
  private Boolean animaux;

  public Filtres() {
    this.global = "";
  }

}
