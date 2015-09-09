package fil.iagl.iir.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.UtilisateurDAO;
import fil.iagl.iir.entite.Utilisateur;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurDAO utilisateurDAO;

	public List<Utilisateur> getAll() {
		return utilisateurDAO.getAll();
	}

}
