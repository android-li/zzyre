package com.xhrd.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.xhrd.constant.Constant;
import com.xhrd.vo.RequestVo;

public class NetUtil {
	private static final String TOKEN_INVALIAD = "10100003";

	public static Object post(RequestVo vo) {
		HttpClient httpclient = new HttpClient();
		httpclient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

		LOG.LOG(LOG.INFO, NetUtil.class.getSimpleName(), "请求地址  "
				+ Constant.requsetUrl4Json);
		PostMethod post = new PostMethod(Constant.requsetUrl4Json);
		String requestJson = vo.requestSoap;
		post.setRequestBody(new NameValuePair[] { new NameValuePair("json",
				requestJson) });
		LOG.LOG(LOG.INFO, NetUtil.class.getSimpleName(), " 请求报文" + requestJson);
		int response = -1;
		Object obj = null;
		String rspJson = null;
		try {
			response = httpclient.executeMethod(post);
			Log.i(NetUtil.class.getSimpleName(), "Response status code: "
					+ response);

			rspJson = post.getResponseBodyAsString();
			LOG.LOG(LOG.INFO, NetUtil.class.getSimpleName(), "返回的json"
					+ rspJson);
			String code = vo.jsonParser.getResponseCode(rspJson);
			/*
			 * if(TOKEN_INVALIAD.equals(code)){ ConfigInfo.setTokenId(""); }
			 */
			obj = vo.jsonParser.parseJSON(rspJson);
			return obj;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * 生成请求报文
	 */
	public static String genRequestSoap(Context context, String reqCode,
			Map<String, Object> requestParam) {
		Gson gson = new Gson();
		Map<String, Object> requestSoap = new HashMap<String, Object>();

		Map<String, String> requestHeader = new HashMap<String, String>();
		requestHeader.put("reqCode", reqCode);
		requestHeader.put("reqTime", System.currentTimeMillis() + "");
		requestHeader.put("tokenId", CommonUtil.getTokenId(context));
		requestHeader.put("transactionId", UUID.randomUUID().toString());
		if (requestParam != null) {
			requestSoap.put("data", requestParam);
		}
		requestSoap.put("reqHeader", requestHeader);

		return gson.toJson(requestSoap);
	}

	/**
	 * @param context
	 * @return
	 */
	public static boolean hasNetwork(Context context) {
		ConnectivityManager con = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo workinfo = con.getActiveNetworkInfo();
		if (workinfo == null || !workinfo.isAvailable()) {
			return false;
		}
		return true;
	}

}
