package fil.iagl.iir.entite;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offre {

  private Integer id;

  private LocalDate dateCreation;

  private String titre;

  private Integer prix;

  private Integer nombrePersonne;

  private Integer dureeMinute;

  private LocalDateTime dateRepas;

  private String note;

  private Menu menu;

  private Integer ageMin;

  private Integer ageMax;

  private Boolean animaux;

  private Adresse adresse;

  private TypeCuisine typeCuisine;

  private Utilisateur hote;

  private List<Reservation> reservations;

  private List<Image> images;

  private Boolean premium;

}
