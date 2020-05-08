package com.hdljava.skycat.mapper;

import com.hdljava.skycat.pojo.attribute;
import com.hdljava.skycat.pojo.attributeExample;
import java.util.List;

public interface attributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(attribute record);

    int insertSelective(attribute record);

    List<attribute> selectByExample(attributeExample example);

    attribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(attribute record);

    int updateByPrimaryKey(attribute record);
}