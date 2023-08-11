package com.exaple.pojo;

/**
 * 映射配置类,存放mapper.xml解析内容
 */
public class MappedStatement {

    // 唯一表示:statementId:namespace.id
    private String statementId;

    private String resultType;

    private String parameterType;

    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public MappedStatement() {
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }
}
