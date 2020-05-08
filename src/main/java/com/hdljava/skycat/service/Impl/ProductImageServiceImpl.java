package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.CategoryMapper;
import com.hdljava.skycat.mapper.ProductImageMapper;
import com.hdljava.skycat.mapper.ProductMapper;
import com.hdljava.skycat.pojo.*;
import com.hdljava.skycat.service.CategoryService;
import com.hdljava.skycat.service.ProductImageService;
import com.hdljava.skycat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService
{
    @Autowired
    ProductImageMapper productImageMapper;
//    @Autowired
//    CategoryMapper categoryMapper;

    @Override
    public List<ProductImage> List(int pid, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andPidEqualTo( pid ).andTypeEqualTo( type );
        example.setOrderByClause( "id desc" );
        return productImageMapper.selectByExample( example );
    }

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert( productImage );
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey( id );
    }

    @Override
    public ProductImage get(int id) {
       return productImageMapper.selectByPrimaryKey( id );
    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.updateByPrimaryKeySelective( productImage );
    }
}
