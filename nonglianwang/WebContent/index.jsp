<%@page import="java.util.Date"%>
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
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- bootstrap-table.min.js -->
<script
	src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<!-- 引入中文语言包 -->
<script
	src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<link
	href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css"
	rel="stylesheet">
<base target="iframename" />
<meta name="charset" content="UTF-8">
<meta charset="utf-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<base target="iframename" />
<title>农联网</title>
<link rel="stylesheet" href="CSS/Mystyle.css">
</head>

<script type="text/javascript">
	if (top != window) {
		top.location.href = window.location.href;
	}
	function _addFavorite() {
		var url = window.location;
		var title = document.title;
		var ua = navigator.userAgent.toLowerCase();
		if (ua.indexOf("360se") > -1) {
			alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
		} else if (ua.indexOf("msie 8") > -1) {
			window.external.AddToFavoritesBar(url, title); //IE8
		} else if (document.all) {//IE类浏览器
			try {
				window.external.addFavorite(url, title);
			} catch (e) {
				alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
			}
		} else if (window.sidebar) {//firfox等浏览器；
			window.sidebar.addPanel(title, url, "");
		} else {
			alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
		}
	}
</script>
<body>
	<div>
		<div class="body" id="back">
			<div class="header">
				<div class="shoucanglan">
					<div class="navttopL">
						<ul>
							<li>|&nbsp;<a href="javascript:void(0);"
								onclick="_addFavorite()" role="button">收藏本站</a>&nbsp;|
							</li>
							<c:if
								test="${sessionScope.identityString eq '普通用户'||sessionScope.identityString eq '会员用户'||not empty sessionScope.identityString }">
								<li>&nbsp;<a href="javascript:void(0);" role="button">我的订单</a>&nbsp;|
								</li>
								<li><a id="modal-863945" role="button" data-toggle="modal"
									onclick="showCar()">我的购物车</a>&nbsp;|</li>
							</c:if>
						</ul>
					</div>

					<div class="navttopR">
						<table>
							<tr>
								<c:if test="${empty sessionScope.user }">
									<td>|&nbsp;<a href="regist/regist.jsp">注册</a>&nbsp;|
									</td>
									<td>&nbsp;<a href="login/login.jsp">登陆</a>&nbsp;|
									</td>
								</c:if>
								<c:if test="${not empty sessionScope.user }">
									<td>尊敬的<c:if
											test="${sessionScope.identityString eq '管理员' }">管理员</c:if> <c:if
											test="${sessionScope.identityString eq '商家' }">商家</c:if> <c:if
											test="${sessionScope.identityString eq '会员用户' }">会员用户</c:if>
										<c:if test="${sessionScope.identityString eq '普通用户' }">普通用户</c:if>&nbsp;<a
										href="javacript:void(0);">${sessionScope.user.username }</a>&nbsp;
									</td>
									<td>&nbsp;,欢迎您回来&nbsp;</td>
									<td><a
										href="<%=request.getContextPath() + "/Invalidate"%>">退出</a></td>
								</c:if>

							</tr>
						</table>
					</div>
				</div>
				<div class="LOGO">
					<div class="LOGO1">
						<img src="img/LOGO.png" alt="农联网">
					</div>
					<div class="LOGO2">
						<h3>农联网</h3>
					</div>
					<div class="index">
						<table>
							<tr>
								<td class="indexleft"></td>
								<td class="indexmain"></td>
								<td class="indexright"></td>
							</tr>
						</table>
						<form class="indexform" action="index.html" method="get"
							id="HOTform">
							<input type="text" name="HOTindex" class="inputHOT1" /><input
								type="submit" class="inputHOT2" />
						</form>
						<div class="showHOT">
							<font style="font-size: 14px; color: red;">热门搜索：土杂鸡 黄牛肉 龙虾
								螃蟹 湘莲</font>
						</div>
					</div>
				</div>
				<div class="navbg">
					<div class="nav">
						<nav>
							<a href="<%=request.getContextPath() + "/index.jsp"%>" class="a"
								target="_top">首页</a>
							<!-- 新闻模块，针对不同身份的用户 -->
							<c:if test="${sessionScope.identityString eq '管理员'}">
								<a href="javascript:void(0);" class="a">冻结用户</a>
							</c:if>
							<c:if test="${sessionScope.identityString eq '商家'}">
								<a
									href="<%=request.getContextPath() + "/SelectGoodsshelves/SelectGoodsShelves.jsp"%>"
									class="a">查看货架</a>
							</c:if>
							<c:if
								test="${sessionScope.identityString eq '普通用户'||sessionScope.identityString eq '会员用户'||empty sessionScope.identityString }">
								<a href="javascript:void(0);" class="a">查询商品</a>
							</c:if>

							<!-- 商家入驻模块，针对不同用户 -->
							<c:if test="${sessionScope.identityString eq '商家'}">
								<a href="shopmanager/shopmanager.jsp" class="a">添加商品</a>
							</c:if>
							<c:if test="${sessionScope.identityString eq '会员用户'}">
								<a href="businessmanlogin/businessmanLogin.jsp" class="a">会员专区</a>
							</c:if>
							<c:if
								test="${empty sessionScope.identityString||sessionScope.identityString eq '普通用户'}">
								<a href="businessmanlogin/businessmanLogin.jsp" class="a">商家入驻</a>
							</c:if>
							<c:if test="${sessionScope.identityString eq '管理员'}">
								<a href="VerifierServlet?pageCode=1" class="a">审核商家</a>
							</c:if>
							<c:if test="${sessionScope.identityString eq '商家'}">
								<a href="javascript:void(0);" class="a">农产品批发预定</a>
							</c:if>
							<c:if
								test="${empty sessionScope.identityString||sessionScope.identityString eq '会员用户'||sessionScope.identityString eq '普通用户'||sessionScope.identityString eq '管理员'}">
								<a
									href="<%=request.getContextPath() + "/scanGoods/scanGoods.jsp"%>"
									class="a">产品选购</a>
							</c:if>


							<!-- 会员中心模块，针对不同的用户 -->
							<c:if test="${sessionScope.identityString eq '普通用户'}">
								<a href="javascript:void(0);" class="a">成为会员</a>
							</c:if>
							<c:if test="${sessionScope.identityString eq '管理员'}">
								<a href="javascript:void(0);" class="a">查询商品</a>
							</c:if>
							<c:if test="${sessionScope.identityString eq '商家'}">
								<a href="javascript:void(0);" class="a">添加会员商品</a>
							</c:if>
							<c:if
								test="${sessionScope.identityString eq '会员用户'||empty sessionScope.identityString}">
								<a href="javascript:void(0);" class="a">会员中心</a>
							</c:if>

							<a href="<%=request.getContextPath() + "/aboutus/aboutas.jsp"%>"
								class="a">关于我们</a>
						</nav>
					</div>
				</div>
			</div>
			<div class="content">
				<div class="iframemain" id="iframemain">
					<iframe src="initframe.html" width="100%" height="700px"
						name="iframename" frameborder="0" scrolling="no"></iframe>
				</div>
			</div>
			<div class="footer">
				<p>
					<font size="12px">@天涯若比邻<br />QQ:2681280434<br />电话:13657097058
					</font>
				</p>
			</div>
		</div>
	</div>
	<!-- 模态框，装的是购物车 -->
	<div class="modal fade" id="modal-container-863945" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 1500px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">我的购物车</h4>
				</div>
				<div class="modal-body" id="modal-body">
					<div class="container">
						<div class="row">
							<table id="table">
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$("#modal-container-863945").on("hidden.bs.modal", function() { 
	$(this).removeData("bs.modal"); 
	});
$('#modal-container-863945').on('show.bs.modal', function () {
	let $table = $('#table');
$.ajax({
    url: '<%=request.getContextPath() + "/ShowCar"%>',
				type : 'GET',
				dataType : "json",
				data : {
					"uno" : "${sessionScope.user.uno }",
				},
				success : function(data) {
					$('#table').bootstrapTable(
									{
										method : 'get',
										/* editable : true,//开启编辑模式   */
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
										pageList : [ 10, 15,20, 50, 70, 100 ],
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
													field : "goodsprice",
													title : "商品单价",
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
													field : "goodsdescript",
													title : "商品描述",
													align : "center",
													valign : "middle",
													sortable : "true"
												},
												{
													field : "goodsweight",
													title : "商品重量",
													align : "center",
													valign : "middle",
													sortable : "true"
												},{
													field : "totalPrice",
													title : "价格小计",
													align : "center",
													valign : "middle",
													sortable : "true"
												},{
									                field: 'Desc',
									                title: '操作',
									                formatter : function operateFormatter(value, row, index) {
									                    return [
									                        '<button  type="button" class="btn btn-default btn-ms" style="background:#eeaa00;">编辑</button>','<button  type="button" class="btn btn-primary btn-ms">删除</button>' ]
									                        .join('');
									            }
									            },],
										idField : "ono",
										data : data,

									});

				}
			});
})
						function showCar() {
							$('#modal-container-863945').modal('show');
						}
					</script>
</html>
