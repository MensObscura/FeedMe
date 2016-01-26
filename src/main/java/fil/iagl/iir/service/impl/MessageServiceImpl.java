package fil.iagl.iir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.message.MessageDao;
import fil.iagl.iir.entite.Message;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  private MessageDao messageDao;

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.MessageService#sauvegarder(fil.iagl.iir.entite.Message)
   */
  @Override
  public void sauvegarder(Message message) {
    if (message == null) {
      throw new FeedMeException("Parametre null");
    }
    if (message.getObjet() == null) {
      throw new FeedMeException("Objet null");
    }
    if (message.getTexte() == null) {
      throw new FeedMeException("Texte null");
    }

    this.messageDao.sauvegarder(message);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.MessageService#supprimer(java.lang.Integer)
   */
  @Override
  public void supprimer(Integer idMessage) {
    if (idMessage == null) {
      throw new FeedMeException("Parametre null");
    }
    this.messageDao.supprimer(idMessage);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.MessageService#getAllNonLuParId(java.lang.Integer)
   */
  @Override
  public List<Message> getAllNonLuParId(Integer id) {
    return messageDao.getAllNonLuParId(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see fil.iagl.iir.service.MessageService#getAll(java.lang.Integer)
   */
  @Override
  public List<Message> getAll(Integer id) {
    return messageDao.getAll(id);
  }
  
  @Override
	public void marquerCommeLu(Integer idMsg) {
      if (idMsg == null) {
    	  throw new FeedMeException("Paramètre null");
      } 
      if (idMsg < 0) {
    	  throw new FeedMeException("idMsg < 0");
      }
      messageDao.marquerCommeLu(idMsg);
  }

}
