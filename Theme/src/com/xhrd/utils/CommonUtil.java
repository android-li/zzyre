package com.xhrd.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class CommonUtil {
	// 设置TokenId
	public static void setTokenId(Context context, String tokenid) {
		if (!TextUtils.isEmpty(tokenid)) {
			SharedPreferences.Editor sp = context.getSharedPreferences(
					"configure", 0).edit();
			sp.putString("tokenid", tokenid);
			sp.commit();
		}
	}
	//获取TokenId
	public static String getTokenId(Context context) {
		SharedPreferences sp = context.getSharedPreferences("configure", 0);
		String tokenid = sp.getString("tokenid", "0");
		return tokenid;
	}

	public static void showInfoDialog(Context context, String message) {
		showInfoDialog(context, message, "提示", "确定", null);
	}

	public static void showInfoDialog(Context context, String message,
			String titleStr, String positiveStr,
			DialogInterface.OnClickListener onClickListener) {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
		localBuilder.setTitle(titleStr);
		localBuilder.setMessage(message);
		if (onClickListener == null)
			onClickListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			};
		localBuilder.setPositiveButton(positiveStr, onClickListener);
		localBuilder.show();
	}
}
