package com.icss.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import HPRTAndroidSDKA300.HPRTPrinterHelper;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.icss.entity.SysPrintTempletDetail;

public class PrintUtil {
	private static final String TAG = "PrintService";
	private static final String IMGPATH = "/assets/img/";
	private static final int STATICBIT = 8;
	private String offset; // 所有字段水平偏移字段 默认0
	private String horizontal;// 水平方向的dpi 默认200
	private String vertical; // 垂直方向的dpi 默认200
	private String height; // 整个标签的高度 默认180
	private String weight; // 整个标签的宽度 默认75
	private String qty;// 打印张数 默认1
	private String printSpeed;
	private List<SysPrintTempletDetail> sptds;
	private Map<String, Object> orderInfo;

	// 打印的所有方法 全在这里 包括 文字 图片 条码
	public PrintUtil(String height, String weight, String qty,String printSpeed,
			Map<String, Object> orderInfo, List<SysPrintTempletDetail> sptds) {
		this.offset = "0";
		this.horizontal = "200";
		this.vertical = "200";
		this.height = new BigDecimal(height)
				.multiply(new BigDecimal(STATICBIT)).toString();
		this.weight = new BigDecimal(weight)
				.multiply(new BigDecimal(STATICBIT)).toString();
		this.qty = qty;
		this.printSpeed = printSpeed;
		this.orderInfo = orderInfo;
		this.sptds = sptds;
	}

	/**
	 * 
	 * 初始化打印机配置
	 * 
	 * @throws Exception
	 */
	private void initPrinter() throws Exception {
		HPRTPrinterHelper.printAreaSize(offset, horizontal, vertical, height,
				qty);
		HPRTPrinterHelper.PageWidth(weight);
		HPRTPrinterHelper.papertype_CPCL(1);
		HPRTPrinterHelper.Speed(printSpeed);
	}

	public boolean print() {
		try {
			initPrinter();
			// 线LN 条形码BC 图片IG 动态文本DT 静态文本ST
			for (SysPrintTempletDetail sptd : sptds) {
				// Log.v("执行", "zhek" + sptd.getId());
				String[] coord = getArrayForCoord(sptd);
				String fieldType = sptd.getFieldType();
				switch (fieldType) {
				case "LN":
					printLine(sptd, coord);
					break;
				case "BC":
					printBarcode(sptd, coord);
					break;
				case "IG":
					printImage(sptd, coord);
					break;
				case "DT":
					printDynamicText(sptd, coord);
					break;
				case "ST":
					printStaticText(sptd, coord);
					break;
				default:
					break;
				}
			}
			// Log.v(TAG, "执行打印");
			HPRTPrinterHelper.Form();
			return HPRTPrinterHelper.Print() > 0 ? true : false;
		} catch (Exception e) {
			LogUtil.e("【" + TAG + "】" + "打印执行异常" + e.getMessage());
			// Log.v(TAG, "异常" + e.getMessage());
			return false;
		}
	}

	/**
	 * 生成静态文字
	 * 
	 * @param sptd
	 * @param coord
	 * @throws Exception
	 */
	private void printStaticText(SysPrintTempletDetail sptd, String[] coord)
			throws Exception {
		// Log.v(TAG, "静态文本" + sptd.getId());
		// Log.v(TAG, "静态文本" + sptd.getFieldName());
		int font = getFont(sptd.getFontSize());
		// Log.v(TAG, "静态文本" + font);
		int fontType = getFontType("Y".equals(sptd.getIsBold()),
				font > 32 ? true : false);
		// Log.v(TAG, "静态文本" + fontType);
		int DAWeight = Integer.parseInt(weight);
		// Log.v(TAG, "静态文本" + DAWeight);
		if (sptd.getFieldName().contains("auto")) {
			autoPrintTextUpdown(font, fontType, DAWeight, coord,
					sptd.getFieldRemark());
		} else {
			autoPrintTextRows(font, fontType, DAWeight, coord,
					sptd.getFieldRemark());
		}

	}

	/**
	 * 生成动态文字
	 * 
	 * @param sptd
	 * @param coord
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	private void printDynamicText(SysPrintTempletDetail sptd, String[] coord)
			throws NumberFormatException, Exception {
		// Log.v(TAG, "动态文本");
		// 如何获得动态文字
		// String name = stringToCamel(sptd.getFieldName());
		// Object obj = getObjectValue(name);

		String str = getDyNamicTextStr(sptd.getFieldName());
		if (null == str || "" == str) {
			// Log.v(TAG, "打印动态文字为空");
			return;
		}
		int font = getFont(sptd.getFontSize());
		int fontType = getFontType("Y".equals(sptd.getIsBold()), font / 100 > 0);
		int DAWeight = Integer.parseInt(coord[4]);
		autoPrintTextRows(font % 100, fontType, DAWeight, coord, str);
	}

	private String getDyNamicTextStr(String fieldName) {
		String printText = "";
		Object transStr = orderInfo.get(fieldName);
		if (transStr instanceof BigDecimal) {
			printText = String.valueOf(((BigDecimal) transStr).doubleValue());
		} else if (transStr instanceof Integer) {
			printText = String.valueOf(((Integer) transStr).intValue());
		} else {
			printText = String.valueOf(transStr);
		}
		return printText;
	}

	// 反射机制
	// private Object getObjectValue(String name) throws NoSuchFieldException,
	// IllegalAccessException, IllegalArgumentException {
	// Object obj = null;
	// Field filed = ddo.getClass().getDeclaredField(name);
	// boolean isAccessible = filed.isAccessible();
	// if (!isAccessible) {
	// filed.setAccessible(true);
	// obj = filed.get(ddo);
	// filed.setAccessible(isAccessible);
	// }
	// return obj;
	// }

	/**
	 * 生成图片方法
	 * 
	 * @param sptd
	 * @param coord
	 * @throws Exception
	 */
	private void printImage(SysPrintTempletDetail sptd, String[] coord)
			throws Exception {
		// Log.v(TAG, "图片");
		String imgName = sptd.getFieldName() + ".jpg";
		// Log.i("imgName", imgName);
		InputStream open = getClass().getResourceAsStream(IMGPATH + imgName);
		if (null != open) {
			Bitmap bitmap = compressImg(BitmapFactory.decodeStream(open), coord);
			HPRTPrinterHelper.Expanded(coord[0], coord[1], bitmap, 0);
		} else {
			HPRTPrinterHelper.Box(coord[0], coord[1], coord[2], coord[3], "1");
		}
	}

	/**
	 * 生成条码方法
	 * 
	 * @param sptd
	 * @param coord
	 * @throws Exception
	 */
	private void printBarcode(SysPrintTempletDetail sptd, String[] coord)
			throws Exception {
		// Log.v(TAG, "条码");
		HPRTPrinterHelper.Barcode(HPRTPrinterHelper.BARCODE,
				HPRTPrinterHelper.code128, "1", "1", coord[5], coord[0],
				coord[1], false, "0", "0", "0", orderInfo.get("logi_no")
						.toString());
	}

	/**
	 * 生成直线方法
	 * 
	 * @param sptd
	 * @throws Exception
	 */
	private void printLine(SysPrintTempletDetail sptd, String[] coord)
			throws Exception {
		// Log.v(TAG, "直线");
		// across 横线 upright 竖线
		String fieldName = sptd.getFieldName();
		String width = "1";
		if (fieldName.contains("across")) {
			HPRTPrinterHelper.Line(coord[0], coord[1], coord[2], coord[1],
					width);
		} else if (fieldName.contains("upright")) {
			HPRTPrinterHelper.Line(coord[0], coord[1], coord[0], coord[3],
					width);
		}
	}

	private String[] getArrayForCoord(SysPrintTempletDetail sptd) {
		BigDecimal left = sptd.getLeft().multiply(new BigDecimal(STATICBIT),
				new MathContext(0, RoundingMode.HALF_UP));
		BigDecimal top = sptd.getTop().multiply(new BigDecimal(STATICBIT),
				new MathContext(0, RoundingMode.HALF_UP));
		BigDecimal width = sptd.getWidth().multiply(new BigDecimal(STATICBIT),
				new MathContext(0, RoundingMode.HALF_UP));
		BigDecimal height = sptd.getHeight().multiply(
				new BigDecimal(STATICBIT),
				new MathContext(0, RoundingMode.HALF_UP));
		String[] coord = new String[6];
		int widthADD = left.add(width).intValue();
		int heightADD = top.add(height).intValue();
		coord[0] = left.intValue() + "";
		coord[1] = top.intValue() + "";
		coord[2] = widthADD + "";
		coord[3] = heightADD + "";
		coord[4] = width.intValue() + "";
		coord[5] = height.intValue() + "";
		return coord;
	}

	private int getFont(int fontSize) {
		// 25.4mm = 72pt
		// 获得毫米
		// Log.v("fontSize", fontSize + "");
		int font = (int) (25.4 / 72 * fontSize / 2) * 8;
		if (font <= 16) {
			font = 16;
		} else if (font <= 24) {
			font = 24;
		} else if (font <= 32) {
			font = 32;
		} else if (font <= 48) {
			font = 124;
		} else if (font <= 64) {
			font = 132;
		} else {
			font = 132;
		}
		// Log.v("font", font + "");
		return font;
	}

	private int getFontType(boolean isBold, boolean isDoubleWH) {
		// Log.v("FontType", isDoubleWH + "11111");
		int fontType = 0;
		if (isBold) {
			fontType = fontType | 1;
		}
		if (isDoubleWH) {
			fontType = fontType | 4;
			fontType = fontType | 8;
			// fontType = fontType | 12;
			// Log.v("FontType", fontType +"");
		}
		return fontType;
	}

	private void autoPrintTextRows(int font, int fontType, int DAWeight,
			String[] coord, String str) throws NumberFormatException, Exception {
		int rows = 0;
		if ((fontType & 4) == 4) {
			rows = DAWeight / (font * 2) * 2;
		} else {
			rows = DAWeight / (font) * 2;
		}
		String[] allText = new String[str.length()];// 获得字符长度
		ArrayList<String> listText = new ArrayList<String>();
		// Log.v("zhixing", "自动换行DAWeight" + DAWeight);
		// Log.v("zhixing", "自动换行lenght" + str.length());
		listText.clear();
		int a = 0;
		String text = "";
		for (int i = 0; i < str.length(); i++) {
			allText[i] = "" + str.charAt(i);
			a += allText[i].getBytes("utf8").length;
			text += allText[i];
			if (a > rows) {
				listText.add(text);
				text = "";
				a = 0;
			}
		}
		listText.add(text);
		// Log.v("zhixing", "自动换行" + listText);

		for (int i = 0; i < listText.size(); i++) {
			// Log.v("zhixing", "自动换行" + listText.get(i));
			HPRTPrinterHelper.PrintTextCPCL(HPRTPrinterHelper.TEXT, font,
					coord[0], "" + (Integer.parseInt(coord[1]) + (i * font)),
					listText.get(i), fontType, false, 0);
		}
	}

	private void autoPrintTextUpdown(int font, int fontType, int DAWeight,
			String[] coord, String str) throws NumberFormatException, Exception {
		if (str.contains("(")) {
			str = str.split("\\(")[0];
		}
		for (int i = 0; i < str.length(); i++) {
			// Log.v("zhixing", "垂直打印" + coord[1]);
			HPRTPrinterHelper.PrintTextCPCL(HPRTPrinterHelper.TEXT, font,
					coord[0], ""
							+ (Integer.parseInt(coord[1]) + ((i * font) * 2)),
					str.substring(i, i + 1), fontType, false, 0);
		}
	}

	/**
	 * 驼峰命名法
	 * 
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	private String stringToCamel(String str) {
		if (str == null || str.isEmpty()) {
			return "";
		}
		String[] temp = str.split("_");
		String camelStr = temp[0].toLowerCase();
		for (int i = 1; i < temp.length; i++) {
			String camel = temp[i];
			if (camel.isEmpty()) {
				continue;
			} else if (camel.length() > 1) {
				camelStr += (camel.substring(0, 1).toUpperCase() + camel
						.substring(1).toLowerCase());
			} else {
				camelStr += camel.toUpperCase();
			}
		}
		return camelStr;
	}

	public Bitmap compressImg(Bitmap image, String[] coord) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
		if (baos.toByteArray().length / 1024 > 1024) {
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		int be = 1;// be=1表示不缩放
		Bitmap bitmap = null;
		do {
			ByteArrayInputStream isBm = new ByteArrayInputStream(
					baos.toByteArray());
			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
			newOpts.inJustDecodeBounds = true;
			bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
			newOpts.inJustDecodeBounds = false;

			// Log.v("errr", 1 + "");
			int w = newOpts.outWidth;
			int h = newOpts.outHeight;
			// Log.v("IMGw", w + "");
			// Log.v("IMGh", h + "");
			// Log.i(TAG, w + "---------------" + h);
			// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
			// float hh = 800f;// 这里设置高度为800f
			// float ww = 480f;// 这里设置宽度为480f
			int ww = Integer.parseInt(coord[4]) / 8 * 4;
			int hh = Integer.parseInt(coord[5]) / 8 * 4;
			// Log.v("IMGww", ww + "");
			// Log.v("IMGhh", hh + "");
			// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
			if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
				be = (int) (newOpts.outWidth / ww);
			} else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
				be = (int) (newOpts.outHeight / hh);
			}
			if (be <= 0)
				be = 1;
			newOpts.inSampleSize = be; // 设置缩放比例
			// newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565
			// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
			isBm = new ByteArrayInputStream(baos.toByteArray());
			bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
			baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		} while (be != 1);
		// newOpts.inJustDecodeBounds = false;
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * 质量压缩方法
	 * 
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 90;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
		// Log.v("compressImage", "执行质量压缩");
			baos.reset(); // 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}
}
