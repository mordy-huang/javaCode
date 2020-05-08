package com.hdljava.skycat.service;

import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {

    String type_single="type_single"; //图片是单张属性
    String type_detail="type_detail"; //图片是商品详情属性
    /**
     * 查询图片
     * @return
     */
    List<ProductImage> List(int pid,String type);
    /**
     * 增加产品图片
     * @param productImage
     */
    void add(ProductImage productImage);

    /**
     * 删除一个产品
     * @param id
     */
    void delete(int id);

    /**
     * 得到产品类
     * @return
     */
    ProductImage get(int id);

    /**
     * 编辑一个产品
     * @param product
     */
    void update(ProductImage product);
}
