package com.hdljava.skycat.service;

import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.pojo.attribute;

import java.util.List;

public interface ProductService {
    /**
     * 分页查询
     * @return
     */
    List<Product> List(int cid);
    /**
     * 增加产品
     * @param product
     */
    void add(Product product);
    /**
     * 删除一个产品
     * @param id
     */
    void delete(int id);

    /**
     * 得到产品类
     * @return
     */
    Product get(int id);

    /**
     * 编辑一个产品
     * @param product
     */
    void update(Product product);

    //给属性添加产品
    void fill(Category category);

    //给多个属性添加产品
    void fill(List<Category> categories);
    //给每个分类添加推荐产品
    void fillByRow(List<Category> categories);
    //给产品添加销量和评价数
    void setSaleCountAndReview(Product product);
    void  setSaleCountAndReview(List<Product> products);
    //设置产品产品页要显示的的第一张图片
    void setFirstProductImage(Product product);
    void setFirstProductImage(List<Product> products);

    List<Product> search(String keyword);
}
