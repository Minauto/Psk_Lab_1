package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.Random;

@Specializes
@ApplicationScoped
public class AlternateVictorDecider extends VictorDecider{

    public String decideVictor() {
        Random rand = new Random();
        int int_random = rand.nextInt(2);
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        if(int_random == 0){
            return "left";
        }else{
            return "right";
        }
    }
}
