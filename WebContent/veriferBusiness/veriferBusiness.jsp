<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="img/Tree_32px_530727_easyicon.net.ico"
	type="image/x-icon" />
<link rel="shortcut icon" href="img/Tree_32px_530727_easyicon.net.ico"
	type="image/x-icon" />
<meta name="charset" content="UTF-8">
<meta charset="utf-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<base target="iframename" />
<title>审核商家</title>
</head>
 <link type="text/css" rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css"/>
<body>
	<div id="info"></div>
    <div style="text-align: center;">
        <ul class="pagination" id="pagination1"></ul>
    </div>

</body>
<script type="text/javascript"
	src="<%=request.getContextPath() + "/JS/jquery-3.1.1.min.js"%>"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() + "/JS/jqpaginator.min.js"%>"></script>
<script type="text/javascript">
	/*利用ajax获取所有的待审核商家数量 */
	
	$("#paginator").ready(function(){


		$.ajax({
			url:"<%=request.getContextPath() + "/VerifierServlet"%>",
			type : 'GET',
			success : function() {
				var count=<%=request.getAttribute("count")%>
				if(count==0){
					alert('暂时没有待审核的商家哦');
				}else{
				$.jqPaginator('#pagination1',
						{
							pageSize:10,/*设置每页可见条目数量*/
							totalCounts:${requestScope.count }, /*需要显示的总条目数*/
							currentPage : 1,/*当前页*/
							onPageChange : function(num, type) {
								$.ajax({
									url:"<%=request.getContextPath() + "/GetVeriferBusiness"%>",
									tyspe:'GET',
									data:{
										page:num,
										count:10
									},
									dataType:'json',
									success:function(data){
										$("#info").empty();
										$("#info").html("<table border=\"1px\" class=\"table table-striped\" width=\"90%\"><tr height=\"40px\"><td colspan=\"8\" align=\"center\"><h3><font face=\"verdana\">审核商家</font></h3></td></tr>");
										$("#info").append("<thead><tr border=\"1px\"><td width=\"10%\" align=\"center\">用户UNO</td><td width=\"10%\" align=\"center\">商家真实名字</td><td width=\"10%\" align=\"center\">身份证号码</td><td width=\"10%\" align=\"center\">电话</td><td width=\"10%\" align=\"center\">商家地址</td><td width=\"10%\" align=\"center\">地址补充</td><td width=\"10%\" align=\"center\">通过</td><td width=\"10%\" align=\"center\">删除申请(不通过)</td></tr></thead>");
										$.each(data,function(i){
											$("#info").append("<tr border=\"1px\">");
											$("#info").append("<td width=\"10%\" align=\"center\">"+data[i].uno+"</td>");
											$("#info").append("<td width=\"10%\" align=\"center\">"+data[i].businessManName+"</td>");
											$("#info").append("<td width=\"10%\" align=\"center\">"+data[i].businessManIdCard+"</td>");
											$("#info").append("<td width=\"10%\" align=\"center\">"+data[i].businessManTel+"</td>");
											$("#info").append("<td width=\"10%\" align=\"center\">"+data[i].businessManAddr+"</td>");
											$("#info").append("<td width=\"10%\" align=\"center\">"+data[i].businessManAddrSupplement+"</td>");
											$("#info").append("<td width=\"10%\" align=\"center\"><a href=\"/nonglianwang/PassOrNotServlet?uno="+data[i].uno+"&action=pass\" onclick=\"return confirm('确定通过吗？');\">通过</a></td>");
											$("#info").append("<td width=\"10%\" align=\"center\"><a href=\"/nonglianwang/PassOrNotServlet?uno="+data[i].uno+"&action=delete\" onclick=\"return confirm('确定删除吗');\">不通过(删除)</a></td></tr>");
										});
											$("#info").append("</table>");
									},
									error:function(){
										alert("出错了");
									}
								});
			                    	
							}
						});
				}
			},
			error : function() {
				alert('ajax请求出错');
			}
		});
	});
	
	
	

</script>
</html>