<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="charset" charset="UTF-8">
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<base target="iframename">
<title>登陆页面</title>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="text-center">${requestScope.errmsg }</div>
	<br/>
	<form action=<%=request.getContextPath() + "/Login"%> method="get"
		role="form" class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-4 control-label">用户名</label>
			<div class="col-sm-4">
				<input type="text" name="username" class="form-control" value="" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-4 control-label">密码</label>
			<div class="col-sm-4">
				<input type="password" name="password" class="form-control" />
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-2">
				<input type="submit" value="提交" class="btn btn-defalut" />
			</div>
			<div class="col-sm-2">
				<input type="reset" value="重置" class="btn btn-defalut" />
			</div>
		</div>
	</form>
</body>
</html>