package fil.iagl.iir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fil.iagl.iir.dao.offre.OffreDao;
import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.outils.FeedMeSession;
import fil.iagl.iir.service.OffreService;

public class OffreServiceImpl implements OffreService {

	@Autowired
	private OffreDao offreDao;

	@Override
	public void sauvegarder(Offre offre) {
		if (offre == null) {
			throw new RuntimeException("Parametre null");
		}

		offre.getHote().setIdUtilisateur(FeedMeSession.getIdUtilisateurConnecte());

		this.offreDao.sauvegarder(offre);
	}

	@Override
	public Offre afficher(Integer id) {
		if (id == null) {
			throw new RuntimeException("Parametre null");
		}

		return this.offreDao.getById(id);
	}

	@Override
	public List<Offre> lister() {
		return offreDao.getAll();
	}

}
