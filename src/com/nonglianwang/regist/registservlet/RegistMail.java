package com.nonglianwang.regist.registservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonglianwang.regist.registbean.RegistBean;
import com.nonglianwang.regist.registservice.ActivateException;
import com.nonglianwang.regist.registservice.NotRegistException;
import com.nonglianwang.regist.registservice.RegistService;

@WebServlet("/Mail")
public class RegistMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistService rs = new RegistService();

	/**
     * 用来�?活注册的账户
     *1、先确认session中是否存在user信息
     *		⚪存在：继续�?下执�?
     *		�? 不存在：抛出自定义异常信�?
     *2、获取session中的user信息
     *3、查询数据库，如果该用户状�?�为true,则抛出已经激活异常，否则继续执行
     *4、向用户发�?�邮件信息激�?
     *		□创建超链接（写�?个工具类�?
     *		□将用户的uno和新创建的uuid保存到要发�?�的链接的request域中
     *		□邮件主�?+邮件发�?�人+地址+内容（超链接:�?活码�?
     *5、截取链接中的uno和uuid,与该地址中request域名作比较，如果相同，则�?活成功，否则，激活失�?
     * Service()方法   
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//获取用户uno
		//判断用户是否已经�?�?
		Boolean flag=false;
		String uno=req.getParameter("uno");
		RegistBean user=null;
		try {
			user=rs.getUser(uno);
		} catch (ActivateException e1) {
			String msg=e1.getMessage();
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("regist/activation.jsp").forward(req, resp);
			return;
		} catch (NotRegistException e) {
			String msg=e.getMessage();
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("regist/activation.jsp").forward(req, resp);
			return;
		}
		flag=true;
		
		
		
		if(flag){
		//发邮�?
		//获得邮箱地址
		String mailTo=req.getParameter("activationemail");
		Properties properties=new Properties();
		properties.load(this.getClass().getClassLoader().getResourceAsStream("email_temp.properties"));
		//创建验证
		Authenticator authenticator=new Authenticator (){
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("uname"), 
						properties.getProperty("password"));
			}
		};
		properties.put("mail.smtp.host", properties.get("host"));
		properties.put("mail.smtp.auth", authenticator);
		Session session=Session.getDefaultInstance(properties,authenticator);
		MimeMessage message=new MimeMessage(session);
		String zsString=new String("ũ����".getBytes("ISO-8859-1"),"UTF-8");
		try {
			message.setSubject(properties.getProperty("subject"),"utf-8");
			message.setContent(MessageFormat.format(properties.getProperty("content"), user.getActivationCode()), 
					"text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(properties.getProperty("from"),zsString));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo, false));
			message.setSentDate(new Date());
			message.saveChanges();//保证报头域同会话内容保持�?�?
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		/*
		 * 不能直接用该方法，需要authenticator验证//������Ҳ��֪������ǰ����ʲô�ˣ�����¸�Ӧ���Ǵ���һ��������֤�� by:lirunmin
		 * try {
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}*/
		Transport transport=null;
		try {
			transport = session.getTransport("smtp");
			transport.connect(properties.getProperty("host"),properties.getProperty("uname"),properties.getProperty("password"));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		try {
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			resp.setContentType("text/html;charset=UTF-8");
			resp.setHeader("target", "iframename");
			PrintWriter pw=resp.getWriter();
			pw.write("emmm,����ʧ����");
			throw new RuntimeException(e);
		}
		try {
			transport.close();
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		String msg="���������ѷ������������䣬�������伤�������˻�";
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("regist/msg.jsp").forward(req, resp);
		return;
	}	
	
	
		/**
		 * 发邮�?
		 *//*
		@Test
		public void sendMail(){
			Properties properties=new Properties();
			try {
				properties.load(this.getClass().getClassLoader().getResourceAsStream("email_temp.properties"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			String host=properties.getProperty("host");
			String username =properties.getProperty("username");
			String password=properties.getProperty("password");
			//创建邮件会话
			Session session=Session.getInstance(properties, null);
			邮件内容应该再方法外面设置，因为�?要转发�?�所以这里先注释�?
			//获取邮件内容
			String content=properties.getProperty("content");
			//替换占位符号
			String checkCode=CreateUUID.getUUID32();
			content =MessageFormat.format(content, checkCode);
			try {
				content=new String(content.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException(e1);
			}
			System.out.println(content);
			//创建邮件内容
			MimeMessage message =new MimeMessage(session);
			//设置邮件内容
			try {
				message.setContent("content", "text/html");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}*/

		


	}
}
