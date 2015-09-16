package fil.iagl.iir.dao.pays;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Pays;

public interface PaysDao {

	Pays getById(@Param("id") Integer id);

	List<Pays> getAll();

}
