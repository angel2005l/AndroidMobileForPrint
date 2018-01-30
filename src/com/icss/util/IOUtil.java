package com.icss.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IOUtil {

	private static IOUtil ioUtil;

	private IOUtil() {

	}

	// 单例模式 获得IOUtil对象
	public static IOUtil getInstance() {
		// 线程非安全方法 添加同步锁
		if (null == ioUtil) {
			synchronized (IOUtil.class) {
				if (null == ioUtil) {
					ioUtil = new IOUtil();
				}
			}
		}
		return ioUtil;
	}

	// 具体的写入操作 
	public void write(File file, String info) throws FileNotFoundException,
			UnsupportedEncodingException {
		FileOutputStream outputStream = new FileOutputStream(file, true);
		byte[] b = info.getBytes("UTF-8");
		try {
			outputStream.write(b, 0, b.length);
			outputStream.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
