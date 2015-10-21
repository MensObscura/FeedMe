package fil.iagl.iir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.entite.Reservation;
import fil.iagl.iir.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

  @Autowired
  private ReservationService reservation;

  /**
   * Sauvegarde une réservation dans la base de données
   * 
   * @param res
   *            une réservation
   * @return la réservation telle qu'elle est enregistrée dans la base de
   *         données
   */
  @RequestMapping(method = RequestMethod.PUT)
  public Reservation reserver(@RequestBody Reservation res) {
    reservation.sauvegarder(res);
    return res;
  }
}
