package com.example.test;

import com.example.dao.IUserDao;
import com.example.pojo.User;
import com.exaple.io.Resources;
import com.exaple.sqlSession.SqlSession;
import com.exaple.sqlSession.SqlSessionFactory;
import com.exaple.sqlSession.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

public class Demo {

    /**
     * 传统方式,不使用mapper代理测试
     */
    @Test
    public void test1() throws Exception {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(1);
        user.setUsername("anthony");
        User user2 = sqlSession.selectOne("user.selectOne", user);
        System.out.println(user2);
        User user3 = sqlSession.selectOne("user.selectList", null);
        System.out.println(user3);
        sqlSession.close();
    }


    @Test
    public void test2() throws Exception {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user1 = new User();
        user1.setId(1);
        user1.setUsername("anthony");
        User byCondition = userDao.findByCondition(user1);

        System.out.println(byCondition);
        sqlSession.close();
    }

    @Test
    public void test3() throws Exception {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> byCondition = userDao.findAll();

        System.out.println(byCondition);
        sqlSession.close();
    }
}
