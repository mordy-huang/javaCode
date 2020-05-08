package com.hdljava.skycat.controller;

import com.hdljava.skycat.pojo.Category;
import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.pojo.ProductImage;
import com.hdljava.skycat.service.CategoryService;
import com.hdljava.skycat.service.ProductImageService;
import com.hdljava.skycat.service.ProductService;
import com.hdljava.skycat.util.ImageUtil;
import com.hdljava.skycat.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin_productImage_list")
    public String productController(Model model,int pid){
        Product product = productService.get( pid );
       product.setCategory(categoryService.get(   product.getCid()));
//        System.out.println(pid);
       List<ProductImage> singleImage = productImageService.List( pid, ProductImageService.type_single );
//        System.out.println(singleImage.size());
        List<ProductImage> detailImage = productImageService.List( pid, ProductImageService.type_detail );

        model.addAttribute( "p",product );
        model.addAttribute("detail",detailImage);
        model.addAttribute("single",singleImage);
        return "/admin/listProductImage";
    }

    @RequestMapping("/admin_productImage_add")
    public String add(ProductImage productImage, HttpSession session , UploadedImageFile up) {
         productImageService.add( productImage );
        String imageName = productImage.getId() + ".jpg";
        String imageFolder=null; //图片保存路径
        String imageFolder_middle=null; //单张图片中等大小保存路径
        String imageFolder_small=null;  //单张图片小等大小保存路径
        if(productImage.getType().equals(ProductImageService.type_single )){
            imageFolder= session.getServletContext().getRealPath( "img/productSingle" );
            imageFolder_middle=session.getServletContext().getRealPath( "img/productSingleMiddle" );
            imageFolder_small=session.getServletContext().getRealPath( "img/productSingleSmall" );
        }else{
            imageFolder= session.getServletContext().getRealPath( "img/productDetail" );
        }
        File file = new File( imageFolder, imageName );
        file.getParentFile().mkdirs();

        try {
                up.getImage().transferTo( file );
                BufferedImage bufferedImage = ImageUtil.change2jpg( file );
                ImageIO.write(bufferedImage,"jpg",file);

            if(productImage.getType().equals(ProductImageService.type_single )){
                File file_small = new File( imageFolder_small, imageName );
                File file_middle = new File( imageFolder_middle, imageName );

                ImageUtil.resizeImage(file, 56, 56, file_small);
                ImageUtil.resizeImage(file, 217, 190, file_middle);
            }
            } catch (IOException e) {
                e.printStackTrace();
            }

        return "redirect:/admin_productImage_list?pid="+productImage.getPid();
    }

    @RequestMapping("/admin_productImage_delete")
    public String delete(int id,HttpSession session)   {
        //要删除的文件名
        String imageName=id+".jpg";
        //图片文件夹的路径
        String imageFolder;
        ProductImage pi = productImageService.get( id );
        productImageService.delete( id );

        if(pi.getType().equals( ProductImageService.type_single )){
            imageFolder = session.getServletContext().getRealPath( "img/productSingle");
           String imageFolder_small = session.getServletContext().getRealPath( "img/productSingleSmall");
            String imageFolder_middle = session.getServletContext().getRealPath( "img/productSingleMiddle");
            File file_small = new File( imageFolder_small, imageName );
            File file_middle = new File( imageFolder_middle, imageName );
            file_small.delete();
            file_middle.delete();
        }
        else{
             imageFolder = session.getServletContext().getRealPath( "img/productDetail");
        }
        File file = new File( imageFolder, imageName );
        file.delete();
        return "redirect:/admin_productImage_list?pid="+pi.getPid();
    }





}
