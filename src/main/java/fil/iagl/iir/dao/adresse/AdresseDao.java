package fil.iagl.iir.dao.adresse;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Adresse;

public interface AdresseDao {

  /**
   * Recupere une adresse par son id.
   * 
   * @param id
   *            l'ID de l'adresse
   * @return
   * 		<ul>
   *         <li>L'adresse si elle existe</li>
   *         <li>Null sinon</li>
   *         </ul>
   */
  Adresse getById(@Param("id") Integer id);

  /**
   * Enregistre une adresse.
   * 
   * @param adresse
   *            l'adresse a sauvegarder
   * @return Le nombre de lignes inserees
   */
  Integer sauvegarder(@Param("adresse") Adresse adresse);
}
