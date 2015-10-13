package fil.iagl.iir.entite;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

	private String menu;

	private Integer ageMin;

	private Integer ageMax;

	private Boolean animaux;

	private Adresse adresse;

	private TypeCuisine typeCuisine;

	private Utilisateur hote;

}
