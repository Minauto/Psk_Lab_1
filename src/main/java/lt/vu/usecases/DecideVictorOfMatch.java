package lt.vu.usecases;

import lt.vu.interceptors.MyInterceptor;
import lt.vu.services.VictorDecider;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class DecideVictorOfMatch implements Serializable {
    @Inject
    VictorDecider victorDecider;

    private CompletableFuture<String> decideVictorTask = null;

    @MyInterceptor
    public String decideVictor() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        decideVictorTask = CompletableFuture.supplyAsync(() -> victorDecider.decideVictor());

        return  "/usersPlaying?faces-redirect=true&amp;matchId=" + requestParameters.get("matchId");
    }

    public boolean isTaskRunning() {
        return decideVictorTask != null && !decideVictorTask.isDone();
    }

    public String getStatus() throws ExecutionException, InterruptedException {
        if (decideVictorTask == null) {
            return null;
        } else if (isTaskRunning()) {
            return "Match is in progress";
        }
        return "Victor of the match: team " + decideVictorTask.get();
    }

}
