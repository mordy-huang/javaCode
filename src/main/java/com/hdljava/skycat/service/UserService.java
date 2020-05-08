package com.hdljava.skycat.service;

import com.hdljava.skycat.pojo.Product;
import com.hdljava.skycat.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 分页查询
     * @return
     */
    List<User> List();
    /**
     * 增加一个用户
     * @param user
     */
    void add(User user);
    /**
     * 删除一个产品
     * @param id
     */
    void delete(int id);

    /**
     * 得到产品类
     * @return
     */
    User get(int id);

    /**
     * 更新一个用户
     * @param user
     */
    void update(User user);
    boolean isExist(String name);
    User isRight(String name, String password);
    User get(String name, String pw);
}
