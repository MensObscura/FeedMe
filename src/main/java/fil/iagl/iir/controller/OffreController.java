package fil.iagl.iir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.service.OffreService;

@RestController
@RequestMapping("/offres")
public class OffreController {

	@Autowired
	private OffreService offreservice;

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public Offre sauvegarder(@RequestBody Offre offre) {
		offreservice.sauvegarder(offre);
		return offre;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Offre afficher(@PathVariable("id") Integer id) {
		return offreservice.afficher(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Offre> afficher() {
		return offreservice.lister();
	}
}
