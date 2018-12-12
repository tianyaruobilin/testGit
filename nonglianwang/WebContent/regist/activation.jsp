<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<base target="iframename">
<title>激活</title>
</head>
<body>
${requestScope.msg }
<c:if test="${not empty requestScope.user }">
<form action="Mail" method="get">
<input type=text value="${user.e_mail }" name="activationemail"/>
<input type="hidden" value="${user.uno }" name="uno"/>
<br/>
<input type="submit"value="提交"/>
</form>
</c:if>
</body>
</html>