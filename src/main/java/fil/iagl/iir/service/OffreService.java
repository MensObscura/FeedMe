package fil.iagl.iir.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fil.iagl.iir.entite.Offre;

@Service
public interface OffreService {

	void sauvegarder(Offre offre);

	Offre afficher(Integer id);

	List<Offre> lister();
}
