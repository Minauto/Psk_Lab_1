package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Match;

import java.util.List;

@Getter
@Setter
public class Game {

    private Integer id;
    private String name;
    private List<Match> matches;
}