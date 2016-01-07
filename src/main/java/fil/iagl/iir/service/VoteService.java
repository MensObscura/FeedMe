package fil.iagl.iir.service;

import java.util.List;

import fil.iagl.iir.entite.Vote;

public interface VoteService {

  void sauvegarder(Vote vote);
  
  List<Vote> getVotesByOffre(Integer idOffre);

}
