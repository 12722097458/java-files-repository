package com.igeek.shop.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {

    public static void writeJSON(String jsonString,HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print(jsonString);

        out.flush();
        out.close();
    }

	public static void writeList2JSON(List list,HttpServletResponse response) throws IOException {
		JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
		String jsonString = JSONArray.toJSONString(list, SerializerFeature.WriteDateUseDateFormat);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonString);

		out.flush();
		out.close();
	}

	public static void writeArray2JSON(Object[] array,HttpServletResponse response) throws IOException {
		JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
		String jsonString = JSONArray.toJSONString(array, SerializerFeature.WriteDateUseDateFormat);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonString);

		out.flush();
		out.close();
	}

	public static void writeJavaBean2JSON(Object javaBean,HttpServletResponse response) throws IOException {
		JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
		String jsonString = JSONObject.toJSONString(javaBean, SerializerFeature.WriteDateUseDateFormat);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonString);

		out.flush();
		out.close();
	}

	public static void writeData2Map2JSON(Object data,HttpServletResponse response) throws IOException {

		Map<String,Object> map = new HashMap<>();
		map.put("result", data);

		JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
		String jsonString = JSONObject.toJSONString(map, SerializerFeature.WriteDateUseDateFormat);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonString);

		out.flush();
		out.close();
	}
}
