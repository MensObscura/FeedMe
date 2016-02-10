package fil.iagl.iir.service;

import fil.iagl.iir.entite.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

  void sauvegarder(Vote vote);
  
  List<Vote> getVotesByOffre(Integer idOffre);

  Integer getNoteMoyenne(List<Vote> votes);

  Vote getVote(Integer idUtilisateur, Integer idOffre);

}
