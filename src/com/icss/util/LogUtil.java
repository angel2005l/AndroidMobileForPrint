package com.icss.util;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.Context;

public class LogUtil {

	private static FileUtil fileUtil;
	private static DateFormat dateFormat;
	private static String V = "VERBOSE：";
	private static String D = "DEBUG：";
	private static String I = "INFO：";
	private static String E = "ERROR：";
	private static String ENTER = "\n";
	private static String SPACE = "\t";

	// 注册
	@SuppressLint("SimpleDateFormat")
	public static void regist(Context context) {
		fileUtil = new FileUtil(context);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	// 注销
	public static void unregist() {
		fileUtil = null;
		dateFormat = null;
	}

	// 获得时间格式
	private static String getCurTimeStr() {
		return dateFormat.format(System.currentTimeMillis()) + SPACE;
	}

	/**
	 * 日志记录方法 开启子线程进行日志的记录 并保证 线程添加同步锁
	 */
	private static synchronized void startWriteThread(final String systemOut) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					IOUtil.getInstance()
							.write(fileUtil.getLogFile(), systemOut);
				} catch (FileNotFoundException | UnsupportedEncodingException | ParseException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 对应安卓日志的四中类型
	// V
	public static void v(String out) {
		synchronized (fileUtil) {
			String systemOut = getCurTimeStr() + V + out + ENTER;
			startWriteThread(systemOut);
		}

	}

	// D
	public static void d(String out) {
		synchronized (fileUtil) {
			String systemOut = getCurTimeStr() + D + out + ENTER;
			startWriteThread(systemOut);
		}
	}

	// I
	public static void i(String out) {
		synchronized (fileUtil) {
			String systemOut = getCurTimeStr() + I + out + ENTER;
			startWriteThread(systemOut);
		}
	}

	// E
	public static void e(String out) {
		synchronized (fileUtil) {
			String systemOut = getCurTimeStr() + E + out + ENTER;
			startWriteThread(systemOut);
		}
	}
}
