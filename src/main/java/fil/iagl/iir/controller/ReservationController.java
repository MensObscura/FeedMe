package fil.iagl.iir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public boolean reserver	(@ModelAttribute("reservation")Reservation res){
		reservation.sauvegarder(res);
		return true;
	}
}
