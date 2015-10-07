package fil.iagl.iir.dao.particulier;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Particulier;

public interface ParticulierDao {

	Particulier getById(@Param("id") Integer id);

	Integer sauvegarder(@Param("particulier") Particulier particulier);
	
	Particulier getParticulierByUtilisateurId(@Param("id") Integer id);

}
