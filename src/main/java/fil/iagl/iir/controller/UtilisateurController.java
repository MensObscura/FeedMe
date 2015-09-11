package fil.iagl.iir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fil.iagl.iir.service.UtilisateurService;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@RequestMapping(value = "/particulier/{id}", method = RequestMethod.GET)
	public String afficherProfil(@PathVariable("id") Integer id){
		return utilisateurService.getById(id).toString();
	}
	
}
