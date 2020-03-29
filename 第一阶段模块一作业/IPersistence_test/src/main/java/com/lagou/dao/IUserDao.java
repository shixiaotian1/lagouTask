package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

public interface IUserDao {

    //查询所有用户
    public List<User> findAll() throws Exception;


    //根据条件进行用户查询
    public User findByCondition(User user) throws Exception;


    // 新增用户
    int insertInfo(User user);

    // 删除用户
    int deleteInfo(User user);

    // 更新用户
    int updateInfo(User user);
}
