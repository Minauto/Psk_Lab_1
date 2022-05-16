package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.User;
import lt.vu.persistence.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class MatchesForUsers {

    @Inject
    private UserDAO userDAO;

    @Getter
    @Setter
    private User user;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer userId = Integer.parseInt(requestParameters.get("userId"));
        this.user = userDAO.findOne(userId);
    }


    public String updateName() {
        try{
            userDAO.update(this.user);
        } catch (Exception e) {
            return "/userMatches.xhtml?faces-redirect=true&userId=" + this.user.getId() + "&error=optimistic-lock-exception";
        }
        return "userMatches.xhtml?userId=" + this.user.getId() + "&faces-redirect=true";
    }
}
