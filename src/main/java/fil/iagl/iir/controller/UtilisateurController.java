package fil.iagl.iir.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.service.UtilisateurService;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;	
	
	@RequestMapping(value = "/particulier/{id}", method = RequestMethod.GET)
	public String afficherProfil(@PathVariable("id") Integer id){
		return new JSONObject(utilisateurService.getById(id)).toString();
	}
	

	
}
