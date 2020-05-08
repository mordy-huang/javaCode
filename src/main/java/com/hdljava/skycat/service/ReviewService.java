package com.hdljava.skycat.service;



import com.hdljava.skycat.pojo.Review;

import java.util.List;

public interface ReviewService {
    /**
     * 查询评价
     * @return
     */
    List<Review> List(int pid);

    /**
     * 增加一个用户
     * @param review
     */
    void add(Review review);
    /**
     * 删除一条评价
     * @param id
     */
    void delete(int id);

    /**
     * 得到一个产品的评价
     * @return
     */
    Review get(int pid);

    int getCount(int pid);
}
