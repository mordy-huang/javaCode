package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.ProductMapper;
import com.hdljava.skycat.pojo.*;
import com.hdljava.skycat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductIServiceImpl implements ProductService
{
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    CategoryService categoryService;
    @Override
    public List<Product> List(int cid) {
        ProductExample p = new ProductExample();
        p.createCriteria().andCidEqualTo( cid );
        p.setOrderByClause( "id desc" );
        List<Product> products = productMapper.selectByExample( p );
        setFirstProductImage( products );
        setCategory( products );
        return products;
    }

    @Override
    public void add(Product product) {
        productMapper.insert( product );
    }

    @Override
    public void delete(int id) {
       productMapper.deleteByPrimaryKey( id );
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey( id );
        //设置产品属性和第一张显示图片
        setCategory( product );
        setFirstProductImage( product);
        return product;
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective( product );
    }

    @Override
    public void fill(Category category) {
        List<Product> products = List( category.getId() );
        category.setProducts( products );
    }

    @Override
    public void fill(List<Category> categories) {
        for (Category category: categories) {
            fill( category );
        }
    }

    /**
     * 给所有属性添加推荐产品
     * @param categories
     */
    @Override
    public void fillByRow(List<Category> categories) {

        int productEachRowNumber=8;
        for (Category category: categories){
            //设置商品
            List<Product> products = category.getProducts();
            //新建一个空的产品推荐分类
            List<List<Product>> productByRow = new ArrayList<>( );
            //添加产品分类里的产品
            for (int i= 0;i<products.size();i+=productEachRowNumber){
                int size = i+productEachRowNumber;
                size = size>products.size() ? products.size():size;
                //得到每个分类推荐的8个产品
                List<Product> productsEachRow = products.subList( i, size );
                productByRow.add( productsEachRow );
            }
            category.setProductsByRow( productByRow );
        }
    }

    /**
     * 给产品添加销售数量 和 评价数量
     * @param product
     */
    @Override
    public void setSaleCountAndReview(Product product) {
        int pid=product.getId();
        int reviewCount = reviewService.getCount( pid );
        int saleCount = orderItemService.getSaleCount( pid );
        product.setSaleCount( saleCount );
        product.setReviewCount( reviewCount );
    }
    @Override
    public void setSaleCountAndReview(List<Product> products) {
        for (Product product: products) {
            setSaleCountAndReview( product );
        }
    }

    @Override
    public void setFirstProductImage(Product product) {
        int pid = product.getId();
        List<ProductImage> list = productImageService.List( pid, ProductImageService.type_single );
        if(!list.isEmpty()) {
            product.setFirstProductImage( list.get( 0 ) );
        }
    }

    public void setFirstProductImage(List<Product> products) {
        for (Product product: products) {
            setFirstProductImage( product );
        }
    }

    @Override
    public List<Product> search(String keyword) {
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike( "%"+keyword+"%" );
        example.setOrderByClause( "id asc" );
        List<Product> products = productMapper.selectByExample( example );
        setFirstProductImage( products );
        setCategory( products );
        return products;
    }

    public void setCategory(List<Product> ps){
        for (Product p : ps)
            setCategory(p);
    }
    public void setCategory(Product p){
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }

}
