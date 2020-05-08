package com.hdljava.skycat.mapper;

import com.hdljava.skycat.pojo.attributeValue;
import com.hdljava.skycat.pojo.attributeValueExample;
import java.util.List;

public interface attributeValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(attributeValue record);

    int insertSelective(attributeValue record);

    List<attributeValue> selectByExample(attributeValueExample example);

    attributeValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(attributeValue record);

    int updateByPrimaryKey(attributeValue record);
}