<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="charset" content="UTF-8">
<meta charset="utf-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<base target="iframename" />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>商品选购</title>
</head>
<body>

	<div class="row" id="row">
		<div class="row" id="secondRow"></div>
		<div id="Pagination"></div>
		<div id="doing"></div>
	</div>
</body>
<script type="text/javascript">
function dataHandler(data){//操作通过ajax获取的JSON数据串的方法
	var goodslist=data.goodslist;
	for(var i in goodslist){
		var goodsname=goodslist[i].goodsname;
		var goodsimgpath=goodslist[i].goodsimgpath;
		var goodsprice=goodslist[i].goodsprice;
		var goodsno=goodslist[i].goodsno;
		var uno=goodslist[i].uno;
		var goodsdescript=goodslist[i].goodsdescript;
		var goodscategory=goodslist[i].goodscategory;
		var pdescript=$("<p>"+goodsdescript+"</p>");
		var h3_name_price=$("<h3>"+goodsname+"&nbsp;&nbsp;￥:"+goodsprice+"/kg</h3>");
		var basePath='<%=request.getContextPath()%>';
		var img=$("<img src='"+basePath+"/uploadimg/"+goodsimgpath+"' alt='"+goodsname+"' title='"+goodsname+"&nbsp;&nbsp;"+goodsdescript+"' style='overflow:hidden;height:150px;width:200px;'/>");
		var a=$("<a href='javascript:void(0);'></a>");/* 用a标签阔起img标签 */
		a=img.wrapAll(a);
		var thumbnail=$("<div class='thumbnail'></div>");
		thumbnail=thumbnail.append(a);
		/* 到这里获取了thumbnail元素了，接下来需要获取caption元素 */
		var button=$("<a href='javascript:void(0);' class='btn btn-primary'>加入购物车</a>");
		var uno='${sessionScope.user.uno }';
		var input_AddCar=$("<form class=\"bs-example bs-example-form\" action=\""+basePath+"/AddToCar\" role=\"form\"><div><div class=\"input-group\"><input type=\"number\" name=\"goodsKG\" min=\"1\" max=\"100\" step=\"0.1\" placeholder=\"输入重量\" onkeyup=\"value=value.replace(/([0-9]+\.[0-9]{2})[0-9]*/,'')\"  class=\"form-control\"><input type=\"hidden\" name=\"goodsno\" value=\""+goodsno+"\"/><span class=\"input-group-btn\"><input type=\"hidden\" name=\"uno\" value=\""+uno+"\"/><button class=\"btn btn-default\" type=\"submit\">加入购物车</button></span></div><!-- /input-group --></div><!-- /.col-lg-6 --></div><!-- /.row --></form>");
		var div_caption=$("<div class='caption text-center'></div>");
		var p_descript=$("<p>"+goodsdescript+"</p>");
		div_caption.append(input_AddCar);
		h3_name_price.insertBefore(input_AddCar);
		p_descript.insertBefore(input_AddCar);
		div_caption.insertAfter(a);
		var div_col_md=$("<div class='col-sm-6 col-md-3'></div>");
		thumbnail.append(div_caption);
		div_col_md.append(thumbnail);
		$("#secondRow").append(div_col_md);
	}
		//到这里商品就循环完了，接下来需要获取分页框了
		var startCode=data.startCode;
		var endCode=data.endCode;
		var previous=data.previousHref;
		var previousStyleCode=previous==""?"disabled":"";
		console.log(previousStyleCode);
		var currentPage=data.currentPage;
		var currentCodeStyle="active";
		var next=data.nextHref;
		var nextStyleCode=next==""?"disabled":"";
		console.log(nextStyleCode);
		var pager=$("<ul class=\"pager\"><li class=\""+previousStyleCode+"\"><a href=\"javascript:void(0);\" onclick=\"getAndHandlerData("+previous+")\">上一页</a></li><li id=\"next\" class=\""+nextStyleCode+"\"><a href=\"javascript:void(0);\" onclick=\"getAndHandlerData("+next+")\">下一页</a></li></ul>")						
		$("#Pagination").append(pager);
		for (var i = startCode; i <=endCode; i++) {
			currentCodeStyle=currentPage==i?currentCodeStyle:"";
			var pageCode=$("<li class="+currentCodeStyle+"><a href=\"javascript:void(0);\" onclick=\"getAndHandlerData("+i+")\">"+i+"</a></li>");
			pageCode.insertBefore("#next");
		}
}

function getAndHandlerData(IntPageCode){
	$.ajax({
		url:'<%=request.getContextPath()%>/ScanAllGoods',
		type : 'Get',
		dataType : 'json',
		data : {
			'pagecode' : IntPageCode
		},
		timeout : 15000,//响应超时后跳转到error处理
		// 请求发送之前（发送请求前可修改XMLHttpRequest对象的函数，如添加自定义HTTP头。）
		beforeSend : function(XMLHttpRequest) {
			$("#doing").html("正在拉取商品中，请稍后···");
		},
		success : function(data) {
			$("#secondRow").empty();
			$("#Pagination").empty();
			dataHandler(data);
		},
		complete : function(XMLHttpRequest, textStatus) {
			$("#doing").empty();
		},
		// 请求失败时调用此函数。
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#doing").html('唉~ 获取商品失败了');
		}
	})
}
	
	$(function(){
		$.ajax({
			url:'<%=request.getContextPath()%>/ScanAllGoods',
			type : 'Get',
			dataType : 'json',
			data : {
				'catagory' : 'all'
			},
			timeout : 15000,//响应超时后跳转到error处理
			// 请求发送之前（发送请求前可修改XMLHttpRequest对象的函数，如添加自定义HTTP头。）
			beforeSend : function(XMLHttpRequest) {
				$("#doing").html("正在拉取商品中，请稍后···");
			},
			success : function(data) {
				$("#secondRow").empty();
				$("#Pagination").empty();
				dataHandler(data);
			},
			complete : function(XMLHttpRequest, textStatus) {
				$("#doing").empty();
			},
			// 请求失败时调用此函数。
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#doing").html('唉~ 获取商品失败了');
			}
		})
	})
</script>
</html>