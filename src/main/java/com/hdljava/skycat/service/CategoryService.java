package com.hdljava.skycat.service;

import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.util.Page;

import java.util.List;

public interface CategoryService {
    /**
     * 分页查询
     * @return
     */
    List<Category> List();

//    /**
//     * 查询总记录数
//     * @return
//     */
//    int total();

    /**
     * 增加
     * @param category
     */
    void add(Category category);

    void delete(int id);

    /**
     * 得到单个商品属性的信息
     * @return
     */
    Category get(int id);

    void update(Category category);
}
