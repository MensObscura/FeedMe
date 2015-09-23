package fil.iagl.iir.dao.authentification;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Authentification;

public interface AuthentificationDao {

	Authentification getByUsername(@Param("username") String username);

	Integer sauvegarder(@Param("authentification") Authentification authentification);

}
