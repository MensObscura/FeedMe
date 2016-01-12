package fil.iagl.iir.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fil.iagl.iir.dao.vote.VoteDao;
import fil.iagl.iir.entite.Vote;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

  @Autowired
  private VoteDao voteDao;

  @Override
  public void sauvegarder(Vote vote) {
    if (vote == null) {
      throw new FeedMeException("Parametre null");
    }
    this.voteDao.sauvegarder(vote);
  }

  @Override
  public List<Vote> getVotesByOffre(Integer idOffre) {
    if (idOffre == null) {
      throw new FeedMeException("Parametre null");
    }
    return this.voteDao.getVotesByOffre(idOffre);
  }

  /**
   * Retourne la note moeyenne depuis une liste de votes.
   * La note est renvoyée sous forme : 3.75 devient 37
   * @param la liste des votes
   * @return la note moyenne des votes
   */
  @Override
  public Integer getNoteMoyenne(List<Vote> votes) {
    if (votes == null) {
      throw new FeedMeException("Parametre null");
    }
    List<Integer> notes = votes.stream().map(v -> v.getNote()).collect(Collectors.toList());
    Double noteMoyenne = notes.stream().mapToInt(x -> x).average().orElse(0) * 10;
    return noteMoyenne.intValue();
  }

}
