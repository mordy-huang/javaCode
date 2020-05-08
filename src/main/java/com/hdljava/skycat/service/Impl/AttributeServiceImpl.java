package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.attributeMapper;
import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.pojo.attribute;
import com.hdljava.skycat.pojo.attributeExample;
import com.hdljava.skycat.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    attributeMapper attributeMapper;

    @Override
    public List<attribute> List(int cid) {
        attributeExample example = new attributeExample();
        example.createCriteria().andCidEqualTo( cid );
        example.setOrderByClause( "id desc" );
        return attributeMapper.selectByExample( example );
    }

    @Override
    public void add(attribute attribute) {
         attributeMapper.insert( attribute );
    }

    @Override
    public void delete(int id) {
     attributeMapper.deleteByPrimaryKey( id );
    }

    @Override
    public attribute get(int id) {
        return  attributeMapper.selectByPrimaryKey( id );
    }

    @Override
    public void update(attribute attribute) {
        attributeMapper.updateByPrimaryKeySelective(attribute);
    }
}
