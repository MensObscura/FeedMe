package fil.iagl.iir.dao;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Utilisateur;

public interface UtilisateurDao {

	/**
	 * @param id
	 * @return
	 */
	Utilisateur getById(@Param("id") Integer id);
	
	/**
	 * @param utilisateur
	 * @return
	 */
	Integer sauvegarder(@Param("utilisateur") Utilisateur utilisateur);

}
