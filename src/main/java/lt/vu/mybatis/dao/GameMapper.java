package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Game;
import org.mybatis.cdi.Mapper;

@Mapper
public interface GameMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Game record);
    Game selectByPrimaryKey(Integer id);
    List<Game> selectAll();
    int updateByPrimaryKey(Game record);
}