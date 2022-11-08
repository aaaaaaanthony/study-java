package com.mmzcg.annotation;

// 导入必需的 java 库

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// 扩展 HttpServlet 类
@WebServlet("/hello")
public class HelloWorld extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // 执行必需的初始化
        message = "注解执行必需的初始化";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy() {
        // 什么也不做
        System.out.printf("注解我就没见过销毁过");
    }
}
