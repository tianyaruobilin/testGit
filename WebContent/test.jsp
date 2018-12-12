<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<a id="modal-863945" href="#modal-container-863945" role="button"
		class="btn" data-toggle="modal">触发遮罩窗体</a>
	<div class="modal fade" id="modal-container-863945" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">标题</h4>
				</div>
				<div class="modal-body">${requestScope.ttt }</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$("#modal-863945").click(function(){
		action = "http://localhost:8080/nonglianwang/TTest"
		form = $("<form></form>")
        form.attr('action',action)
        form.attr('method','get')
        input1 = $("<input type='hidden' name='input1'/>")
        input1.attr('value','input1 value')
        input2 = $("<input type='text' name='textinput' value='text input' />")
        form.append(input1)
        form.append(input2)
        form.appendTo("body")
        form.css('display','none')
        form.submit()
	})
</script>
</body>
</html>