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
		//request.getParameterMap(),����û��ڽ�����û���������յ����ڣ�����key���Ǵ��ڣ�ֻ��valueΪ���ַ���""
		//���ǿ��ַ����޷�ת����Ŀ�����ڶ���������ת���쳣
		//��������Ҫһ���µ�key-value��map����û��birthday��key
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
		
		// ��װ����������
		Product condition = new Product();
		try {
			// ��������������ڸ�ʽ������Ҫע����������ת����
			DateConverter dateConverter = new DateConverter();
			dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy/MM/dd" });

			// ע��ת������ֻ��Ŀ�����student����java.util.Date�������͵�setXxx����
			// ��ʹ�������dateConverterת������ɴ�String��Date�Ļ��й���
			ConvertUtils.register(dateConverter, Date.class);

			//BeanUtils.populate(user, request.getParameterMap());
			BeanUtils.populate(condition, newMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// ��ȡ��ʾ��ҳ��ҳ��ֵ
		String strPageNo = request.getParameter("pageNo");
		int pageNo = 1;
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}

		// ��װ�ɷ�ҳ����
		PageUtils<Product> pageUtils = new PageUtils<Product>();
		pageUtils.setPageSize(5);
		pageUtils.setPageNo(pageNo);

		// ����ҵ�񣬵õ���Ҫ�Ľ�����ݣ������ݸ����ҳ��ȥ��ȡ��ʾ
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

		// ��ȡ��Ʒid
		String pid = request.getParameter("pid");

		// ͬʱ�����������Ʒ����ʷ��¼
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
			// ����ǿյģ���˵����ǰû�в�������ʷ��¼����ǰ��Ʒ���ǵ�һ��
			pids = pid;
		} else {
			// ������ǰ�Ѿ��������������ʷ��¼
			// [6,2,11,4,5,1]
			String[] splitPids = pids.split(",");
			LinkedList<String> linkedList = new LinkedList<>();
			for (int i = 0; i < splitPids.length; i++) {
				linkedList.addLast(splitPids[i]);
			}

			// ���ж�ԭ�ȵ���ʷ��¼���Ƿ��д���Ʒ�����û�У���ɾ����Ȼ��׷�ӵ�ͷ��
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
					// ��ʷ��¼����ౣ��7��pid
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

		// �������¼��idsֵ���뵽cookie��
		Cookie cookie = new Cookie("historyPids", pids);
		cookie.setPath(contextPath);
		cookie.setMaxAge(3600 * 24 * 30);

		response.addCookie(cookie);

		// ����ҵ�񣬵õ���Ҫ�Ľ�����ݣ������ݸ����ҳ��ȥ��ȡ��ʾ
		ProductService productService = new ProductServiceImpl();

		Product product = productService.getById(pid);
		request.setAttribute("product", product);

		CategoryService categoryService = new CategoryServiceImpl();
		Category category = categoryService.getById(product.getCid());
		request.setAttribute("category", category);

		request.getRequestDispatcher("/WEB-INF/jsp/root/product_info.jsp").forward(request, response);
	}

}