package fil.iagl.iir.dao.adresse;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Adresse;

public interface AdresseDao {

	/**
	 * Recuperer une adresse par son id.
	 * 
	 * @param id
	 *            l'id de l'adresse
	 * @return 
	 * <ul>
	 * 		<li>L'adresse si elle existe</li>
	 * 		<li>Null sinon</li>
	 * </ul>
	 */
	Adresse getById(@Param("id") Integer id);

}
