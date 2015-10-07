package fil.iagl.iir.service;

import java.util.List;

import fil.iagl.iir.entite.Offre;

public interface OffreService {

	void sauvegarder(Offre offre);

	Offre afficher(Integer id);

	List<Offre> lister();
}
