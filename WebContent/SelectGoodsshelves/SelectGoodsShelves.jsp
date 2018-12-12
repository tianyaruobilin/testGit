<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="charset" content="UTF-8">
<meta charset="utf-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">

<!-- 引入bootstrap样式 -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 引入bootstrap-table样式 -->
<link
	href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css"
	rel="stylesheet">

<!-- jquery -->
<script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- bootstrap-table.min.js -->
<script
	src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script
	src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<base target="iframename" />
<title>查询货架</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div id="reportTableDiv">
				<div id="toolbar">
					<button id="button" class="btn btn-default">删除选中的商品</button>
				</div>
				<table id="table">
				</table>
			</div>
			<script type="text/javascript">
			let $table = $('#table');
			$(function() {
			    $.ajax({
			        url: '<%=request.getContextPath() + "/GoodsShelves"%>',
								type : 'GET',
								dataType : "json",
								data : {
									"uno" : "${sessionScope.user.uno }"
								},
								success : function(data) {
									$('#table')
											.bootstrapTable(
													{
														method : 'get',
														editable : true,//开启编辑模式  
														cache : false,
														height : 650,
														striped : true,
														pagination : true,
														toolbar : '#toolbar',
														clickEdit : true,
														showToggle : true,
														showPaginationSwitch : true, //显示切换分页按钮
														pageSize : 10,
														contentType : "application/x-www-form-urlencoded", //重要选项,必填
														pageNumber : 1,
														pageList : [ 10, 15,
																20, 50, 70, 100 ],
														sidePagination : 'client',
														search : true,
														showColumns : false,
														cardView : false, //是否显示详细视图
														detailView : false, //是否显示父子表
														showRefresh : true,
														/* showExport : false, //是否显示导出按钮 */
														/* exportDataType: 'selected', */
														/* exportTypes : [ 'Excel' ], */
														search : true,
														clickToSelect : true,
														columns : [
																{
																	field : "checked",
																	checkbox : true
																},
																{
																	field : "goodsno",
																	title : "商品编号",
																	align : "center",
																	valign : "middle",
																	sortable : "true"
																},
																{
																	field : "goodsname",
																	title : "商品名称",
																	align : "center",
																	valign : "middle",
																	sortable : "true"
																},
																{
																	field : "goodscategory",
																	title : "商品类目",
																	align : "center",
																	valign : "middle",
																	sortable : "true"
																},
																{
																	field : "goodsprice",
																	title : "商品价格",
																	align : "center",
																	valign : "middle",
																	sortable : "true"
																},
																{
																	field : "goodsdescript",
																	title : "商品描述",
																	align : "center",
																	valign : "middle",
																	sortable : "true"
																},
																{
																	field : "goodsweight",
																	title : "商品库存",
																	align : "center",
																	valign : "middle",
																	sortable : "true"
																},
																{
																	field : "preferential",
																	title : "是否特价",
																	align : "center",
																	valign : "middle",
																	sortable : "true"
																} ],
														idField : "goodsno",
														data : data,

													});

								}
							});
				});
			$("#button").click(function(){
				if(confirm("确定删除选中的商品吗？")==true){
					
					var todelete=$table.bootstrapTable('getSelections');
					if(todelete.length==0){
						alert('您没有选择商品');return false;
					}
					var list=[];
					var todeletegoodsno="";
						for (var i = 0; i < todelete.length; i++) {
							todeletegoodsno=todelete[i].goodsno;
							list.push(todeletegoodsno);
						}
					//到这里list里面的数据就是要发送到服务器端的商品的商品编号
					var str_goodsnolist=list.join('-');
					//利用ajax将数据发送给服务器端，进行数据的删除
					$.ajax({
						url:'<%=request.getContextPath() + "/GoodsShelves"%>',
						type : 'GET',
						data : {
							"goodsno" : str_goodsnolist
						},
						success:function(){
							location.reload(true);
							alert('删除成功');
						},
						error:function(){
							alert('删除失败')
						}
					});
				}else{
					return false;
				}
			});
			</script>
		</div>
	</div>
</body>
</html>