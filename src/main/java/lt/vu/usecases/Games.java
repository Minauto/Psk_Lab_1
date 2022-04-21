package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Game;
import lt.vu.persistence.GameDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Games {

    @Inject
    private GameDAO gameDAO;

    @Getter
    @Setter
    private Game gameToCreate = new Game();

    @Getter
    private List<Game> allGames;

    @PostConstruct
    public void init(){
        loadAllGames();
    }

    @Transactional
    public void createGame(){
        this.gameDAO.persist(gameToCreate);
    }

    private void loadAllGames(){
        this.allGames = gameDAO.loadAll();
    }
}
