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

		// Ϊ��������ԵĻ�ȡ����������ͨ�ؼ�ֵ
		Map<String, String> params = new HashMap<String, String>();

		// 1.���������ļ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ��һ����������������С ���ֽ�Ϊ��λ �ڶ����������������ĵ�ַ
		String temp_path = this.getServletContext().getRealPath("temp");

		// DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new
		// File(temp_path));
		File tempFile = new File(temp_path);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}

		// ��ȡ����������ļ�·��
		String path = this.getServletContext().getRealPath("upload");

		File uploadFile = new File(path);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		// �ֱ����û�������С
		factory.setSizeThreshold(1024 * 1024);// 1M
		// ���û���ĵ�ַ
		factory.setRepository(tempFile);

		// 2.����������
		ServletFileUpload upload = new ServletFileUpload(factory);
		// ���ϴ��ļ������б���
		upload.setHeaderEncoding("UTF-8");

		// �жϱ��������ǲ����ļ��ϴ� true---���ļ��ϴ� false---��ͨ��
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		if (multipartContent) {
			// �ļ��ϴ�
			try {
				List<FileItem> parseRequest = upload.parseRequest(request);
				if (parseRequest != null) {
					// ����
					for (FileItem item : parseRequest) {
						// �ж�item�����ǲ�����ͨ�����󣬻����ļ��ϴ��Ķ���
						if (item.isFormField()) {
							// ��ȡ����name����ֵ
							String fieldName = item.getFieldName();
							// ��ȡ���������ֵ
							String fieldValue = item.getString("UTF-8");// �Ա��������ֵ���б���

							System.out.println(fieldName + "=" + fieldValue);

							params.put(fieldName, fieldValue);
						} else {
							// item��һ���ļ��ϴ��Ķ���
							// �Ȼ�ȡ�ļ���
							String fileName = item.getName();
							// ��ȡ�ļ�����
							// InputStream in = item.getInputStream();
							/*
							 * OutputStream out = new FileOutputStream(new File(path+"/" + fileName)); //����
							 * IOUtils.copy(in, out);
							 */

							String newFileName = UUID.randomUUID().toString();

							int lastIndexOfDot = fileName.lastIndexOf(".");
							if (lastIndexOfDot != -1) {
								// �к�׺,��Ҫ�õ���׺
								String subfix = fileName.substring(lastIndexOfDot);
								newFileName = newFileName + subfix;
							}

							// ��item�е��ļ���д�뵽Ŀ��λ��
							item.write(new File(uploadFile, newFileName));
							// �ر���
							// in.close();
							/// out.close();
							// Ӧ�ý�����������ʱ�ļ����
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
			// ��ǰ�Ĵ�����ķ�ʽ
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