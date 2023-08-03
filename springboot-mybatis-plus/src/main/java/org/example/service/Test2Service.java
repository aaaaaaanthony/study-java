package org.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Test2;


public interface Test2Service extends IService<Test2> {

    void insert();

    void insertRuntimeException();
}
