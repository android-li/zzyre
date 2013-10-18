package com.xhrd.utils;

import android.util.Log;

public class LOG {
	// log level
	public static final int ERROR = 1;
	public static final int WARNING = 2;
	public static final int INFO = 3;
	public static final int TRACE = 4;
	// log tag
	private static final String LOGTAG = "XMClient";
	// current log level
	private static int m_iLogLevel = 4;

	public static void LOG(int level, String sClassName, String message) {
		if (sClassName == null || message == null) {
			return;
		}
		if (level <= m_iLogLevel) {
			String logMessage = "[" + sClassName + "]" + message;
			if (level == TRACE || level == INFO) {
				Log.i(LOGTAG, logMessage);
			} else if (level == WARNING) {
				Log.w(LOGTAG, logMessage);
			} else if (level == ERROR) {
				Log.e(LOGTAG, logMessage);
			}
		}
	}
}
