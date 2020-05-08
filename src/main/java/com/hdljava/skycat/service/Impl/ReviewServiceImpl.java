package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.ReviewMapper;
import com.hdljava.skycat.pojo.Review;
import com.hdljava.skycat.pojo.ReviewExample;
import com.hdljava.skycat.pojo.User;
import com.hdljava.skycat.service.ReviewService;
import com.hdljava.skycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;
    @Override
    public List<Review> List(int pid) {
        ReviewExample example = new ReviewExample();
        example.setOrderByClause( "id desc" );
        example.createCriteria().andPidEqualTo(pid);
        List<Review> reviews = reviewMapper.selectByExample( example );
        setUsers( reviews );
        return reviews;
    }

    @Override
    public void add(Review review) {
        reviewMapper.insertSelective( review );
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey( id );
    }

    @Override
    public Review get(int pid) {
        Review review = reviewMapper.selectByPrimaryKey( pid );
        setUser( review );
        return  review;
    }

    @Override
    public int getCount(int pid) {
       return List( pid ).size();
    }
    
    private void setUser(Review review){
        User user = userService.get( review.getUid() );
        review.setUser(user);
    }
    public void setUsers(List<Review> reviews){
        for (Review review: reviews) {
            setUser( review );
        }
    }
}
