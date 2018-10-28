package com.wm.edu.utils.http;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;

public class HttpUtils {
	private static Logger logger = Logger.getLogger(HttpUtils.class);
	public static final int timeout = 10;

	public static String post(String url) {
		return post(url, "");
	}

	public static String post(String url, String data) {
		return httpPost(url, data);
	}

	public static String post(String url, InputStream instream) {
		try {
			HttpEntity entity = Request
					.Post(url)
					.bodyStream(instream,
							ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}

	public static String get(String url) {
		return httpGet(url);
	}

	private static String httpPost(String url, String data) {
		try {
			HttpEntity entity = Request
					.Post(url)
					.bodyString(data,
							ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}

	public static String postFile(String url, File file) {
		return postFile(url, null, file);
	}

	public static String postFile(String url, String name, File file) {
		try {
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addBinaryBody(name, file).build();
			Request request = Request.Post(url);
			request.body(reqEntity);
			HttpEntity resEntity = request.execute().returnResponse()
					.getEntity();
			return resEntity != null ? EntityUtils.toString(resEntity) : null;
		} catch (Exception e) {
			logger.error("postFile请求异常，" + e.getMessage() + "\n post url:"
					+ url);
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] getFile(String url) {
		try {
			Request request = Request.Get(url);
			HttpEntity resEntity = request.execute().returnResponse()
					.getEntity();
			return EntityUtils.toByteArray(resEntity);
		} catch (Exception e) {
			logger.error("postFile请求异常，" + e.getMessage() + "\n post url:"
					+ url);
			e.printStackTrace();
		}
		return null;
	}

	private static String httpGet(String url) {
		try {
			HttpEntity entity = Request.Get(url).execute().returnResponse()
					.getEntity();
			return entity != null ? EntityUtils.toString(entity,"utf-8") : null;
		} catch (Exception e) {
			logger.error("get请求异常，" + e.getMessage() + "\n get url:" + url);
			e.printStackTrace();
		}
		return null;
	}
}
