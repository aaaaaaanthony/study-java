package com.exaple.sqlSession;

import com.exaple.executor.Executor;
import com.exaple.pojo.Configuration;
import com.exaple.pojo.MappedStatement;

import java.lang.reflect.*;
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

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        // 使用JDK动态代理生成基于代理的对象
        Object o = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {

            // 1.代理对象的应用
            // 2.执行代理对象的方法
            // 3.参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String name = method.getName();
                String name1 = method.getDeclaringClass().getName();
                String statementId = name1 + "." + name;

                MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
                String sqlCommandType = mappedStatement.getSqlCommandType();

                switch (sqlCommandType) {
                    case "select":
                        Type genericReturnType = method.getGenericReturnType();
                        if (genericReturnType instanceof ParameterizedType) {
                            if (args != null) {
                                return selectList(statementId, args[0]);
                            }else {
                                return selectList(statementId, null);
                            }

                        }
                        return selectOne(statementId, args[0]);
                    case "update":
                        break;
                    case "delete":
                        break;

                }

                return null;
            }
        });
        return (T) o;
    }
}
