package fil.iagl.iir.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.UtilisateurDAO;
import fil.iagl.iir.entite.Utilisateur;
import fil.iagl.iir.service.UtilisateurService;


@Service
public class UtilisateurServiceImpl implements UtilisateurService{

	@Autowired
	private UtilisateurDAO utilisateurDao;
	
	@Override
	public Utilisateur getById(Integer id) {
		if ( id == null ){
			throw new RuntimeException("trololo");
		}
		return utilisateurDao.getById(id);
	}

	
	
}
