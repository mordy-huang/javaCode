package com.hdljava.skycat.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.hdljava.skycat.mapper.CategoryMapper;
import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.service.CategoryService;
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
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("/admin_category_list")
    public String categoryController(Model model,Page page){
        PageHelper.offsetPage( page.getStart(),page.getCount());
        List<Category> categories =categoryService.List();
        int total = (int) new PageInfo<>(categories).getTotal();
//        System.out.println("sizeof:"+categories.size()+","+"pageInfo:"+total);
        page.setTotal( total );
        model.addAttribute( "cs",categories );
        model.addAttribute( "page",page );
        return "admin/listCategory";
    }

    @RequestMapping("/admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {

        categoryService.add( c );
        File imageFolder = new File( session.getServletContext().getRealPath("img/category") );
        File file = new File( imageFolder,c.getId() + ".jpg" );
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        uploadedImageFile.getImage().transferTo( file );
        BufferedImage img = ImageUtil.change2jpg( file );
        ImageIO.write( img,"jpg",file );
        return "redirect:/admin_category_list";
    }

    @RequestMapping("/admin_category_delete")
    public String add(int id, HttpSession session) throws IOException {
        categoryService.delete( id );
        String realPath = session.getServletContext().getRealPath( "img/category" );
        File imageFolder = new File(realPath);
        File file = new File( imageFolder,id+".jpg" );
        file.delete();
        return "redirect:/admin_category_list";
    }

    @RequestMapping("/admin_category_edit")
    public String get(int id, Model model) throws IOException {
        Category category = categoryService.get( id );
        model.addAttribute("c",category);
        return "admin/editCategory";
    }

    @RequestMapping("/admin_category_Update")
    public String get(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        System.out.println(category);
        categoryService.update( category );
        MultipartFile image = uploadedImageFile.getImage();
        if(image!=null&&!image.isEmpty()){
            File imageFolder = new File( session.getServletContext().getRealPath("img/category") );
            File file = new File( imageFolder,category.getId() + ".jpg" );
            image.transferTo( file );
            BufferedImage img = ImageUtil.change2jpg( file );
            ImageIO.write( img,"jpg",file );

        }

        return "redirect:/admin_category_list";
    }




}
