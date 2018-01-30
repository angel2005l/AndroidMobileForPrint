package com.icss.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icss.R;

public class DialogUtil {

	// 创建Loaidn试图 并获得调用对象的Context
	public static Dialog createDialogView(Context context, String msg) {
		// 根据context会的视图 视图名dialogLoadingView
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.diglog_loading, null);// 加载视图
		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.dialogLoadingView);// 添加布局
		TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView);// 添加提示文字tipTextView
		tipTextView.setText(msg);

		Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);
		loadingDialog.setCancelable(true);// 支持返回键 消失
		loadingDialog.setCanceledOnTouchOutside(false);// 不支持 点击 loading外部
														// loading消失
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));// 设置loading的布局

		/**
		 * 封装DiaLoading 显示方法
		 */
		Window window = loadingDialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		window.setGravity(Gravity.CENTER);
		window.setAttributes(lp);
		window.setWindowAnimations(R.style.PopWindowAnimStyle);
		loadingDialog.show();
		return loadingDialog;
	}

	/**
	 * 关闭dialog
	 * 
	 * @param mDialogLoading
	 */
	public static void colseDialog(Dialog mDialogLoading) {
		if (null != mDialogLoading && mDialogLoading.isShowing()) {
			mDialogLoading.dismiss();
		}
	}

	/**
	 * title默认为"提示"
	 * 
	 * @param context
	 * @param msg
	 */
	public static void dialogMsg(Context context, String msg, String title,OnClickListener listener) {
		AlertDialog.Builder dlg = new AlertDialog.Builder(context);
		dlg.setTitle(TextUtils.isEmpty(title) ? "提示" : title);
		dlg.setMessage(msg);
		dlg.setPositiveButton("确定", listener);
		dlg.show();
	}
}
