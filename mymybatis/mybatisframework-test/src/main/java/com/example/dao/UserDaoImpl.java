//package com.example.dao;
//
//import com.example.pojo.User;
//import com.exaple.io.Resources;
//import com.exaple.sqlSession.SqlSession;
//import com.exaple.sqlSession.SqlSessionFactory;
//import com.exaple.sqlSession.SqlSessionFactoryBuilder;
//
//import java.io.InputStream;
//import java.util.List;
//
///**
// * 重复编码的代码
// */
//public class UserDaoImpl implements IUserDao{
//    @Override
//    public List<User> findAll() {
//
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        User user = new User();
//        user.setId(1);
//        user.setUsername("anthony");
//        User user2 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user2);
//        User user3 = sqlSession.selectOne("user.selectList", null);
//        System.out.println(user3);
//        sqlSession.close();
//        return null;
//    }
//
//    @Override
//    public User findByCondition(User user) {
//
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        User user = new User();
//        user.setId(1);
//        user.setUsername("anthony");
//        User user2 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user2);
//        User user3 = sqlSession.selectOne("user.selectList", null);
//        System.out.println(user3);
//        sqlSession.close();
//        return null;
//    }
//}
