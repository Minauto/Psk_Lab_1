package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.User;

import java.util.List;

@Getter
@Setter
public class Match {

    private Integer id;
    private Integer gameId;
    private String title;
    private List<User> users;

}