package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    /**
     * 作业：新增、删除、修改测试类
     * @throws Exception
     */
    @Test
    public void testAdd() throws Exception {
        // 获取inputStream
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        // 创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        // 创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 用户类入参
        User user = new User();
        user.setId(5);
        user.setUsername("wangwu");
        // 代理生成dao
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 根据条件查询单个
        int count = userDao.insertInfo(user);
        System.out.println(count);
        count = userDao.deleteInfo(user);
        System.out.println(count);
        // 用户类入参
        User user1 = new User();
        user1.setId(3);
        user1.setUsername("cat");
        count = userDao.updateInfo(user1);
        System.out.println(count);
    }

    /**
     * 原测试方法
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        // 获取inputStream
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        // 创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        // 创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 用户类入参
        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
        // 代理生成dao
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 根据条件查询单个
        User userInfo = userDao.findByCondition(user);
        System.out.println(userInfo);
        // 查询全部
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }
}
