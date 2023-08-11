package com.exaple.sqlSession;

import com.exaple.config.XmlConfigBuilder;
import com.exaple.pojo.Configuration;
import org.dom4j.DocumentException;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    /**
     * 1.解析 配置文件,封装容器对象
     * 2.创建SqlSessionFactory工厂对象
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream) throws DocumentException {
        // 解析配置威廉
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(inputStream);

        // 创建工厂对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
