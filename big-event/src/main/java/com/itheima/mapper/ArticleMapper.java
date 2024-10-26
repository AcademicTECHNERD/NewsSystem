package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);


//    List<Article> list(Integer userId, Integer categoryId, String state);
//
//    @Select("SELECT * FROM article WHERE state = #{state} AND (category_id = #{categoryId} OR category_id IS NULL)")
// 查询文章，添加 userId 参数
List<Article> list(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId, @Param("state") String state);

    // 删除文章
    @Delete("DELETE FROM article WHERE id = #{id}")
    void deleteById(Long id);
}
