package com.igeek.shop.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@WebServlet(name = "FileUploadServlet", urlPatterns = "/fileupload.do")
public class FileUploadServlet extends BaseServlet {

	public void doFileUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		System.out.println("name=" + name);
		System.out.println("password=" + password);

		System.out.println("------------------old-------------------");

		// 为了有针对性的获取表单中其他普通控件值
		Map<String, String> params = new HashMap<String, String>();

		// 1.创建磁盘文件项工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 第一个参数：缓存区大小 以字节为单位 第二个参数：缓存区的地址
		String temp_path = this.getServletContext().getRealPath("temp");

		// DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new
		// File(temp_path));
		File tempFile = new File(temp_path);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}

		// 获取服务器存放文件路径
		String path = this.getServletContext().getRealPath("upload");

		File uploadFile = new File(path);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		// 分别设置缓存区大小
		factory.setSizeThreshold(1024 * 1024);// 1M
		// 设置缓存的地址
		factory.setRepository(tempFile);

		// 2.创建核心类
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 将上传文件名进行编码
		upload.setHeaderEncoding("UTF-8");

		// 判断表单的内容是不是文件上传 true---是文件上传 false---普通表单
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		if (multipartContent) {
			// 文件上传
			try {
				List<FileItem> parseRequest = upload.parseRequest(request);
				if (parseRequest != null) {
					// 遍历
					for (FileItem item : parseRequest) {
						// 判断item对象是不是普通表单对象，还是文件上传的对象
						if (item.isFormField()) {
							// 获取表单的name属性值
							String fieldName = item.getFieldName();
							// 获取表单中输入的值
							String fieldValue = item.getString("UTF-8");// 对表单中输入的值进行编码

							System.out.println(fieldName + "=" + fieldValue);

							params.put(fieldName, fieldValue);
						} else {
							// item是一个文件上传的对象
							// 先获取文件名
							String fileName = item.getName();
							// 获取文件内容
							// InputStream in = item.getInputStream();
							/*
							 * OutputStream out = new FileOutputStream(new File(path+"/" + fileName)); //拷贝
							 * IOUtils.copy(in, out);
							 */

							String newFileName = UUID.randomUUID().toString();

							int lastIndexOfDot = fileName.lastIndexOf(".");
							if (lastIndexOfDot != -1) {
								// 有后缀,则要得到后缀
								String subfix = fileName.substring(lastIndexOfDot);
								newFileName = newFileName + subfix;
							}

							// 把item中的文件流写入到目标位置
							item.write(new File(uploadFile, newFileName));
							// 关闭流
							// in.close();
							/// out.close();
							// 应该将缓存区的临时文件清空
							item.delete();

							params.put(item.getFieldName(), "true");
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			// 以前的处理表单的方式
			request.getParameter("name");
		}

		name = params.get("name");
		password = params.get("password");

		String file1 = params.get("file1");
		String file2 = params.get("file2");

		System.out.println(name);
		System.out.println(password);
		System.out.println(file1);
		System.out.println(file2);

	}

}