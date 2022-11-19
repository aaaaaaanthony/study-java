<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<h2>servlet的注解版本上上传文件</h2>

<h1>单个文件上传</h1>
<form method="post" action="/upload" enctype="multipart/form-data">
    选择一个文件:
    <input type="file" name="file"/>
    <br/><br/>
    <input type="submit" value="上传"/>
</form>


<h1>多个文件上传</h1>
<form  method="post" action="/mutilupload" enctype="multipart/form-data">
    用户名：<input type="text" name="usename"> <br/>
    上传文件：<input type="file" name="file"><br/>
    上传文件： <input type="file" name="file"><br/>
    <input type="submit" value="提交"/>
</form>


</body>
</html>
