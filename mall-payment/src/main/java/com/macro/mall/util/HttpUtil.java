package com.macro.mall.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;

public class HttpUtil {

	/**
	 * 携带证书的请求
	 */
//	private final static String PFX_PATH = "/Users/Mac/Desktop/1601714492_20200808_cert/apiclient_cert.p12"; //客户端证书路径
//	private final static String PFX_PATH = "/usr/local/ca-certificates/apiclient_cert.p12"; //线上测试环境证书路径
//	private final static String PFX_PWD = "1601714492"; //线上测试环境客户端证书密码

	// private final static String PFX_PATH = "/Users/hongjian/Desktop/HaPond/code/o_project/ocean-mall/cert/apiclient_cert.p12"; //线上部署环境证书路径
	private static final String PFX_PATH = "/home/apiclient_cert.p12";//线上部署环境证书路径
	private final static String PFX_PWD = "1660798095"; //线上部署环境客户端证书密码

	public static String sslSendPost(String url, String param) throws Exception
	{
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		InputStream instream = new FileInputStream(new File(PFX_PATH));
		try
		{
			keyStore.load(instream, PFX_PWD.toCharArray());
		}
		finally
		{
			instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, PFX_PWD.toCharArray()).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]
				{ "TLSv1" } // supportedProtocols ,这里可以按需要设置
				, null // supportedCipherSuites
				, new HostnameVerifier() {

			@Override
			public boolean verify(String hostname, SSLSession session)
			{
				//TODO 这里可以自己做host效验
				return true;
			}
		});
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try
		{
			HttpPost httpPost = new HttpPost(url);
//			HttpGet httpget = new HttpGet(url);
//			for (Map.Entry<String, String> entry : heads.entrySet())
//			{
//				String mapKey = entry.getKey();
//				String mapValue = entry.getValue();
//				System.out.println(mapKey + ":" + mapValue);
//				httpget.addHeader(mapKey, mapValue);
//			}
			//            httpost.addHeader("Connection", "keep-alive");// 设置一些heander等
			//CloseableHttpResponse response = httpclient.execute(httpget);

			//设置请求头等
			// 设置通用的请求属性
			httpPost.addHeader("Content-tepy", "text/xml");
			httpPost.addHeader("accept", "*/*");
			httpPost.addHeader("connection", "Keep-Alive");
			httpPost.addHeader("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			HttpEntity stringEntity = new StringEntity(param);
			httpPost.setEntity(stringEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try
			{
				HttpEntity entity = response.getEntity();
				String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");//返回结果
				EntityUtils.consume(entity);
				return jsonStr;
			}
			finally
			{
				response.close();
			}
		}
		finally
		{
			httpclient.close();
		}
	}

	/**
	 * POST请求
	 *
	 * @param url
	 * @param outStr
	 * @return
	 */
	public static String doPostStr(String url, String outStr) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(outStr);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url 		发送请求的 URL
	 * @param param 	请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		Writer out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new BufferedWriter(new OutputStreamWriter(
					conn.getOutputStream(),"UTF-8"));
			// 发送请求参数
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * GET请求
	 */
	public static String doGetStr(String url) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 获取URLConnection对象对应的输出流
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("发送 Get 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
