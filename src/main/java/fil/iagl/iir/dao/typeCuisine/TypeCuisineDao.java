package fil.iagl.iir.dao.typeCuisine;

import java.util.List;

import fil.iagl.iir.entite.TypeCuisine;

public interface TypeCuisineDao {

  /**
   * Recupere la liste de tous les TypeCuisine.
   * 
   * @return La liste des TypeCuisine
   */
  List<TypeCuisine> getAll();

}
