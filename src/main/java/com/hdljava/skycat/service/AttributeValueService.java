package com.hdljava.skycat.service;

import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.pojo.attributeValue;

import java.util.List;

public interface AttributeValueService {
    void init(Product p);
    void update(attributeValue attributeValue);
    attributeValue get(int pid,int aid);
    List<attributeValue> list(int pid);
}
