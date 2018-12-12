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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>商家入驻</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 商家入驻页面需要做的事情有
0、获取商家的真实姓名
1、获取商家的身份证、电话号码
2、获取商家的详细联系方式
3、获取商家地址，为了方便以后发货用
-->

</head>
<body>
	<div class="text-center">${requestScope.exceptionString }</div>
	<br/>
	<div id="applyBusiness" class="container">
		<form role="form" class="form-horizontal"
			action="<%=request.getContextPath() + "/BusinessManSupplement"%>"
			method="Get">
			<input type="hidden" name="uno" value="${sessionScope.user.uno }" />
			<table>
				<tr>
					<td align="right">您的真实姓名：</td>
					<td align="left"><input type="text" name="BusinessManName"
						value="" placeholder="您的真实姓名" class="form-control" /></td>
					<td align="left"><font color="red">${errors['BusinessManName'] }</font></td>
				</tr>
				<tr>
					<td align="right">您的身份证号：</td>
					<td align="left"><input type="text" name="BusinessManIdCard"
						value="" placeholder="身份证号哦" class="form-control" /></td>
					<td align="left"><font color="red">${errors['BusinessManIdCard'] }</font></td>
				</tr>
				<tr>
					<td align="right">您的电话号码：</td>
					<td align="left"><input type="text" name="BusinessManTel"
						value="" placeholder="11位手机号码" class="form-control" /></td>
					<td align="left"><font color="red">${errors['BusinessManTel'] }</font></td>
				</tr>
				<tr>
					<td align="right">您的真实地址：</td>
					<td align="left"><div class="form-group">
							<div class="col-sm-5 ">
								<select id="province" name="province" class="form-control"><option
										id="pleaseChoose">---请选择省---</option></select>
							</div>
							<label for="province" class="col-sm-1 control-label">省&nbsp;&nbsp;&nbsp;
							</label>
							<div class="col-sm-5">
								<select id="city" name="city" class="form-control"><option>---请选择市---</option>
								</select>
							</div>
							<label for="city" class="col-sm-1 control-label">市&nbsp;&nbsp;&nbsp;</label>
						</div></td>
					<c:if test="${not empty errors['province'] }">
						<td align="left"><font color="red">${errors['province'] }</font></td>
					</c:if>
					<c:if test="${not empty errors['city'] }">
						<td align="left"><font color="red">${errors['city'] }</font></td>
					</c:if>
				</tr>
				<tr>
					<td align="right">详细地址补充：</td>
					<td align="left"><textarea rows="3" cols="20"
							placeholder="在这补充您的真实地址" name="BusinessManAddrSupplement"
							class="form-control"></textarea></td>
					<td align="left"><font color="red">${errors['BusinessManAddrSupplement'] }</font></td>
				</tr>
				<tr>
					<td align="right"><input type="submit" value="提交"
						class="btn btn-default" /></td>
					<td align="center"><input type="reset" value="重置"
						class="btn btn-default" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script src=<%=request.getContextPath() + "/JS/jquery-3.1.1.min.js"%>
	type="text/javascript"></script>
<script type="text/javascript">
	function getXMLHttpRequest() {
		var xmlHttpRequest;
		try {// IE7+、Chrome、Firefox、Opera8.0+和Safari
			xmlHttpRequest = new XMLHttpRequest();
		} catch (e) {
			try {// IE7+
				xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {// IE5、6
					xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("不知道你用的是什么浏览器，太老了吧，快去换一个~~~");
				}
			}
		}
		return xmlHttpRequest;
	}
	$(document).ready(function() {
		var xmlhttp = getXMLHttpRequest();
		if(${not empty sessionScope.user }){
		//利用ajax判断是否申请过商家的信息
		$.ajax({
			url : "<%=request.getContextPath() + "/AjaxApplyOrNotServlet"%>", //设置请求的地址
			type : "GET",
			dataType : "text",
			data : {
				"nocache" : new Date().getTime(),
				"name":"${sessionScope.user.uno }"
			},
			success:function(data){
				if(data!=""){
					$("#applyBusiness").innerHTML="";
					$("#applyBusiness").html(data);
				}
			},
			error:function(){
				alert("获取用户是否申请过信息出错");
			}
		});
		}
		//利用ajax,获取所有的省
		$.ajax({
			url : "<%=request.getContextPath() + "/ProvinceServlet"%>",//设置请求的地址
			type : "GET",
			dataType : "json",
			data : {
				"nocache" : new Date().getTime(),
				"province" : null
			},
			//设置请求成功时的回调函数
			success : function(data) {
				var province = data["province"];
				for ( var i in province) {
					//获得省下拉菜单
					var eleprovince = document.getElementById("province");
					//创建option元素
					var eleoption = document.createElement("option");
					//为option元素添加value属性值
					eleoption.value = province[i];
					//为option创建文本节点
					var textNode = document.createTextNode(province[i]);
					//将文本节添加到option下
					eleoption.appendChild(textNode);
					//将option元素添加到下拉菜单下
					eleprovince.appendChild(eleoption);
				}
			},
			//设置请求失败时执行的回调函数
			error : function() {
				alert("请求失败");
			},
		});
	});
	/*利用ajax获取省下面对应的市区*/
	$("#province").change(function() {
		var checkText = $("#province").find("option:selected").text();
		$.ajax({
			url : "<%=request.getContextPath() + "/ProvinceServlet"%>", //设置请求的地址
			type : "GET",
			dataType : "json",
			data : {
				"nocache" : new Date().getTime(),
				"province" : checkText
			},
			asycn: "false",
			//设置请求成功时的回调函数
			success : function(data) {
				var city = data["citys"];
				$("#city").empty();
				for ( var i in city) {
					//获得市下拉菜单
					var elecity = document.getElementById("city");
					//创建option元素
					var eleoption = document.createElement("option");
					//为option元素添加value属性值
					eleoption.value = city[i];
					//为option创建文本节点
					var textNode = document.createTextNode(city[i]);
					//将文本节添加到option下
					eleoption.appendChild(textNode);
					//将option元素添加到下拉菜单下
					elecity.appendChild(eleoption);
				}
			},
			//设置请求失败时执行的回调函数
			error : function() {
				alert("请求失败");
			}
		});
	});
</script>
</html>