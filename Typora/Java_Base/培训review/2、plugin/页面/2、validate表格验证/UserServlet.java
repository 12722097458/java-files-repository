package com.igeek.shop.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.igeek.shop.entity.Category;
import com.igeek.shop.entity.User;
import com.igeek.shop.service.CategoryService;
import com.igeek.shop.service.UserService;
import com.igeek.shop.service.impl.CategoryServiceImpl;
import com.igeek.shop.service.impl.UserServiceImpl;
import com.igeek.shop.utils.JSONUtils;
import com.igeek.shop.utils.MD5Utils;
import com.igeek.shop.utils.MailUtils;

@WebServlet(name = "UserServlet", urlPatterns = "/user.do")
public class UserServlet extends BaseServlet {
	
	public void doCheckUsernameAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		System.out.println(request.getMethod()+"="+username);
		
		UserService userService = new UserServiceImpl();
		boolean result = userService.doCheckUsername(username);
		
		//{"result":true or false}
		JSONUtils.writeData2JSON(result, response);
	}

	public void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if ("username".equals(cookie.getName())) {
					// �������ĵ�cookieֵ��Ҫ���½���
					String decodeUsername = URLDecoder.decode(cookie.getValue(), "UTF-8");
					request.setAttribute("username", decodeUsername);
					break;
				}
			}
		}

		request.getRequestDispatcher("/WEB-INF/jsp/root/login.jsp").forward(request, response);
	}

	public void doLoginAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ��ǰ��Ŀ·����
		String contextPath = request.getContextPath();

		// �û����͸�������������������Ϣ������װ����request����
		// �����û��������ϢҲ����������
		// �˷����еĲ����������ǿؼ�������(�����������������)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String remember = request.getParameter("remember");

		// ���û��������֤���ǰ����������ɵ���֤����ƥ��
		// ����ȥƥ���Ӧ��session�������ԭ���У�ֱ������ʹ�ã����û�У��򴴽��µģ�
		HttpSession session = request.getSession();
		String code_session = (String) session.getAttribute("code_session");

		System.out.println(code_session + "=" + vcode);

		if (code_session == null || "".equals(code_session)) {
			// û���õ���֤�룬˵�����������ĵ�¼
			// ���ʧ�ܵ���ʾ��Ϣ
			//request.setAttribute("error", "�Բ�������ȷ��ɵ�¼������");
			JSONUtils.writeData2JSON(-2, response);
			return;
		}
		
		// �����֤��ƥ�䣬��ɺ����ĵ�¼��֤
		session.removeAttribute("code_session");

		if (!code_session.equalsIgnoreCase(vcode)) {
			// ��֤�벻ƥ��
			// ���ʧ�ܵ���ʾ��Ϣ
			//request.setAttribute("error", "�Բ�����֤�����������������µ���֤�룬�ٴε�¼��");
			JSONUtils.writeData2JSON(-1, response);
			return;
		}

		// ���ֲ�����serviceҵ���߼���
		UserService userService = new UserServiceImpl();

		User user = userService.login(username, password);
		if (user == null) {
			// �����¼ʧ��,���ص���¼ҳ�棬���û�������ɵ�¼����

			// ���ʧ�ܵ���ʾ��Ϣ
			//request.setAttribute("error", "�Բ����û������������󣬵�¼ʧ�ܣ�");
			JSONUtils.writeData2JSON(0, response);
			return;
		}
		
		if(user.getState().equals(0)) {
			// δ�����¼ʧ��
			JSONUtils.writeData2JSON(-3, response);
			return;
		}

		// �����¼�ɹ�,������ҳ

		// ��ס�û��ĵ�¼��Ϣ
		session.setAttribute("user", user);

		if ("on".equals(remember)) {
			// ��ס�û���
			// �������ĵ�cookieֵ��Ҫ���±���
			String encodeUsername = URLEncoder.encode(username, "UTF-8");
			Cookie cookie = new Cookie("username", encodeUsername);
			cookie.setPath(contextPath);
			cookie.setMaxAge(3600 * 24 * 7);
			response.addCookie(cookie);
		} else {
			// ����ס�û���
			Cookie cookie = new Cookie("username", "");
			cookie.setPath(contextPath);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		JSONUtils.writeData2JSON(1, response);
	}

	public void toLoginSuccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/root/loginsuccess.jsp").forward(request, response);
	}

	public void doLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��ǰ��Ŀ·����
		String contextPath = request.getContextPath();

		HttpSession session = request.getSession();
		// ע��ʱ����session���Ƴ���¼��Ӧ��key
		session.removeAttribute("user");
		// session.invalidate();

		// �ض�����ҳ
		response.sendRedirect(contextPath + "/product.do?op=toIndex");
	}

	public void toRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/root/register.jsp").forward(request, response);
	}
	
	public void doRegisterAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���ֲ�����serviceҵ���߼���
		UserService userService = new UserServiceImpl();

		User user = new User();
		try {
			// ��������������ڸ�ʽ������Ҫע����������ת����
			DateConverter dateConverter = new DateConverter();
			dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy/MM/dd" });

			// ע��ת������ֻ��Ŀ�����student����java.util.Date�������͵�setXxx����
			// ��ʹ�������dateConverterת������ɴ�String��Date�Ļ��й���
			ConvertUtils.register(dateConverter, Date.class);

			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		user.setState(0);// Ĭ��δ����
		// ����
		String uid = UUID.randomUUID().toString().replace("-", "");
		user.setUid(uid);
		// ������
		String code = UUID.randomUUID().toString().replace("-", "");
		user.setCode(code);

		// ��������м���
		String md5 = MD5Utils.md5(user.getPassword());
		user.setPassword(md5);

		System.out.println(user);

		int register = userService.register(user);
		if(register==1) {
			
			String contextPath = request.getContextPath();
			String localAddr = request.getLocalAddr();
			int localPort = request.getLocalPort();
			
			String from=this.getServletContext().getInitParameter("adminEmail");
			String to=user.getEmail();
			String subject="�����̳ǣ����û�����֪ͨ";
			
			String content="��ϲ��ע��ɹ�����������������������˻�<br/>";
			
			String href="http://"+localAddr+":"+localPort+contextPath+"/user.do?op=doActive&amp;code="+code;
			System.out.println(href);
			content+="<a href='"+href+"'>"+href+"</a>";
			
			String serverHost=this.getServletContext().getInitParameter("serverHost");
			
			//���ע��ɹ��ˣ�����Ҫ���û�ע��ʱ��������䣬����һ�⼤���ʼ�
			MailUtils.send(from, to, subject, content, serverHost);
		}
		
		JSONUtils.writeData2JSON(register, response);

	}
	
	public void doActive(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		
		UserService userService = new UserServiceImpl();
		int result = userService.doActive(code);
		
		String contextPath = request.getContextPath();
		
		response.sendRedirect(contextPath+"/user.do?op=toActiveResult&result="+result);
	}
	
	public void toActiveResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = request.getParameter("result");
		if("1".equals(result)) {
			request.setAttribute("message", "ע���û�����ɹ���");
		}else {
			request.setAttribute("error", "ע���û�����ʧ�ܣ������³��Լ��������");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/root/activeresult.jsp").forward(request, response);
	}
	
}