package com.exaple.executor;

import com.exaple.config.BoundSql;
import com.exaple.pojo.Configuration;
import com.exaple.pojo.MappedStatement;
import com.exaple.utils.GenericTokenParser;
import com.exaple.utils.ParameterMapping;
import com.exaple.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleExecutor implements Executor{

    private Connection connection = null;

    private PreparedStatement preparedStatement = null;

    private ResultSet resultSet = null;

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object param) throws Exception {
        // 获取链接
        connection = configuration.getDataSource().getConnection();
        /**
         * 预编译对象
         * 带占位符的sql语句,要替换成 带问号的
         * 获取要执行的slq语句
         *  select * from product where id = #{id} and username = #{username}
         *  select * from product where id = ? and username = ?
         */
        String sql = mappedStatement.getSql();

        BoundSql boundSql = getBoundSql(sql);
        String finalSql = boundSql.getFinalSql();

        preparedStatement = connection.prepareStatement(finalSql);

        // 设置参数
        String parameterType = mappedStatement.getParameterType();
        if (parameterType!=null) {
            Class<?> aClass = Class.forName(parameterType);
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                // id | username
                String paramName = parameterMapping.getContent();

                Field declaredField = aClass.getDeclaredField(paramName);
                declaredField.setAccessible(true);

                Object value = declaredField.get(param);

                preparedStatement.setObject(i+1, value);
            }
        }


        // 执行sql,发起查询

        List<E> list = new ArrayList<>();

        String resultType = mappedStatement.getResultType();

        Class<?> aClass1 = Class.forName(resultType);
        Object o = aClass1.newInstance();
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();

            for (int i = 1 ;i <= metaData.getColumnCount(); i++) {
                // 字段名  id name
                String columnName = metaData.getColumnName(i);
                // 字段值
                Object object = resultSet.getObject(columnName);

                // 封装对象
                // 属性描述器
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, aClass1);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, object);
            }
            list.add((E) o);
        }
        // 处理返回结果集
        return list;
    }

    /**
     * @param sql 将#{}  带成问号的替换
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // #{} 替换成 ?
        // #{}里面的值 保存下来 ParameterMapping
        String finalSql = genericTokenParser.parse(sql);

        // #{} 里面的值的集合
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(finalSql, parameterMappings);
        return boundSql;
    }

    @Override
    public void close() {
        try {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
