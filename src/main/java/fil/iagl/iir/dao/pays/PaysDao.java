package fil.iagl.iir.dao.pays;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Pays;

public interface PaysDao {

  /**
   * Recupere un Pays par son ID.
   * 
   * @param id
   *            l'ID du pays a recuperer
   * @return Le Pays associe a cet ID
   */
  Pays getById(@Param("id") Integer id);

  /**
   * Recupere la liste de tous les Pays.
   * 
   * @return La liste de tous les Pays
   */
  List<Pays> getAll();

}
