package com.exaple.executor;

import com.exaple.pojo.Configuration;
import com.exaple.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object param) throws Exception;

    void close();
}
