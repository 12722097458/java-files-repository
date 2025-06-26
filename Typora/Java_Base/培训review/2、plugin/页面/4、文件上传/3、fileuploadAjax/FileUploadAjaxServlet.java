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
// ʹ��ע��@MultipartConfig��һ��Servlet��ʶΪ֧���ļ��ϴ�
@MultipartConfig
public class FileUploadAjaxServlet extends BaseServlet {

	public void doFileUploadOneAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ȡ��ͨ���
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		System.out.println(name);
		System.out.println(password);

		// �洢·��
		// ��ȡ����������ļ�·��
		String path = this.getServletContext().getRealPath("products/1");
		System.out.println("path:"+path);
		File uploadFile = new File(path);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		// ��ȡ�ϴ����ļ�����,��ÿ���ؼ���������һ��part
		// Collection<Part> parts = request.getParts();

		// �������֣���ȡ�ļ����Ǹ�part����
		Part part = request.getPart("file1");

		// ѭ�������ϴ����ļ�
		// ��ȡ����ͷ������ͷ�ĸ�ʽ��form-data; name="file"; filename="snmp4j--api.zip"
		String header = part.getHeader("content-disposition");

		// ��ȡ�ļ���
		String fileName = getFileName(header);

		//������
		String newFileName = UUID.randomUUID().toString();

		int lastIndexOfDot = fileName.lastIndexOf(".");
		if (lastIndexOfDot != -1) {
			// �к�׺,��Ҫ�õ���׺
			String subfix = fileName.substring(lastIndexOfDot);
			newFileName = newFileName + subfix;
		}

		// ���ļ�д��ָ��·��
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

		// �洢·��
		// ��ȡ����������ļ�·��
		String path = this.getServletContext().getRealPath("upload");

		File uploadFile = new File(path);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}

		// ��ȡ�ϴ����ļ�����
		Collection<Part> parts = request.getParts();

		System.out.println(parts.size());

		// һ�����ϴ�����ļ��������������ؼ���������������
		for (Part part : parts) {

			String contentType = part.getContentType();
			System.out.println("contentType=" + contentType);
			
			if(contentType==null) {
				//�����ϴ��ļ����
				continue;
			}

			// ѭ�������ϴ����ļ�
			// ��ȡ����ͷ������ͷ�ĸ�ʽ��form-data; name="file"; filename="snmp4j--api.zip"
			String header = part.getHeader("content-disposition");

			// ��ȡ�ļ���
			String fileName = getFileName(header);

			String newFileName = UUID.randomUUID().toString();

			int lastIndexOfDot = fileName.lastIndexOf(".");
			if (lastIndexOfDot != -1) {
				// �к�׺,��Ҫ�õ���׺
				String subfix = fileName.substring(lastIndexOfDot);
				newFileName = newFileName + subfix;
			}

			// ���ļ�д��ָ��·��
			part.write(uploadFile + File.separator + newFileName);

		}

		JSONUtils.WriteData2Map2JSON(1, response);

	}

	/**
	 * ��������ͷ�������ļ��� ����ͷ�ĸ�ʽ�������google������£�form-data; name="file";
	 * filename="snmp4j--api.zip" IE������£�form-data; name="file";
	 * filename="E:\snmp4j--api.zip"
	 * 
	 * @param header
	 *            ����ͷ
	 * @return �ļ���
	 */
	public String getFileName(String header) {
		System.out.println(header);
		/*
		 * String[] tempArr1 = header.split(";"); ����ִ����֮���ڲ�ͬ��������£�tempArr1���������������������
		 * �������google������£�tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE������£�tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/*
		 * �������google������£�tempArr2={filename,"snmp4j--api.zip"}
		 * IE������£�tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// ��ȡ�ļ��������ݸ����������д��
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

}