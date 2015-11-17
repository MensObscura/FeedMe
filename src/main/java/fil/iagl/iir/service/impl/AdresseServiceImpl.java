package fil.iagl.iir.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.adresse.AdresseDao;
import fil.iagl.iir.dao.ville.VilleDao;
import fil.iagl.iir.entite.Adresse;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.AdresseService;

@Service
public class AdresseServiceImpl implements AdresseService {
  @Autowired
  private AdresseDao adresseDao;

  @Autowired
  private VilleDao villeDao;

  @Override
  public void sauvegarder(Adresse adresse) {
    if (adresse == null) {
      throw new FeedMeException("L'adresse ne peut pas etre null");
    }
    if (adresse.getVille() == null) {
      throw new FeedMeException("La ville ne peut pas etre null");
    }
    villeDao.sauvegarder(adresse.getVille());
    adresseDao.sauvegarder(adresse);
  }

}
