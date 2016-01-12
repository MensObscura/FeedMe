package fil.iagl.iir.service;

import java.util.List;

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

  /**
   * Recupere la liste de tous les utilisateurs premium
   * 
   * @return la liste de tous les utilisateurs premium
   */
  List<Particulier> getAllPremium();

  /**
   * Permet de modifier les informations d'un Particulier
   * @param particulier Les nouvelles informations Particulier
   */
  void modifierProfil(Particulier particulier);
  
  /**
   * Permet de faire devenir un utilisateur prénium
   * @param utilisateur qui souhaite devenir prénium
   */
  void devenirPrenium(Utilisateur utilisateur);
}
