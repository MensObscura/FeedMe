package fil.iagl.iir.dao.authentification;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Authentification;
import fil.iagl.iir.entite.Utilisateur;

public interface AuthentificationDao {

  Authentification<? extends Utilisateur> getByUsername(@Param("username") String username);

  Integer sauvegarder(@Param("authentification") Authentification<? extends Utilisateur> authentification);

}
