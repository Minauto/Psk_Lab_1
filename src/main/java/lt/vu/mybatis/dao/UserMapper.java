package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.User;
import org.mybatis.cdi.Mapper;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(User record);
    User selectByPrimaryKey(Integer id);
    List<User> selectAll();
    int updateByPrimaryKey(User record);
}