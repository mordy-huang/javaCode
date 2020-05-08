package com.hdljava.skycat.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hdljava.skycat.mapper.CategoryMapper;
import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.service.CategoryService;
import com.hdljava.skycat.service.ProductService;
import com.hdljava.skycat.util.ImageUtil;
import com.hdljava.skycat.util.Page;
import com.hdljava.skycat.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin_product_list")
    public String productController(Model model,Page page,int cid){

        Category category = categoryService.get( cid );
        List<Product> products = productService.List(cid);
        PageHelper.offsetPage( page.getStart(),page.getCount());
        int total = (int) new PageInfo<>(products).getTotal();
        System.out.println("sizeof:"+products.size()+","+"pageInfo:"+total);
        page.setTotal( total );
        page.setParam( "&cid=" +category.getId() );
        model.addAttribute( "c",category );
        model.addAttribute( "products",products );
        model.addAttribute( "page",page );
        return "admin/listProduct";
    }

    @RequestMapping("/admin_product_add")
    public String add(Product product) {
         product.setCreateDate( new Date( ) );
         productService.add( product );
         return "redirect:/admin_product_list?cid="+product.getCid();
    }

    @RequestMapping("/admin_product_edit")
    public String add(int id, Model model) {
        Product product = productService.get( id );
        Category c = categoryService.get( product.getCid());
        product.setCategory(c);
        model.addAttribute( "p", product );
        return "admin/editProduct";
    }

    @RequestMapping("/admin_product_update")
    public String update(Product product) {
        productService.update( product );
        return "redirect:/admin_product_list?cid=" + product.getCid();
    }

    @RequestMapping("/admin_product_delete")
    public String delete(int id)   {
        int cid = productService.get( id ).getCid();
        System.out.println(id);
        productService.delete( id );

        return "redirect:/admin_product_list?cid="+cid;
    }





}
