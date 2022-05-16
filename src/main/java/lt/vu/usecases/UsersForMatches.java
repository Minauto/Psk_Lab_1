package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Match;
import lt.vu.entities.User;
import lt.vu.persistence.MatchDAO;
import lt.vu.persistence.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class UsersForMatches {

    @Inject
    private UserDAO userDAO;

    @Inject
    private MatchDAO matchDAO;

    @Getter
    @Setter
    private Match match;

    @Getter
    @Setter
    private User user = new User();

    @Getter
    @Setter
    private User userToAdd  = new User();

    @Getter @Setter
    private User userToCreate = new User();

    @Getter @Setter
    private List<User> allUsers = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer matchId = Integer.parseInt(requestParameters.get("matchId"));
        this.match = matchDAO.findOne(matchId);
        this.allUsers = userDAO.loadAll();
    }

    @Transactional
    public void addUser() {
        this.userToAdd = userDAO.findOne(this.user.getId());
        userToAdd.addMatches(this.match);
        match.addUsers(this.userToAdd);
    }

    @Transactional
    public void createUser() {
        userToCreate.addMatches(this.match);
        this.match.addUsers(this.userToCreate);
        userDAO.persist(userToCreate);
    }


}
