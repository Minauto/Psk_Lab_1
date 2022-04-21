package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Game;
import lt.vu.entities.Match;
import lt.vu.persistence.GameDAO;
import lt.vu.persistence.MatchDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class MatchesForGame {

    @Inject
    private GameDAO gameDAO;

    @Inject
    private MatchDAO matchDAO;

    @Getter
    @Setter
    private Game game;

    @Getter @Setter
    private Match matchToCreate = new Match();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer gameId = Integer.parseInt(requestParameters.get("gameId"));
        this.game = gameDAO.findOne(gameId);
    }

    @Transactional
    public void createMatch() {
        matchToCreate.setGame(this.game);
        matchDAO.persist(matchToCreate);
    }
}
