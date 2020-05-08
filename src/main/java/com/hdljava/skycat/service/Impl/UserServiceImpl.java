package com.hdljava.skycat.service.Impl;

import com.hdljava.skycat.mapper.UserMapper;
import com.hdljava.skycat.pojo.User;
import com.hdljava.skycat.pojo.UserExample;
import com.hdljava.skycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> List() {
        UserExample example = new UserExample();
        example.setOrderByClause("id asc");
        return userMapper.selectByExample( example );
    }

    @Override
    public void add(User user) {
        userMapper.insert( user );
    }

    @Override
    public void delete(int id) {
        userMapper.deleteByPrimaryKey( id );
    }

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey( id );
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective( user );
    }

    @Override
    public boolean isExist(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo( name );
        List<User> users = userMapper.selectByExample( example );
        if(users.size()==0) return false;
        else return true;
    }

    @Override
    public User isRight(String name, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo( name ).andPasswordEqualTo( password );
        List<User> users = userMapper.selectByExample( example );
        if(users.isEmpty()) return null;
        else return users.get( 0 );
    }

    @Override
    public User get(String name, String pw) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo( name ).andPasswordEqualTo( pw );
        List<User> users = userMapper.selectByExample( example );
        if (users.isEmpty()) return null;
        else return users.get( 0 );
    }
}
