package com.exaple.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.exaple.io.Resources;
import com.exaple.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XmlConfigBuilder {

    private Configuration configuration;

    public XmlConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        // 解析数据源
        Properties properties = new Properties();
        List<Element> list = rootElement.selectNodes("//property");
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }

        // 创建数据源对象
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getProperty("driverClassName"));
        druidDataSource.setUrl(properties.getProperty("url"));
        druidDataSource.setUsername(properties.getProperty("username"));
        druidDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(druidDataSource);

        // 解析映射配置文件
        List<Element> mappersList = rootElement.selectNodes("//mapper");
        for (Element element : mappersList) {
            String path = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(path);

            // 解析映射配置文件的对象
            XMLMaperBuilder xmlMaperBuilder = new XMLMaperBuilder(configuration);
            xmlMaperBuilder.parse(resourceAsStream);

        }

        return configuration;
        
    }
}
