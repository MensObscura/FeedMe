package fil.iagl.iir.dao.offre;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fil.iagl.iir.entite.Offre;

public interface OffreDao {

	List<Offre> getAll();

	Offre getById(@Param("id") Integer id);

	Integer sauvegarder(@Param("offre") Offre offre);

}
