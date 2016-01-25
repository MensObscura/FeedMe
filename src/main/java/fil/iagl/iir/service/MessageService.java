package fil.iagl.iir.service;

import java.util.List;

import fil.iagl.iir.entite.Message;

public interface MessageService {

  /**
   * Sauvegarde un message
   * 
   * @param message le message à sauvegarder
   */
  void sauvegarder(Message message);

  /**
   * Supprime un message d'id passé en paramêtre
   * 
   * @param idMessage l'id du message à supprimer
   */
  void supprimer(Integer idMessage);

  /**
   * Recupere tous les messages non lus pour un utilisateur 
   * 
   * @param id l'id de l'utilisateur
   * @return tous les messages non lus de l'utilisateur
   */
  List<Message> getAllNonLuParId(Integer id);

  /**
   * Recupere tous les messages pour un utilisateur 
   * 
   * @param id l'id de l'utilisateur
   * @return tous les messages de l'utilisateur
   */
  List<Message> getAll(Integer id);
  
  /**
   * Marque le message idMsg comme lu
   * @param idMsg
   */
  void marquerCommeLu(Integer idMsg);
}
