/**
 * 由regist.jsp页面引入
 */
/**
 * 这是一个获取ajax对象xmlHttpRequest对象的方法
 */
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
	// 获取xmlhttpRequest对象
	var xmlHttpRequest = getXMLHttpRequest();
	// 获取regist页面上的用户名输入框
	var e_username = $("#username");
	e_username.blur(function() {
		// 获取输入框中的值
		var v_uesername = e_username.val();
		$.get("../Ajax", {
			username : v_uesername
		}, function(data) {
			var object = eval(data);
			if (object != null) {
				$("#ajax").html(object);
			}else {
				$("#ajax").html("");
			}
		}, "text")
	});
});