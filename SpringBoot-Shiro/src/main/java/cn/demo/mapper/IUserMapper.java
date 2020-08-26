package cn.demo.mapper;

import cn.demo.entry.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Classname IUserMapper
 * @Description TODO
 * @Date 2020/8/26 1:38
 * @Created by XJC·AW
 */
@Mapper
public interface IUserMapper {
    @Select("select * from userinfo where username=#{name}")
    User findUserByName(String name);
}
