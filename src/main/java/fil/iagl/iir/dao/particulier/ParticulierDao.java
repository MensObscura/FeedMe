package fil.iagl.iir.dao.particulier;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Particulier;

public interface ParticulierDao {

	/**
	 * Recupere un particulier par son ID.
	 * 
	 * @param id
	 *            l'ID du particulier a recuperer
	 * @return Un Particulier
	 */
	Particulier getById(@Param("id") Integer id);

	/**
	 * Enregistre un particulier.
	 * 
	 * @param particulier
	 *            le Particulier a sauvegarder
	 * @return le nombre de lignes inserees
	 */
	Integer sauvegarder(@Param("particulier") Particulier particulier);

	/**
	 * Recupere un particulier par ID d'utilisateur.
	 * 
	 * @param id
	 *            l'ID de l'utilisateur qui est associe au particulier
	 * @return Le Particulier associe a l'ID d'utilisateur
	 */
	Particulier getParticulierByUtilisateurId(@Param("id") Integer id);

}
