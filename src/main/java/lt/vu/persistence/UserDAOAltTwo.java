package lt.vu.persistence;

import lt.vu.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Alternative
@ApplicationScoped
public class UserDAOAltTwo extends UserDAO{

    @Inject
    private EntityManager em;

    public List<User> loadAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public void persist(User user){
        this.em.persist(user);
    }

    public User findOne(Integer id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void update(User user){
        try {
            System.out.println("Pausing first transaction for 10 second");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        em.persist(user);
    }
}