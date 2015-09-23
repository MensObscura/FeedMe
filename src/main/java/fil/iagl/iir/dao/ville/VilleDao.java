package fil.iagl.iir.dao.ville;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Ville;

public interface VilleDao {
	
	public Ville getById(@Param("id") Integer id);
	
	public Integer sauvegarder(@Param("ville") Ville ville);

}
