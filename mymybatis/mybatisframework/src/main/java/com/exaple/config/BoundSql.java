package com.exaple.config;

import com.exaple.utils.ParameterMapping;

import java.util.List;

public class BoundSql {

    private String finalSql;

    private List<ParameterMapping> list;

    public BoundSql(String finalSql, List<ParameterMapping> list) {
        this.finalSql = finalSql;
        this.list = list;
    }

    public String getFinalSql() {
        return finalSql;
    }

    public List<ParameterMapping> getList() {
        return list;
    }

    public List<ParameterMapping> getParameterMappings() {
        return list;
    }
}
