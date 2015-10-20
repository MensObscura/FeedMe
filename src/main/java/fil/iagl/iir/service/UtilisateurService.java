package fil.iagl.iir.service;

import org.springframework.stereotype.Service;

import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Utilisateur;

@Service
public interface UtilisateurService {

	/**
	 * Permet de récupérer un utilisateur selon son ID
	 * @param id L'ID de l'utilisateur a récupérer
	 * @return Un utilisateur
	 */
  Utilisateur getById(Integer id);

  /**
   * Permet de récupérer un particulier en fonction de l'ID utilisateur qui lui est associé 
   * @param id L'ID de l'utilisateur
   * @return Un particulier
   */
  Particulier getParticulierByUtilisisateurId(Integer id);

}
