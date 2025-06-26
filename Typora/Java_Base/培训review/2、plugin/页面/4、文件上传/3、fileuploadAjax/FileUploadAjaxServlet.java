package com.igeek.shop.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONArray;
import com.igeek.shop.entity.Area;
import com.igeek.shop.entity.City;
import com.igeek.shop.entity.Province;
import com.igeek.shop.service.AreaService;
import com.igeek.shop.service.impl.AreaServiceImpl;
import com.igeek.shop.utils.JSONUtils;

@WebServlet(name = "FileUploadAjaxServlet", urlPatterns = "/fileuploadajax.do")
// 使用注解@MultipartConfig将一个Servlet标识为支持文件上传
@MultipartConfig
public class FileUploadAjaxServlet extends BaseServlet {

	public void doFileUploadOneAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取普通组件
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		System.out.println(name);
		System.out.println(password);

		// 存储路径
		// 获取服务器存放文件路径
		String path = this.getServletContext().getRealPath("products/1");
		System.out.println("path:"+path);
		File uploadFile = new File(path);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		// 获取上传的文件集合,把每个控件都看成是一个part
		// Collection<Part> parts = request.getParts();

		// 根据名字，获取文件的那个part对象
		Part part = request.getPart("file1");

		// 循环处理上传的文件
		// 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
		String header = part.getHeader("content-disposition");

		// 获取文件名
		String fileName = getFileName(header);

		//重命名
		String newFileName = UUID.randomUUID().toString();

		int lastIndexOfDot = fileName.lastIndexOf(".");
		if (lastIndexOfDot != -1) {
			// 有后缀,则要得到后缀
			String subfix = fileName.substring(lastIndexOfDot);
			newFileName = newFileName + subfix;
		}

		// 把文件写到指定路径
		part.write(uploadFile + File.separator + newFileName);

		JSONUtils.WriteData2Map2JSON(1, response);
	}

	public void doFileUploadManyAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		System.out.println(name);
		System.out.println(password);

		// 存储路径
		// 获取服务器存放文件路径
		String path = this.getServletContext().getRealPath("upload");

		File uploadFile = new File(path);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		// 获取上传的文件集合
		Collection<Part> parts = request.getParts();

		System.out.println(parts.size());

		// 一次性上传多个文件，并且数量及控件名不清楚的情况下
		for (Part part : parts) {

			String contentType = part.getContentType();
			System.out.println("contentType=" + contentType);
			
			if(contentType==null) {
				//不是上传文件组件
				continue;
			}

			// 循环处理上传的文件
			// 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
			String header = part.getHeader("content-disposition");

			// 获取文件名
			String fileName = getFileName(header);

			String newFileName = UUID.randomUUID().toString();

			int lastIndexOfDot = fileName.lastIndexOf(".");
			if (lastIndexOfDot != -1) {
				// 有后缀,则要得到后缀
				String subfix = fileName.substring(lastIndexOfDot);
				newFileName = newFileName + subfix;
			}

			// 把文件写到指定路径
			part.write(uploadFile + File.separator + newFileName);

		}

		JSONUtils.WriteData2Map2JSON(1, response);

	}

	/**
	 * 根据请求头解析出文件名 请求头的格式：火狐和google浏览器下：form-data; name="file";
	 * filename="snmp4j--api.zip" IE浏览器下：form-data; name="file";
	 * filename="E:\snmp4j--api.zip"
	 * 
	 * @param header
	 *            请求头
	 * @return 文件名
	 */
	public String getFileName(String header) {
		System.out.println(header);
		/*
		 * String[] tempArr1 = header.split(";"); 代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
		 * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/*
		 * 火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
		 * IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// 获取文件名，兼容各种浏览器的写法
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

}