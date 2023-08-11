package com.exaple.sqlSession;

import com.exaple.executor.Executor;
import com.exaple.pojo.Configuration;
import com.exaple.pojo.MappedStatement;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object param) throws Exception {

        // 查询操作委派给底层的执行器
        // 执行底层的jdbc, sql配置信息和数据库配置信息
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return executor.query(configuration, mappedStatement,param);
    }

    @Override
    public <T> T selectOne(String statementId, Object param) throws Exception {
        List<Object> objects = this.selectList(statementId, param);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else if (objects.size() > 1) {
            throw new RuntimeException("返回结果过多");
        } else {
            return null;
        }
    }

    @Override
    public void close() {
        executor.close();
    }
}
