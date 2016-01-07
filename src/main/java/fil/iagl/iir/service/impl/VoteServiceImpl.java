package fil.iagl.iir.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fil.iagl.iir.dao.vote.VoteDao;
import fil.iagl.iir.entite.Vote;
import fil.iagl.iir.outils.FeedMeException;
import fil.iagl.iir.service.VoteService;

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

}
