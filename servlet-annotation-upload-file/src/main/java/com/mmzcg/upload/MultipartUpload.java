package com.mmzcg.upload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * 多个文件上传和其余参数一起提交
 */
@WebServlet("/mutilupload")
@MultipartConfig
public class MultipartUpload extends HttpServlet {

    private String message;

    public void init() {
        // 执行必需的初始化
        message = "执行必需的初始化";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // form表单中的参数
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            System.out.println("参数名："+name + "\t" +"结果："+ request.getParameter(name));
        }

        for (Part part : request.getParts()) {

            // 判断是不是上传文件
            String submittedFileName = part.getSubmittedFileName();
            if (submittedFileName == null || submittedFileName.equals("")) {
                continue;
            }


            // 获取文件的路径
            String header = part.getHeader("content-disposition");
            String path = header.substring(header.indexOf("filename=") + 10, header.length() - 1);
            System.out.println("打印请求头:"+header);

            // 获取文件名
            String name = getRealName(path);

            // 获取文件的存放目录
            String dir = getDir(name);

            String realPath = this.getServletContext().getRealPath("/upload" + dir);
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 对拷流
            try {
                InputStream inputStream = part.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(new File(file, name));
                int len = -1;
                byte[] bytes = new byte[1024];
                while ((len = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
                // 关闭资源
                outputStream.close();
                inputStream.close();

                // 删除临时文件
                part.delete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("文件上传成功！");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {
        // 什么也不做
        System.out.printf("我就没见过销毁过");
    }

    /**
     * 根据文件的路径获取文件真实名称
     *
     * @param path
     *            文件的路径
     * @return 文件名称
     */
    public static String getRealName(String path) {
        int index = path.lastIndexOf("\\");

        if (index == -1) {
            index = path.lastIndexOf("/");
        }

        return path.substring(index + 1);
    }

    /**
     * 根据文件名返回一个目录
     *
     * @param name
     *            文件名称
     * @return 目录
     */
    public static String getDir(String name) {
        int i = name.hashCode();
        String hex = Integer.toHexString(i);
        int j = hex.length();

        for (int k = 0; k < 8 - j; k++) {
            hex = "0" + hex;
        }

        return "/" + hex.charAt(0) + "/" + hex.charAt(1);
    }
}
