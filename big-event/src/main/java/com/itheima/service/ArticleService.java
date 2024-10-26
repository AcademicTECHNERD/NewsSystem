package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询nickname
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);



    // 删除文章
    void delete(Long id);  // 新增这一行
}
