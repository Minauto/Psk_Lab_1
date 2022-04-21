package lt.vu.persistence;

import lt.vu.entities.Game;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GameDAO {

    @Inject
    private EntityManager em;

    public List<Game> loadAll() {
        return em.createNamedQuery("Game.findAll", Game.class).getResultList();
    }

    public void persist(Game game){
        this.em.persist(game);
    }

    public Game findOne(Integer id) {
        return em.find(Game.class, id);
    }
}
