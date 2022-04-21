package lt.vu.persistence;

import lt.vu.entities.Match;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MatchDAO {

    @Inject
    private EntityManager em;

    public List<Match> loadAll() {
        return em.createNamedQuery("Match.findAll", Match.class).getResultList();
    }


    public void persist(Match match){
        this.em.persist(match);
    }

    public Match findOne(Integer id) {
        return em.find(Match.class, id);
    }
}
