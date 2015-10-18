package fil.iagl.iir.dao.ville;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Ville;

public interface VilleDao {

	/**
	 * Recupere une ville par son ID.
	 * 
	 * @param id
	 *            l'ID de la ville a recuperer
	 * @return La ville associee a cet ID
	 */
	public Ville getById(@Param("id") Integer id);

	/**
	 * Enregistre une ville
	 * 
	 * @param ville
	 *            la ville a enregistrer
	 * @return Le nombre de lignes inserees
	 */
	public Integer sauvegarder(@Param("ville") Ville ville);

	/**
	 * Recupere la liste de toutes les villes.
	 * 
	 * @return La liste des villes
	 */
	public List<Ville> getAll();

}
