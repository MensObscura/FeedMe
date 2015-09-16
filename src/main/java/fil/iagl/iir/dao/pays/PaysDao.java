package fil.iagl.iir.dao.pays;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Pays;

public interface PaysDao {

	Pays getById(@Param("id") Integer id);

}
