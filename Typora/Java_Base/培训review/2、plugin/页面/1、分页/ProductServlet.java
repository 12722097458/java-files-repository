package com.igeek.shop.web.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.igeek.shop.entity.Category;
import com.igeek.shop.entity.Product;
import com.igeek.shop.service.CategoryService;
import com.igeek.shop.service.ProductService;
import com.igeek.shop.service.impl.CategoryServiceImpl;
import com.igeek.shop.service.impl.ProductServiceImpl;
import com.igeek.shop.utils.JSONUtils;
import com.igeek.shop.utils.PageUtils;
import com.igeek.shop.web.BaseServlet;

@WebServlet(name = "AdminProductServlet", urlPatterns = "/admin/product.do")
public class ProductServlet extends BaseServlet {
	
	public void toList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/administrator/product/list.jsp").forward(request, response);
	}

	public void doSearchAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.getParameterMap(),如果用户在界面上没有输入生日的日期，则其key还是存在，只是value为空字符串""
		//但是空字符串无法转换成目标日期对象，所以抛转换异常
		//所以我需要一个新的key-value的map对象，没有birthday的key
		Map<String, String[]> newMap = new HashMap<String, String[]>();
		Iterator<String> iterator = request.getParameterMap().keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			String[] value = request.getParameterValues(key);
			newMap.put(key, value);
		}
		
		String beginDate = request.getParameter("beginDate");
		if(beginDate==null||"".equals(beginDate)) {
			newMap.remove("beginDate");
		}
		String endDate = request.getParameter("endDate");
		if(endDate==null||"".equals(endDate)) {
			newMap.remove("endDate");
		}
		
		// 组装成条件对象
		Product condition = new Product();
		try {
			// 如果数据中有日期格式，则需要注册日期类型转换器
			DateConverter dateConverter = new DateConverter();
			dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy/MM/dd" });

			// 注册转换器，只有目标对象student中有java.util.Date数据类型的setXxx属性
			// 就使用上面的dateConverter转换器完成从String到Date的换行工作
			ConvertUtils.register(dateConverter, Date.class);

			//BeanUtils.populate(user, request.getParameterMap());
			BeanUtils.populate(condition, newMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 获取显示哪页的页码值
		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}

		// 组装成分页对象
		PageUtils<Product> pageUtils = new PageUtils<Product>();
		pageUtils.setPageSize(5);
		pageUtils.setPageNo(pageNo);

		// 调用业务，得到需要的结果数据，并传递给结果页面去获取显示
		ProductService productService = new ProductServiceImpl();
		productService.searchByCondition(condition, pageUtils);

		request.setAttribute("pageUtils", pageUtils);

		JSONUtils.writeJavaBean2JSON(pageUtils, response);
	}
	
	public void doRemoveAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		
		ProductService productService = new ProductServiceImpl();
				
		int result = productService.doRemove(pid);
		JSONUtils.writeData2JSON(result, response);
	}

	public void toProductInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取商品id
		String pid = request.getParameter("pid");

		// 同时产生浏览此商品的历史记录
		Cookie[] cookies = request.getCookies();
		String pids = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("historyPids")) {
					// 6,2,11,4,5,1
					pids = cookie.getValue();
					break;
				}
			}
		}

		if ("".equals(pids)) {
			// 如果是空的，那说明以前没有产生过历史记录，则当前商品就是第一个
			pids = pid;
		} else {
			// 代表以前已经产生了浏览的历史记录
			// [6,2,11,4,5,1]
			String[] splitPids = pids.split(",");
			LinkedList<String> linkedList = new LinkedList<>();
			for (int i = 0; i < splitPids.length; i++) {
				linkedList.addLast(splitPids[i]);
			}

			// 先判断原先的历史记录中是否有此商品，如果没有，先删除，然后追加到头部
			// [6,2,11,4,5,1]
			// 4
			if (linkedList.contains(pid)) {
				linkedList.remove(pid);
			}
			// [6,2,11,5,1]

			linkedList.addFirst(pid);
			// [4,6,2,11,5,1]

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < linkedList.size(); i++) {
				sb.append("," + linkedList.get(i));
				if (i >= 6) {
					// 历史记录中最多保存7个pid
					break;
				}
			}
			// ,4,6,2,11,5,1
			sb.deleteCharAt(0);

			// 4,6,2,11,5,1
			pids = sb.toString();
		}

		System.out.println(pids);

		String contextPath = request.getContextPath();

		// 把浏览记录的ids值放入到cookie中
		Cookie cookie = new Cookie("historyPids", pids);
		cookie.setPath(contextPath);
		cookie.setMaxAge(3600 * 24 * 30);

		response.addCookie(cookie);

		// 调用业务，得到需要的结果数据，并传递给结果页面去获取显示
		ProductService productService = new ProductServiceImpl();

		Product product = productService.getById(pid);
		request.setAttribute("product", product);

		CategoryService categoryService = new CategoryServiceImpl();
		Category category = categoryService.getById(product.getCid());
		request.setAttribute("category", category);

		request.getRequestDispatcher("/WEB-INF/jsp/root/product_info.jsp").forward(request, response);
	}

}