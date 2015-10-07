package fil.iagl.iir.service;

import org.springframework.stereotype.Service;

import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Utilisateur;

@Service
public interface UtilisateurService {

	Utilisateur getById(Integer id);
	
	Particulier getParticulierByUtilisisateurId(Integer id);
	


}
