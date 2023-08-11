package com.exaple.io;

import java.io.InputStream;

public class Resources {

    // 根据配置文件的路径,加载配置成字节输入流,存到内存中
    public static InputStream getResourceAsStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
