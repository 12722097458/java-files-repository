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
					// 对于中文的cookie值需要重新解码
					String decodeUsername = URLDecoder.decode(cookie.getValue(), "UTF-8");
					request.setAttribute("username", decodeUsername);
					break;
				}
			}
		}

		request.getRequestDispatcher("/WEB-INF/jsp/root/login.jsp").forward(request, response);
	}

	public void doLoginAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取当前项目路径名
		String contextPath = request.getContextPath();

		// 用户发送给服务器的所有请求信息，都封装成了request对象
		// 其中用户输入的信息也都在请求中
		// 此方法中的参数名，就是控件的名称(真正的是请求参数名)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String remember = request.getParameter("remember");

		// 把用户输入的验证码和前面服务器生成的验证进行匹配
		// 尝试去匹配对应的session对象（如果原来有，直接拿来使用；如果没有，则创建新的）
		HttpSession session = request.getSession();
		String code_session = (String) session.getAttribute("code_session");

		System.out.println(code_session + "=" + vcode);

		if (code_session == null || "".equals(code_session)) {
			// 没有用到验证码，说明不是正常的登录
			// 存放失败的提示信息
			//request.setAttribute("error", "对不起，请正确完成登录操作！");
			JSONUtils.writeData2JSON(-2, response);
			return;
		}
		
		// 如果验证码匹配，完成后续的登录验证
		session.removeAttribute("code_session");

		if (!code_session.equalsIgnoreCase(vcode)) {
			// 验证码不匹配
			// 存放失败的提示信息
			//request.setAttribute("error", "对不起，验证码有误，请重新输入新的验证码，再次登录！");
			JSONUtils.writeData2JSON(-1, response);
			return;
		}

		// 表现层依赖service业务逻辑层
		UserService userService = new UserServiceImpl();

		User user = userService.login(username, password);
		if (user == null) {
			// 代表登录失败,返回到登录页面，让用户重新完成登录操作

			// 存放失败的提示信息
			//request.setAttribute("error", "对不起，用户名或密码有误，登录失败！");
			JSONUtils.writeData2JSON(0, response);
			return;
		}
		
		if(user.getState().equals(0)) {
			// 未激活，登录失败
			JSONUtils.writeData2JSON(-3, response);
			return;
		}

		// 代表登录成功,返回首页

		// 记住用户的登录信息
		session.setAttribute("user", user);

		if ("on".equals(remember)) {
			// 记住用户名
			// 对于中文的cookie值需要重新编码
			String encodeUsername = URLEncoder.encode(username, "UTF-8");
			Cookie cookie = new Cookie("username", encodeUsername);
			cookie.setPath(contextPath);
			cookie.setMaxAge(3600 * 24 * 7);
			response.addCookie(cookie);
		} else {
			// 不记住用户名
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
		// 获取当前项目路径名
		String contextPath = request.getContextPath();

		HttpSession session = request.getSession();
		// 注销时，从session中移除登录对应的key
		session.removeAttribute("user");
		// session.invalidate();

		// 重定向到首页
		response.sendRedirect(contextPath + "/product.do?op=toIndex");
	}

	public void toRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/root/register.jsp").forward(request, response);
	}
	
	public void doRegisterAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 表现层依赖service业务逻辑层
		UserService userService = new UserServiceImpl();

		User user = new User();
		try {
			// 如果数据中有日期格式，则需要注册日期类型转换器
			DateConverter dateConverter = new DateConverter();
			dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy/MM/dd" });

			// 注册转换器，只有目标对象student中有java.util.Date数据类型的setXxx属性
			// 就使用上面的dateConverter转换器完成从String到Date的换行工作
			ConvertUtils.register(dateConverter, Date.class);

			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		user.setState(0);// 默认未激活
		// 主键
		String uid = UUID.randomUUID().toString().replace("-", "");
		user.setUid(uid);
		// 激活码
		String code = UUID.randomUUID().toString().replace("-", "");
		user.setCode(code);

		// 对密码进行加密
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
			String subject="极客商城，新用户激活通知";
			
			String content="恭喜您注册成功，请点击下面的链接来激活账户<br/>";
			
			String href="http://"+localAddr+":"+localPort+contextPath+"/user.do?op=doActive&amp;code="+code;
			System.out.println(href);
			content+="<a href='"+href+"'>"+href+"</a>";
			
			String serverHost=this.getServletContext().getInitParameter("serverHost");
			
			//如果注册成功了，则需要向用户注册时输入的邮箱，发送一封激活邮件
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
			request.setAttribute("message", "注册用户激活成功！");
		}else {
			request.setAttribute("error", "注册用户激活失败，请重新尝试激活操作！");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/root/activeresult.jsp").forward(request, response);
	}
	
}