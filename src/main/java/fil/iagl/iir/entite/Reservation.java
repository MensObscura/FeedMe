package fil.iagl.iir.entite;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {

  private Integer id;

  private Offre offre;

  private Utilisateur convive;

  private LocalDate dateReservation;

  private Integer nb_places;

}
