package com.icss.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.icss.R;
import com.icss.entity.PrmtUser;
import com.icss.entity.Result;
import com.icss.service.UserService;
import com.icss.util.DialogUtil;
import com.icss.util.LogUtil;

@SuppressLint("HandlerLeak")
public class LoginActivity extends Activity implements OnClickListener {
	private static final String TAG = "LoginActivity";
	private EditText userId;
	private EditText userPass;
	private Context mContext;
	private Dialog mDialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_layout);
		LogUtil.regist(this);
		mContext = this;
		userId = (EditText) findViewById(R.id.userId);
		userPass = (EditText) findViewById(R.id.userPass);
		// Button btn = (Button) findViewById(R.id.loginBtn);
		// saveProfile = (Button) findViewById(R.id.saveProfileBtn);
		findViewById(R.id.loginView).setOnClickListener(this);
		findViewById(R.id.loginBtn).setOnClickListener(this);
		findViewById(R.id.saveProfileBtn).setOnClickListener(this);
		// btn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// String userIdStr = userId.getText().toString();
		// String userPassStr = userPass.getText().toString();
		// if ("".equals(userIdStr) || "".equals(userPassStr)
		// || null == userIdStr || null == userPassStr) {
		// Toast.makeText(LoginActivity.this, "账户名/密码不能为空",
		// Toast.LENGTH_LONG).show();
		// } else {
		// new GetOrderInfo(userIdStr, userPassStr).start();
		// }
		// }
		// });

		// saveProfile.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent(LoginActivity.this,
		// SettingActivity.class);
		// startActivity(intent);
		// finish();
		// }
		// });

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginView:
			InputMethodManager imm = (InputMethodManager)  
	         getSystemService(Context.INPUT_METHOD_SERVICE);  
	         imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			break;
		case R.id.loginBtn:
			String userIdStr = userId.getText().toString();
			String userPassStr = userPass.getText().toString();
			if ("".equals(userIdStr) || "".equals(userPassStr)
					|| null == userIdStr || null == userPassStr) {
				Toast.makeText(LoginActivity.this, "账户名/密码不能为空",
						Toast.LENGTH_LONG).show();
			} else {
				mDialog = DialogUtil.createDialogView(mContext, "登录中，请稍后...");
				new GetOrderInfo(userIdStr, userPassStr).start();
			}
			break;
		case R.id.saveProfileBtn:
			Intent intent = new Intent(LoginActivity.this,
					SettingActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			int status = msg.getData().getInt("status");
			if (status == 0) {
				//Log.i("LoginMsg", "跳转" + msg.getData());
				Intent toHomeActivity = new Intent(LoginActivity.this,
						MainActivity.class);
				toHomeActivity.putExtra("userInfo", msg.getData());
				startActivity(toHomeActivity);
				finish();
			} else if (status == 999) {
				//Log.i("LoginMsg", "登录失败");
				Toast.makeText(LoginActivity.this,
						msg.getData().getString("msg"), Toast.LENGTH_SHORT)
						.show();
			} else {
				//Log.i("LoginMsg", "异常登录");
				Toast.makeText(LoginActivity.this,
						msg.getData().getString("msg"), Toast.LENGTH_SHORT)
						.show();
			}
		};
	};

	public class GetOrderInfo extends Thread {
		private String userIdStr;
		private String userPassStr;

		public GetOrderInfo(String userIdStr, String userPassStr) {
			this.userIdStr = userIdStr;
			this.userPassStr = userPassStr;
		}

		@Override
		public void run() {
			Result<PrmtUser> result = new Result<PrmtUser>();
			PrmtUser user = new PrmtUser();
			user.setUser_name(userIdStr);
			user.setPassword(userPassStr);
			try {
				result = new UserService().login(user, LoginActivity.this);
			} catch (Exception e) {
				LogUtil.e("【"+TAG+"】"+"服务器连接异常"+e.getMessage());
				result = new Result<PrmtUser>(999, "服务器连接异常");
			}
			//Log.v("result", result.toString());
			Bundle bundle = new Bundle();
			Message message = new Message();
			int status = result.getStatus();
			if (status == 0) {
				bundle.putSerializable("userObj", result.getData());
			}
			bundle.putInt("status", result.getStatus());
			bundle.putString("msg", result.getMsg());
			message.setData(bundle);
			DialogUtil.colseDialog(mDialog);
			mHandler.sendMessage(message);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		userId.setText("");
		userPass.setText("");
		userId.requestFocus();
	}

	@Override
	protected void onStop() {
		super.onStop();

	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}