package com.icss.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.icss.config.SharedPreferenceFileConfiguration;

@SuppressLint("SimpleDateFormat")
public class FileUtil {

	// log日志文名称及相关相对路径
	private static String logFileName = "icssWmsMobLog.txt";
	private static String logFilePath = "log";
	private Context mContext;
	private SPUtil logFileProfile;
	private DateFormat dateFormat;

	// 获得上下文
	public FileUtil(Context context) {
		this.mContext = context;
		this.logFileProfile = new SPUtil(context,
				SharedPreferenceFileConfiguration.LOGFILENAME);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	// 1.获得存储的绝对路径 判断设备是否有sdCard
	public String getPath() {
		String path = null;
		if (null != mContext) {
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				path = mContext.getExternalCacheDir().getAbsolutePath();
			} else {
				path = mContext.getCacheDir().getAbsolutePath();
			}
		} else {
			path = Environment.getRootDirectory().getAbsolutePath();
		}
		return path;
	}

	// 2.获得日志的绝对路径
	public String getLogPath() {
		return getPath() + File.separator + logFilePath + File.separator;
	}

	// 3.获得日志的url 判断存储路径是否存在
	public String getLogUrl() throws ParseException {
		String logPath = getLogPath();
		File file = new File(logPath);
		if (!file.exists()) {
			file.mkdir();
		}
		String dateNow = dateFormat
				.format(new Date(System.currentTimeMillis()));// 当前时间
		String dataOld = logFileProfile.getString("logDate", "");// 之前时间
		if (TextUtils.isEmpty(dataOld)
				|| (dateFormat.parse(dataOld).getTime() - dateFormat.parse(
						dateNow).getTime())
						/ (1000*3600*24) >= 7) {
			logFileName = dateNow + "_" + logFileName;
			logFileProfile.putString("logDate", dateNow);
		}
		return logPath + File.separator + logFileName;
	}

	// 4.获得logfile对象
	public File getLogFile() throws ParseException {
		String logFileUrl = getLogUrl();
		File file = new File(logFileUrl);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return file;
	}
}
