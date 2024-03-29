package org.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.Test;
import org.example.entity.Test2;
import org.example.mapper.TestMapper;
import org.example.service.TestService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Override
    public void insert() {
        new Test().setUsername("anthony").setParentId(0).setAmount(BigDecimal.ZERO).insert();
    }

    @Override
    public void insertRuntimeException() {
        new Test().setUsername("anthony").setParentId(0).setAmount(BigDecimal.ZERO).insert();
        throw new RuntimeException("插入报错了");
    }
}
