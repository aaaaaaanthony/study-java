package com.exaple.sqlSession;

import com.exaple.executor.Executor;
import com.exaple.executor.SimpleExecutor;
import com.exaple.pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {
        Executor executor = new SimpleExecutor();
        return new DefaultSqlSession(configuration,executor);
    }
}
