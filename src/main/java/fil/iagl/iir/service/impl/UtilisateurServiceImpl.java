package fil.iagl.iir.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.particulier.ParticulierDao;
import fil.iagl.iir.dao.utilisateur.UtilisateurDao;
import fil.iagl.iir.entite.Particulier;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

  @Autowired
  private UtilisateurDao utilisateurDao;

  @Autowired
  private ParticulierDao particulierDao;

  /**
   * @see UtilisateurService#getById(Integer)
   */
  @Override
  public Utilisateur getById(Integer id) {
    if (id == null) {
      throw new FeedMeException("Parametre null");
    }
    return utilisateurDao.getById(id);
  }

  /**
   * @see UtilisateurService#getParticulierByUtilisisateurId(Integer)
   */
  @Override
  public Particulier getParticulierByUtilisisateurId(Integer id) {
    if (id == null) {
      throw new FeedMeException("parametre null");
    }
    return particulierDao.getParticulierByUtilisateurId(id);
  }

}
