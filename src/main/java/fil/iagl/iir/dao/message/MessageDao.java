package fil.iagl.iir.dao.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Message;

public interface MessageDao {

  /**
   * Sauvegarde un message en base de donnée
   * 
   * @param message le message a sauvegarder
   * @return le nombre de lignes sauvegardées
   */
  Integer sauvegarder(@Param("message") Message message);

  /**
   * supprime un message en base de donnée
   * 
   * @param id l'id du message a supprimer
   * @return le nombre de lignes modifiées
   */
  Integer supprimer(@Param("idMessage") Integer id);

  /**
   * Permet de récupérer les messages non lus envoyés à un utilisateur
   * 
   * @param id id du destinataire des messages
   * @return la liste des messages non lus pour l'utilisateur spécifié
   */
  List<Message> getAllNonLuParId(@Param("idDestinataire") Integer id);

  /**
   * Permet de récupérer tous les messages envoyés à un utilisateur
   * 
   * @param id id du destinataire des messages
   * @return la liste des messages pour l'utilisateur spécifié
   */
  List<Message> getAll(@Param("idDestinataire") Integer id);

}
