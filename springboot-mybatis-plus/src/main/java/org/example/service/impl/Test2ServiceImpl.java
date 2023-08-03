package org.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Test;
import org.example.entity.Test2;
import org.example.mapper.Test2Mapper;
import org.example.service.Test2Service;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Test2ServiceImpl extends ServiceImpl<Test2Mapper, Test2> implements Test2Service {

    public void insert() {
        new Test2().setUsername("anthony").setParentId(0).setAmount(BigDecimal.ZERO).insert();
    }

    @Override
    public void insertRuntimeException() {
        new Test2().setUsername("anthony").setParentId(0).setAmount(BigDecimal.ZERO).insert();
        throw new RuntimeException("插入报错了");
    }
}
