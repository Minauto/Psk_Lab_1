package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Match;
import org.mybatis.cdi.Mapper;

@Mapper
public interface MatchMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Match record);
    Match selectByPrimaryKey(Integer id);
    List<Match> selectAll();
    int updateByPrimaryKey(Match record);
}