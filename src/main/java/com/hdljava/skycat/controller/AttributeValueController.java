package com.hdljava.skycat.controller;

import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.pojo.attributeValue;
import com.hdljava.skycat.service.AttributeValueService;
import com.hdljava.skycat.service.CategoryService;
import com.hdljava.skycat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class AttributeValueController {
    @Autowired
    AttributeValueService attributeValueService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping("admin_attributeValue_edit")
    public String edit(Model model,int pid){
        Product product = productService.get( pid );
        product.setCategory( categoryService.get( product.getCid() ) );
        attributeValueService.init( product );
        List<attributeValue> attributeValues= attributeValueService.list( pid );
        model.addAttribute( "attributeValues",attributeValues );
        model.addAttribute( "product",product );
        return "admin/editAttributeValue";
    }
    @ResponseBody
    @RequestMapping("admin_attributeValue_update")
    public String update(attributeValue attributeValue){
        attributeValueService.update( attributeValue );
        return "success";
    }
}
