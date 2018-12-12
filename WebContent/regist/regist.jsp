<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="charset" content="UTF-8">
<meta charset="UTF-8">
<base target="iframename">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<title>注册</title>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="registform container">
		<div class="text-center">${requestScope.msg }</div>
		<br/>
		<form action=<%=request.getContextPath() + "/Regist"%> method="get"
			role="form" class="form-horizontal">
			<div class="form-group">
				<label for="username" class="col-sm-4 control-label">用户:</label>
				<div class="col-sm-6">
					<input type="text" name="username" id="username"
						class="form-control" placeholder="输入您的用户名"
						value="${user.username }" />
				</div>
			<c:if test="${not empty errors['username'] }">
				<span>${errors['username'] }</span>
			</c:if>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-4 control-label">密码:</label>
				<div class="col-sm-6">
					<input type="password" name="password" class="form-control"
						id="password" placeholder="输入您的密码" />
				</div>
			<c:if test="${not empty errors['password'] }">
				<span>${errors['password'] }</span>
			</c:if>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-4 control-label">出生日期:</label>
				<div class="col-sm-6">
					<input type="date" name="borntime" class="form-control"
						value="${user.borntime }" />
				</div>
			<c:if test="${not empty errors['borntime'] }">
				<span>${errors['borntime'] }</span>
			</c:if>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-4 control-label">E-mail:</label>
				<div class="col-sm-6">
					<input type="email" name="e_mail" class="form-control"
						value="${user.e_mail }" placeholder="用于激活您的账户" />
				</div>
			<c:if test="${not empty errors['email'] }">
				<span>${errors['email'] }</span>
			</c:if>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">性别:</label>
				<div class="col-sm-4">
					<div class="form-group">
						<div class="col-sm-2">
							<input type="radio" name="sex" value="nv" checked="checked"
								class="form-control">
						</div>
						<label class="col-sm-1 control-label">女&nbsp;&nbsp;&nbsp;</label>
						<div class="col-sm-2">
							<input type="radio" name="sex" value="nan" class="form-control">
						</div>
						<label class="col-sm-1 control-label">男&nbsp;&nbsp;&nbsp;</label>
					</div>
				</div>
			<c:if test="${not empty errors['sex'] }">
				<span>${errors['sex'] }</span>
			</c:if>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">验证码:</label>
				<div class="col-sm-4">
					<input type="text" name="varifyCode" value="" size="3"
						placeholder="不分大小写" class="form-control" />
				</div>
				<span><a href="javascript:;" onclick="newImage();">换一张</a></span><span><img
					id="IMG" src="<c:url value='/varifyCode'></c:url>" /></span>
			<c:if test="${not empty errors['varifyCode'] }">
				<span>${errors['varifyCode'] }</span>
			</c:if>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-sm-4"></div>
					<div class="col-sm-2">
						<input type="submit" value="提交" class="btn btn-default" />
					</div>
					<div class="col-sm-4">
						<input type="reset" value="重置" class="btn btn-default" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<!-- 	c:标签不能放置在外部引入文件中，否则出错 -->
<script src="../JS/jquery-3.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript" async="async">
	function newImage() {
		// 获得img元素
		var element_img = document.getElementById("IMG");
		// 修改img元素的src属性
		element_img.src = "<c:url value='/varifyCode'/>?"
				+ new Date().getTime();
	}
</script>
<script src="../JS/regist.js" type="text/javascript" async="async"
	charset="UTF-8"></script>
</html>