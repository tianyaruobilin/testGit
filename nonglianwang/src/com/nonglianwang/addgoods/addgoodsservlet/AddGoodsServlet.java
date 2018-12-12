package com.nonglianwang.addgoods.addgoodsservlet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nonglianwang.addgoods.addgoodsservice.AddGoodsService;
import com.nonglianwang.addgoods.goodsbean.GoodsBean;



/**
 * Servlet implementation class AddGoodsServlet
 */
@WebServlet("/AddGoodsServlet")
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AddGoodsService addGoodsService = new AddGoodsService();

	/**
	 * 对上传的数据进行处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileDir =this.getServletConfig().getServletContext().getRealPath("uploadimg/");// 要上传到这里
		System.out.println(fileDir);
		String localFileName = ""; // 客户端文件名字
		String serverFileName = ""; // 服务器文件名字
		String serverFilePath = ""; // 服务器端路径
		GoodsBean goods = new GoodsBean();
		if (ServletFileUpload.isMultipartContent(request)) {// 判断是否是上传文件
			// 创建上传工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(20 * 1024);
			factory.setRepository(factory.getRepository());//设置存放临时文件的目录
			// 创建文件上传处理器
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> formlists = null;
			FileItem formitem;
			try {
				formlists = upload.parseRequest(request); // 解析上传请求
			} catch (FileUploadException e) {
				throw new RuntimeException(e);
			}
			Iterator<FileItem> iterator = formlists.iterator();
			while (iterator.hasNext()) {
				formitem = iterator.next(); // 获取FileItem对象
				if (!formitem.isFormField()) { // 判断是否为文件域
					if (formitem.getName() != null && !formitem.getName().equals("")) { // 判断是否选择了文件
						localFileName = formitem.getName(); // 获取文件名
						int ii = localFileName.lastIndexOf(".");
						String sExt = localFileName.substring(ii, localFileName.length());// 取文件名的后缀
						// 得到不重复的文件名，这一步是为了防止同时上传两个同文件名的图片而做的，避免文件名重复
						Date dt = new Date(System.currentTimeMillis());
						SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
						serverFileName = fmt.format(dt);
						serverFileName = serverFileName + sExt;
						// 如果不存在该目录，则新建一个
						File dir = new File(fileDir);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						serverFilePath = fileDir+ serverFileName;// 需要保存到数据库
						System.out.println(serverFilePath);
						goods.setGoodsimgpath(serverFileName);
						File serverFile = new File(serverFilePath);
						try {
							formitem.write(serverFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						response.setContentType("text/html;charset=UTF-8");
						response.getWriter().write("<script>alert('没有选择文件！');window.history.back();</script>");
						return;
					}
				} else {
					// 获得商品名称
					if ("goodsname".equals(formitem.getFieldName())) {
						String goodsname = formitem.getString("UTF-8").trim();
						if (goodsname.equals("") || goodsname == null) {
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("<script>alert('请输入商品名称');window.history.back();</script>");
							return;
						} else {
							if (goodsname.length() > 10) {
								response.setContentType("text/html;charset=UTF-8");
								response.getWriter()
										.write("<script>alert('请输入商品名称的长度小于10');window.history.back();</script>");
								return;
							}
							goods.setGoodsname(goodsname);
						}
					}
					// 获得商品类目
					if ("optionsRadiosinline".equals(formitem.getFieldName())) {
						String optionsRadiosinline = formitem.getString("UTF-8").trim();
						goods.setGoodscategory(optionsRadiosinline);
					}
					//获得商品质量
					if("goodsweight".equals(formitem.getFieldName())){
						String goodsweightString=formitem.getString("UTF-8").trim();
						BigDecimal goodsweight=new BigDecimal(goodsweightString);
						goods.setGoodsweight(goodsweight);
						if (goodsweightString.equals("") || goodsweightString == null) {
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("<script>alert('请输入商品质量(KG)');window.history.back();</script>");
							return;
						}
					}
					// 获得商品价格
					if ("goodsprice".equals(formitem.getFieldName())) {
						String goodsprice = formitem.getString("UTF-8").trim();
						if (goodsprice.equals("") || goodsprice == null) {
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("<script>alert('请输入商品价格（元）');window.history.back();</script>");
							return;
						} else {
							if (goodsprice.length() > 10) {
								response.setContentType("text/html;charset=UTF-8");
								response.getWriter()
										.write("<script>alert('请确保输入长度小于10');window.history.back();</script>");
								return;
							}
							int start = goodsprice.indexOf(".");
							if (start != -1) {
								// 获取小数位长度
								String scalar = goodsprice.substring(start + 1, goodsprice.length());
								if (scalar.length() > 2) {
									response.setContentType("text/html;charset=UTF-8");
									response.getWriter()
											.write("<script>alert('请输入不超过3位小数位');window.history.back();</script>");
									return;
								}
							}
							BigDecimal bigDecimal = new BigDecimal(goodsprice);
							goods.setGoodsprice(bigDecimal);
						}
					}
					/* 获得商品描述 */
					if ("goodsdescript".equals(formitem.getFieldName())) {
						String gooddescript = formitem.getString("UTF-8").trim();
						if (gooddescript.equals("") || gooddescript == null) {
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("<script>alert('请输入商品描述');window.history.back();</script>");
							return;
						} else {
							if (gooddescript.length() > 50) {
								response.setContentType("text/html;charset=UTF-8");
								response.getWriter()
										.write("<script>alert('请输入长度小于50的商品描述');window.history.back();</script>");
								return;
							}
							goods.setGoodsdescript(gooddescript);
						}
					}
					if ("uno".equals(formitem.getFieldName())) {
						String uno = formitem.getString("utf-8").trim();
						goods.setUno(uno);
					}
				}
			}
		}
		// 到这里，表单验证已经完成，我们需要的是对将数据插入到数据库中
		addGoodsService.addGoods(goods);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("<script>alert('商品添加成功');window.history.back();</script>");
		return;
	}
}
