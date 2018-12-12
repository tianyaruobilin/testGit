<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商铺管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/css/fileinput.min.css">
<script
	src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/js/fileinput.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/js/locales/zh.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" role="form" method="post"
			enctype="multipart/form-data"
			action="<%=request.getContextPath() + "/AddGoodsServlet"%>">
			<div class="form-group">
				<label for="goodsname" class="col-sm-4 control-label">商品名称</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="goodsname"
						name="goodsname" placeholder="请输入不超过10个字的商品名称" />
				</div>
			</div>
			<div class="form-group">
				<label for="goodsname" class="col-sm-4 control-label">商品重量</label>
				<div class="col-sm-6">
					<input type="number"  onkeyup="value=value.replace(/([0-9]+\.[0-9]{3})[0-9]*/,'')" class="form-control" id="goodsweight"
						name="goodsweight" min="0" placeholder="商品重量" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-4 control-label">商品分类</label> <label
					class="radio-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios3" value="油脂"
					checked="checked"> 油脂
				</label> <label class="radio-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios4" value="渔产品" /> 渔产品
				</label> <label class="radio-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios4" value="蔬菜" /> 蔬菜
				</label> <label class="radio-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios4" value="瓜果" /> 瓜果
				</label><label class="radio-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios4" value="花贲" /> 花贲
				</label><label class="radio-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios4" value="其它" /> 其它
				</label>
			</div>
			<input type="hidden" name="uno" value="${sessionScope.user.uno }"/>
			<div class="form-group">
				<label for="myFile" class="col-sm-4 control-label">文件输入</label>
				<div class="col-sm-6">
					<input id="myFile" type="file" name="myFile" multiple
						class="file-loading">
					<p class="help-block">请选择png、jpg或gif格式图片</p>
				</div>
			</div>
			<div class="form-group">
				<label for="goodsprice" class="col-sm-4 control-label">上架价格</label>
				<div class="col-sm-6">
					<input type="number"  onkeyup="value=value.replace(/([0-9]+\.[0-9]{3})[0-9]*/,'')"
						class="form-control" id="goodsprice" name="goodsprice"
						placeholder="请输入商品上架价格，最多两位保留三位小数(人民币：元)" min="0" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="goodsdescript" class="col-sm-4 control-label">商品描述</label>
				<div class="col-sm-6">
				<textarea class="form-control" rows="3" name="goodsdescript" placeholder="请输入不超过50个字的商品描述"></textarea>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-6 col-sm-10">
					<button type="submit" class="btn btn-default">提交</button>
				</div>
			</div>

		</form>
	</div>
</body>
<script type="text/javascript">
$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("myFile", "/afterSale/uplode/photo");
});
var FileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function (ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);

        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            //showUploadedThumbs:false,
            // uploadClass: 'hidden',
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-info", //按钮样式
            dropZoneEnabled: false,//是否显示拖拽区域
            //minImageWidth: 150, //图片的最小宽度
            //minImageHeight: 150,//图片的最小高度
            //maxImageWidth: 150,//图片的最大宽度
            //maxImageHeight: 150,//图片的最大高度
            maxFileSize: 2048,//单位为kb，如果为0表示不限制文件大小
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            minFileCount: 1,
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            fileActionSettings : {
                showUpload: false,
                //showRemove: false
               showZoom:false
            }
        });

        //导入文件上传完成之后的事件
        $("#myFile").on("fileuploaded", function (event, data, previewId, index) {
            alert(data.response.code);
            // $("#divControl").hide();
        });
    }
    return oFile;
};
</script>
</html>