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
	 * ���ϴ������ݽ��д���
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileDir =this.getServletConfig().getServletContext().getRealPath("uploadimg/");// Ҫ�ϴ�������
		System.out.println(fileDir);
		String localFileName = ""; // �ͻ����ļ�����
		String serverFileName = ""; // �������ļ�����
		String serverFilePath = ""; // ��������·��
		GoodsBean goods = new GoodsBean();
		if (ServletFileUpload.isMultipartContent(request)) {// �ж��Ƿ����ϴ��ļ�
			// �����ϴ�����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(20 * 1024);
			factory.setRepository(factory.getRepository());//���ô����ʱ�ļ���Ŀ¼
			// �����ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> formlists = null;
			FileItem formitem;
			try {
				formlists = upload.parseRequest(request); // �����ϴ�����
			} catch (FileUploadException e) {
				throw new RuntimeException(e);
			}
			Iterator<FileItem> iterator = formlists.iterator();
			while (iterator.hasNext()) {
				formitem = iterator.next(); // ��ȡFileItem����
				if (!formitem.isFormField()) { // �ж��Ƿ�Ϊ�ļ���
					if (formitem.getName() != null && !formitem.getName().equals("")) { // �ж��Ƿ�ѡ�����ļ�
						localFileName = formitem.getName(); // ��ȡ�ļ���
						int ii = localFileName.lastIndexOf(".");
						String sExt = localFileName.substring(ii, localFileName.length());// ȡ�ļ����ĺ�׺
						// �õ����ظ����ļ�������һ����Ϊ�˷�ֹͬʱ�ϴ�����ͬ�ļ�����ͼƬ�����ģ������ļ����ظ�
						Date dt = new Date(System.currentTimeMillis());
						SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
						serverFileName = fmt.format(dt);
						serverFileName = serverFileName + sExt;
						// ��������ڸ�Ŀ¼�����½�һ��
						File dir = new File(fileDir);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						serverFilePath = fileDir+ serverFileName;// ��Ҫ���浽���ݿ�
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
						response.getWriter().write("<script>alert('û��ѡ���ļ���');window.history.back();</script>");
						return;
					}
				} else {
					// �����Ʒ����
					if ("goodsname".equals(formitem.getFieldName())) {
						String goodsname = formitem.getString("UTF-8").trim();
						if (goodsname.equals("") || goodsname == null) {
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("<script>alert('��������Ʒ����');window.history.back();</script>");
							return;
						} else {
							if (goodsname.length() > 10) {
								response.setContentType("text/html;charset=UTF-8");
								response.getWriter()
										.write("<script>alert('��������Ʒ���Ƶĳ���С��10');window.history.back();</script>");
								return;
							}
							goods.setGoodsname(goodsname);
						}
					}
					// �����Ʒ��Ŀ
					if ("optionsRadiosinline".equals(formitem.getFieldName())) {
						String optionsRadiosinline = formitem.getString("UTF-8").trim();
						goods.setGoodscategory(optionsRadiosinline);
					}
					//�����Ʒ����
					if("goodsweight".equals(formitem.getFieldName())){
						String goodsweightString=formitem.getString("UTF-8").trim();
						BigDecimal goodsweight=new BigDecimal(goodsweightString);
						goods.setGoodsweight(goodsweight);
						if (goodsweightString.equals("") || goodsweightString == null) {
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("<script>alert('��������Ʒ����(KG)');window.history.back();</script>");
							return;
						}
					}
					// �����Ʒ�۸�
					if ("goodsprice".equals(formitem.getFieldName())) {
						String goodsprice = formitem.getString("UTF-8").trim();
						if (goodsprice.equals("") || goodsprice == null) {
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("<script>alert('��������Ʒ�۸�Ԫ��');window.history.back();</script>");
							return;
						} else {
							if (goodsprice.length() > 10) {
								response.setContentType("text/html;charset=UTF-8");
								response.getWriter()
										.write("<script>alert('��ȷ�����볤��С��10');window.history.back();</script>");
								return;
							}
							int start = goodsprice.indexOf(".");
							if (start != -1) {
								// ��ȡС��λ����
								String scalar = goodsprice.substring(start + 1, goodsprice.length());
								if (scalar.length() > 2) {
									response.setContentType("text/html;charset=UTF-8");
									response.getWriter()
											.write("<script>alert('�����벻����3λС��λ');window.history.back();</script>");
									return;
								}
							}
							BigDecimal bigDecimal = new BigDecimal(goodsprice);
							goods.setGoodsprice(bigDecimal);
						}
					}
					/* �����Ʒ���� */
					if ("goodsdescript".equals(formitem.getFieldName())) {
						String gooddescript = formitem.getString("UTF-8").trim();
						if (gooddescript.equals("") || gooddescript == null) {
							response.setContentType("text/html;charset=UTF-8");
							response.getWriter().write("<script>alert('��������Ʒ����');window.history.back();</script>");
							return;
						} else {
							if (gooddescript.length() > 50) {
								response.setContentType("text/html;charset=UTF-8");
								response.getWriter()
										.write("<script>alert('�����볤��С��50����Ʒ����');window.history.back();</script>");
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
		// ���������֤�Ѿ���ɣ�������Ҫ���ǶԽ����ݲ��뵽���ݿ���
		addGoodsService.addGoods(goods);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("<script>alert('��Ʒ��ӳɹ�');window.history.back();</script>");
		return;
	}
}
