package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.ProductMapper;
import com.hdljava.skycat.mapper.attributeMapper;
import com.hdljava.skycat.mapper.attributeValueMapper;
import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.pojo.attribute;
import com.hdljava.skycat.pojo.attributeValue;
import com.hdljava.skycat.pojo.attributeValueExample;
import com.hdljava.skycat.service.AttributeService;
import com.hdljava.skycat.service.AttributeValueService;
import com.hdljava.skycat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttributeValueServiceImpl implements AttributeValueService {
    @Autowired
    attributeValueMapper attributeValueMapper;
    @Autowired
    AttributeService attributeService;

    @Override
    public void init(Product p) {
        //1 根据产品里的分类id  cid 的到该分类的所有属性
        int pid =p.getId();
        List<attribute> attributes = attributeService.List( p.getCid() );
        //2 根据产品和分类属性的 id 添加属性的值
        for (attribute a: attributes) {
            attributeValue av =  get( pid,a.getId());
            //3 如果没有相应的属性值 着建立相应的属性
            if (av==null){
                 av = new attributeValue();
                 av.setAid( a.getId() );
                 av.setPid( pid );
                 attributeValueMapper.insert( av );
            }
        }

    }

    @Override
    public void update(attributeValue attributeValue) {
        attributeValueMapper.updateByPrimaryKeySelective( attributeValue );
    }

    @Override
    public attributeValue get(int pid, int aid) {
        attributeValueExample example = new attributeValueExample();
        example.createCriteria().andAidEqualTo( aid ).andPidEqualTo( pid );
        List<attributeValue> avs = attributeValueMapper.selectByExample( example );
        if (avs.isEmpty())
            return null;
        else
            return avs.get( 0 );
    }

    @Override
    public List<attributeValue> list(int pid) {
        attributeValueExample example = new attributeValueExample();
        example.createCriteria().andPidEqualTo( pid );
        List<attributeValue> avs = attributeValueMapper.selectByExample( example );
        for (attributeValue av : avs) {
            attribute attribute = attributeService.get( av.getAid() );
            av.setAttribute( attribute );
        }
        return avs;
    }
}
