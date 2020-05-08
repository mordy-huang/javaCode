package com.hdljava.skycat.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.pojo.attribute;
import com.hdljava.skycat.service.AttributeService;
import com.hdljava.skycat.service.CategoryService;
import com.hdljava.skycat.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class AttributeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    AttributeService attributeService;

    @RequestMapping("/admin_attribute_list")
    public String attributeController(int cid, Model model, Page page) {
        Category category = categoryService.get( cid );

        PageHelper.offsetPage( page.getStart(), page.getCount() );
        List<attribute> attributes = attributeService.List( cid );
        int total = (int) new PageInfo<>( attributes).getTotal();
        page.setTotal( total );
        page.setParam( "&cid=" + category.getId() );

        model.addAttribute( "c", category );
        model.addAttribute( "page", page );
        model.addAttribute( "attribute", attributes );
        return "admin/listAttribute";
    }

    @RequestMapping("/admin_attribute_add")
    public String add(attribute attribute) {
        attributeService.add( attribute );
        return "redirect:/admin_attribute_list?cid=" + attribute.getCid();
    }

    @RequestMapping("/admin_attribute_edit")
    public String add(int id, Model model) {
        attribute a = attributeService.get( id );
        Category c = categoryService.get( a.getCid());
        a.setCategory(c);
        model.addAttribute( "a", a );
        return "admin/editAttribute";
    }

    @RequestMapping("/admin_attribute_update")
    public String update(attribute attribute) {
        attributeService.update( attribute );
        return "redirect:/admin_attribute_list?cid=" + attribute.getCid();
    }

    @RequestMapping("/admin_attribute_delete")
    public String delete(int id) {
        attribute attribute = attributeService.get( id );
        int cid = attribute.getCid();
        attributeService.delete( id );
        return "redirect:/admin_attribute_list?cid=" + cid;
    }
}
