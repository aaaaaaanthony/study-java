package com.exaple.config;

import com.exaple.pojo.Configuration;
import com.exaple.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMaperBuilder {

    private Configuration configuration;

    public XMLMaperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream resourceAsStream) throws DocumentException {

        Document document = new SAXReader().read(resourceAsStream);

        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sql = element.getTextTrim();

            String statementId = namespace + "." + id;
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setStatementId(statementId);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sql);
            mappedStatement.setSqlCommandType("select");
            configuration.getMappedStatementMap().put(statementId, mappedStatement);
        }



    }
}
