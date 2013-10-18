package com.xhrd.parser;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseParser {

	public Map<String, Object> parseJSON(String paramString) {
		if (paramString == null) {
			return null;
		}
		Map<String, Object> mapData = new HashMap<String, Object>();
		try {
			String rspCode = getResponseCode(paramString);
			mapData.put("rspCode", rspCode);
			if ("00000000".equals(rspCode)) {
				JSONObject json = new JSONObject(paramString);
				JSONObject data = json.getJSONObject("data");
				mapData.put("data", parserData(data));
			} else {
				mapData.put("rspDesc", getResponseDesc(paramString));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapData;
	}

	/*
	 * 继承BaseParser重写方法
	 */
	public abstract Object parserData(JSONObject data) throws JSONException;

	/**
	 * @param res
	 * @throws JSONException
	 *             "rspHeader":{ "reqCode":"INTER00001", "rspCode":"00000000",
	 *             "rspDesc":"成功", "rspTime":"20130101120001",
	 *             "transactionId":"123456789" },
	 */
	public String getResponseCode(String paramString) throws JSONException {
		if (paramString == null) {
			return null;
		} else {
			JSONObject jsonObject = new JSONObject(paramString);
			JSONObject rspHeader = jsonObject.getJSONObject("rspHeader");
			String result = rspHeader.getString("rspCode");

			return result;
		}
	}

	public String getResponseDesc(String paramString) throws JSONException {
		if (paramString == null) {
			return null;
		} else {
			JSONObject jsonObject = new JSONObject(paramString);
			JSONObject rspHeader = jsonObject.getJSONObject("rspHeader");
			String result = rspHeader.getString("rspDesc");

			return result;
		}
	}
}
