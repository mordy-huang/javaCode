package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.CategoryMapper;
import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.pojo.CategoryExample;
import com.hdljava.skycat.util.Page;
import com.hdljava.skycat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> List() {
        CategoryExample ce =new CategoryExample();
        ce.setOrderByClause( "id desc" );
        return categoryMapper.selectByExample( ce );
    }

//    @Override
//    public int total() {
//      return categoryMapper.total();
//    }

    @Override
    public void add(Category category) {
        categoryMapper.insert( category );
    }

    @Override
    public void delete(int id) {
       categoryMapper.deleteByPrimaryKey( id );
    }

    @Override
    public Category get(int id) {
       return categoryMapper.selectByPrimaryKey( id );
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective( category );
    }
}
