package fil.iagl.iir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.dao.pays.PaysDao;
import fil.iagl.iir.dao.typeCuisine.TypeCuisineDao;
import fil.iagl.iir.entite.Pays;
import fil.iagl.iir.entite.TypeCuisine;

@RestController
@RequestMapping("/settings")
public class ParametrageController {

  @Autowired
  private TypeCuisineDao typeCuisineDao;

  @Autowired
  private PaysDao paysDao;

  /**
   * Retourne tous les pays de la base de données
   * 
   * @return la liste de tous les pays
   */
  @RequestMapping(value = "/pays", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public List<Pays> villes() {
    return paysDao.getAll();
  }

  /**
   * Retourne tous les types de cuisine de la base de données
   * 
   * @return la liste de tous les types de cuisine
   */
  @RequestMapping(value = "/typescuisines", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public List<TypeCuisine> typescuisines() {
    return typeCuisineDao.getAll();
  }

}
