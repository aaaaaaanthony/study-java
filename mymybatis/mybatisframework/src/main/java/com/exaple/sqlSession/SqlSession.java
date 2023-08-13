package com.exaple.sqlSession;

import java.util.List;
import java.util.Objects;

public interface SqlSession {

    /**
     * 查询多个结果
     */
    <E> List<E> selectList(String statementId, Object param) throws Exception;

    /**
     * 查询单个结果
     */
    <T> T selectOne(String statementId, Object param) throws Exception;

    /**
     * 清楚资源
     */
    void close();

    /**
     * 生成代理对象
     */
    <T> T getMapper(Class<T> mapperClass);
}
