package fil.iagl.iir.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fil.iagl.iir.entite.Vote;

@Service
public interface VoteService {

  void sauvegarder(Vote vote);
  
  List<Vote> getVotesByOffre(Integer idOffre);

}
