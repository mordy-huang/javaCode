package com.hdljava.skycat.service;

import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.pojo.attribute;

import java.util.List;

public interface AttributeService {
    /**
     * 分页查询
     * @return
     */
    List<attribute> List(int cid);
    /**
     * 增加
     * @param attribute
     */
    void add(attribute attribute);
    /**
     *
     * @param id
     */
    void delete(int id);

    /**
     * 得到单个商品属性的信息
     * @return
     */
    attribute get(int id);

    void update(attribute attribute);
}
