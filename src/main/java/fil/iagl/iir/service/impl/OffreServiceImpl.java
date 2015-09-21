package fil.iagl.iir.service.impl;

import java.util.List;

import fil.iagl.iir.entite.Offre;
import fil.iagl.iir.service.OffreService;

public class OffreServiceImpl implements OffreService {

	@Override
	public void sauvegarder(Offre offre) {
		if (offre == null) {
			throw new RuntimeException("Parametre null");
		}
	}

	@Override
	public Offre afficher(Integer id) {
		return null;
	}

	@Override
	public List<Offre> lister() {
		return null;
	}

}
