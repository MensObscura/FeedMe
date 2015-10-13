package fil.iagl.iir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fil.iagl.iir.dao.typeCuisine.TypeCuisineDao;
import fil.iagl.iir.dao.ville.VilleDao;
import fil.iagl.iir.entite.TypeCuisine;
import fil.iagl.iir.entite.Ville;

@RestController
@RequestMapping("/settings")
public class ParametrageController {

	@Autowired
	private TypeCuisineDao typeCuisineDao;

	@Autowired
	private VilleDao villeDao;

	@RequestMapping(value = "/villes", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<Ville> villes() {
		return villeDao.getAll();
	}

	@RequestMapping(value = "/typescuisines", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<TypeCuisine> typescuisines() {
		return typeCuisineDao.getAll();
	}

}
