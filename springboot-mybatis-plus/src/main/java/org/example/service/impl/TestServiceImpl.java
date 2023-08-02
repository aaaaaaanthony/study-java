package org.example.service.impl;


import org.example.entity.Test;
import org.example.mapper.TestMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {


}
