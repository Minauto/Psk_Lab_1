package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@NamedQueries({
        @NamedQuery(name = "Match.findAll", query = "select a from Match as a")
})
@Table(name = "MATCH")
@Getter
@Setter
public class Match {

    public Match(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne
    @JoinColumn(name="Game_ID")
    private Game game;

    @ManyToMany
    @JoinTable(name="MATCH_ID")
    private List<User> users = new ArrayList<>();

    public void addUsers (User user){
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id)&&
                Objects.equals(title, match.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


}
