<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta name="charset" content="UTF-8">
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<title>成功信息页面</title>
</head>
</head>
<body>
${requestScope.msg }将在三秒后自动跳转到主页
<%	
	String path=3+";"+request.getContextPath()+"/index.jsp";
	response.setHeader("REFRESH", path);
%>
</body>
</html>