package com.yuksuga.foodsearchui.dao;

import com.yuksuga.foodsearchui.domain.User;
import com.yuksuga.foodsearchui.domain.UserList;

public interface UserDao {

    UserList find(String email);

    void add(User user);

    User findOne(Long Id);

    void update(User user);
}
