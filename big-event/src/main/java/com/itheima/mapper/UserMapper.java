package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    //添加
    @Insert("insert into user(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void add(String username, String password);
//数据库表字段通常采用下划线命名法，尤其是在 SQL 和关系型数据库中。这种风格在 SQL 中更为普遍，因为它提高了可读性，尤其是字段名较长时。
//Java 中则通常使用驼峰命名法（camelCase），例如 nickname、email、updateTime。这种风格符合 Java 编码规范，易于理解和使用。
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);
//在 SQL 语句中，使用 #{} 语法将 Java 对象的属性值映射到 SQL 查询中。
// MyBatis 会根据映射规则，将驼峰命名的属性自动转换为下划线命名的数据库字段。
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);

    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
