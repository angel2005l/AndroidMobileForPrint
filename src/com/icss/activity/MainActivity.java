package com.icss.activity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import HPRTAndroidSDKA300.HPRTPrinterHelper;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.icss.R;
import com.icss.config.SharedPreferenceFileConfiguration;
import com.icss.entity.PrmtUser;
import com.icss.entity.Result;
import com.icss.entity.SerializableMap;
import com.icss.entity.SysPrintTempletDetail;
import com.icss.service.PrintService;
import com.icss.util.BluetoothUtil;
import com.icss.util.DialogUtil;
import com.icss.util.LogUtil;
import com.icss.util.PrintUtil;
import com.icss.util.SPUtil;
import com.icss.util.SoundUtil;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity implements OnClickListener,
		OnKeyListener {
	private static final String TAG = "MainActivity";
	private Intent loginIntent;
	private TextView userName;
	private TextView blueToothStatus;
	private EditText orderEdit;
	private Button printBtn;
	private static HPRTPrinterHelper HPRTPrinter;
	private Dialog mDialog;
	private Context mContext;
	private SoundUtil soundUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);
		loginIntent = getIntent();
		mContext = MainActivity.this;
		init();
	}

	private void init() {
		soundUtil = SoundUtil.init(MainActivity.this);
		userName = (TextView) findViewById(R.id.userName);
		blueToothStatus = (TextView) findViewById(R.id.blueToothStatus);
		printBtn = (Button) findViewById(R.id.printBth);
		orderEdit = (EditText) findViewById(R.id.orderEdit);
		findViewById(R.id.blueToothSreach).setOnClickListener(this);
		orderEdit.setOnKeyListener(this);
		printBtn.setOnClickListener(this);
		findViewById(R.id.exitBtn).setOnClickListener(this);
		Message message = new Message();
		message.setData(loginIntent.getBundleExtra("userInfo"));
		mHandler.sendMessage(message);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.blueToothSreach:
			if (HPRTPrinter != null) {
				try {
					HPRTPrinterHelper.PortClose();
				} catch (Exception e) {
					LogUtil.e("【" + TAG + "】" + "初始化蓝牙连接失败" + e.getMessage());
					Toast.makeText(mContext, "初始化蓝牙连接失败", Toast.LENGTH_SHORT)
							.show();
				}
			}
			boolean isEnable = BluetoothUtil.getInstance().onCreat(
					MainActivity.this);
			if (isEnable) {
				// Log.v("isEnable", isEnable + "");
				Intent intentForBluetooth = new Intent(mContext,
						BluetoothActivity.class);
				startActivityForResult(intentForBluetooth,
						HPRTPrinterHelper.ACTIVITY_CONNECT_BT);
			} else {
				// LogUtil.i("【" + TAG + "】" + "该设备不支持蓝牙");
				Toast.makeText(mContext, "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.exitBtn:
			Intent toExit = new Intent(mContext, LoginActivity.class);
			startActivity(toExit);
			finish();
			break;
		case R.id.printBth:
			String orderNo = orderEdit.getText().toString();
			if (null == orderNo || orderNo.isEmpty()) {
				Toast.makeText(getApplicationContext(), "请输入运单号",
						Toast.LENGTH_SHORT).show();
			} else if (!HPRTPrinterHelper.IsOpened()) {
				Toast.makeText(getApplicationContext(), "请先连接蓝牙",
						Toast.LENGTH_SHORT).show();
			} else {
				try {
					// 0：打印机准备就绪。
					// 1：打印机打印中。
					// 2：打印机缺纸。
					// 6：打印机开盖。
					// 其他：出错。
					switch (HPRTPrinterHelper.getstatus()) {
					case 0:
					case 1:
						mDialog = DialogUtil.createDialogView(mContext,
								"打印中...");
						// 执行查询方法
						new PrintRun(orderNo).start();
						break;
					case 2:
						showResult(false, "打印机缺纸/纸带安装不正确,请安装打印纸...");
						break;
					case 6:
						showResult(false, "打印机开盖,请检查打印机盖子是否关闭...");
						break;
					default:
						showResult(false, "打印机未知错误,请重新连接打印机或重启蓝牙设备...");
						break;
					}
				} catch (Exception e) {
					LogUtil.e("【" + TAG + "】" + "执行打印操作异常" + e.getMessage());
				}
			}
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		switch (v.getId()) {
		case R.id.orderEdit:
			if (keyCode == KeyEvent.KEYCODE_ENTER) {
				printBtn.requestFocus();
				printBtn.performClick();
				return true;
			}
			return false;
		default:
			return false;
		}
	}

	// private void printOnclick() {
	// printBtn.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// String orderNo = orderEdit.getText().toString();
	// if (null == orderNo || orderNo.isEmpty()) {
	// Toast.makeText(getApplicationContext(), "请输入运单号",
	// Toast.LENGTH_SHORT).show();
	// } else if (!HPRTPrinterHelper.IsOpened()) {
	// Toast.makeText(getApplicationContext(), "请先连接蓝牙",
	// Toast.LENGTH_SHORT).show();
	// } else {
	// mDialog = DialogUtil.createDialogView(mContext, "打印中...");
	// // 执行查询方法
	// new PrintRun(orderNo).start();
	// }
	//
	// }
	// });
	//
	// }

	private final Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			// Log.i("secondHandler", msg.getData().toString());
			PrmtUser user = (PrmtUser) msg.getData().getSerializable("userObj");

			userName.setText(user.getUser_name());
		};
	};

	private final Handler printHandler = new Handler() {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void handleMessage(Message msg) {

			if (!HPRTPrinterHelper.IsOpened()) {
				Toast.makeText(getApplicationContext(), "蓝牙连接异常",
						Toast.LENGTH_SHORT).show();
			} else {
				boolean isPrint = false;
				String errMsg = "";
				try {
					Bundle data = msg.getData();
					if (null == data) {
						isPrint = false;
						errMsg = "客户端数据传输异常";
					} else {
						int status = data.getInt("status");
						if (status == 0) {
							ArrayList list = data
									.getParcelableArrayList("sptds");
							SerializableMap map = (SerializableMap) data
									.getSerializable("orderData");
							Map<String, Object> orderInfo = map.getMap();
							List<SysPrintTempletDetail> sptds = (List<SysPrintTempletDetail>) list
									.get(0);
							SPUtil printProfile = new SPUtil(mContext,
									"printProfiles");
							isPrint = new PrintUtil(
									printProfile.getString("pageHeight", "180"),
									printProfile.getString("pageHeight", "75"),
									"1",
									printProfile
											.getString(
													"printSpeed",
													SharedPreferenceFileConfiguration.DEFAULTPRINTSPEED),
									orderInfo, sptds).print();// 用于打印反馈的结果
						} else {
							isPrint = false;
							errMsg = data.getString("msg");
							// Log.v(TAG, errMsg);
						}
					}
				} catch (Exception e) {
					LogUtil.e("【" + TAG + "】" + "打印数据交互异常" + e.getMessage());
				} finally {
					DialogUtil.colseDialog(mDialog);
					showResult(isPrint, errMsg);
				}
			}
		}
	};

	/**
	 * 用于展示打印时错误，并播放相应声音
	 * 
	 * @param isPrint
	 * @param errMsg
	 */
	private void showResult(boolean isPrint, String errMsg) {
		if (isPrint) {
			soundUtil.paly(1);
			orderEdit.setText("");
			orderEdit.requestFocus();
		} else {
			soundUtil.paly(2);
			DialogUtil.dialogMsg(mContext, errMsg, "打印错误",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							orderEdit.setText("");
							orderEdit.requestFocus();
						}
					});
		}
	}

	class PrintRun extends Thread {
		private String logiNo;

		public PrintRun(String logiNo) {
			this.logiNo = logiNo;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void run() {
			Message message = new Message();
			try {
				Bundle bundle = new Bundle();
				Result<Map<String, Object>> result = new PrintService()
						.getDdoAndSptds(logiNo, mContext);
				if (null != result) {
					bundle.putInt("status", result.getStatus());
					bundle.putString("msg", result.getMsg());
					if (result.getStatus() == 0) {
						ArrayList list = new ArrayList();
						list.add(result.getData().get("sptds"));
						bundle.putParcelableArrayList("sptds", list);
						SerializableMap map = new SerializableMap();
						map.setMap((Map<String, Object>) result.getData().get(
								"orderData"));
						bundle.putSerializable("orderData", map);
					}
				} else {
					bundle.putInt("status", 500);
					bundle.putString("msg", "服务器交互异常，请联系技术人员");
				}
				message.setData(bundle);
				printHandler.sendMessage(message);
			} catch (URISyntaxException | IOException e) {
				LogUtil.e("【" + TAG + "】" + "服务器/客户端数据交互异常" + e.getMessage());
			}
			super.run();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case HPRTPrinterHelper.ACTIVITY_CONNECT_BT:
			try {
				// 0：连接成功，-1：连接异常，-2：蓝牙地址错误，
				// -3：打印机与SDK 不匹配（握手指令错误），-4：SO 库加载错误。
				// 1：无设备连接
				if (null == data) {
					blueToothStatus.setText("未选择蓝牙设备");
				} else {
					int status = data.getExtras().getInt("is_connected");
					if (status == 0) {
						blueToothStatus.setText("连接成功！");
					} else {
						blueToothStatus.setText("连接失败！" + status);
					}
				}
			} catch (Exception e) {
				LogUtil.e("【" + TAG + "】" + "蓝牙连接建立异常" + e.getMessage());
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);

	};

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {

		super.onStop();
	}

	@Override
	protected void onDestroy() {
		try {
			HPRTPrinterHelper.PortClose();
			BluetoothUtil.getInstance().onExit();
		} catch (Exception e) {
			LogUtil.e("【" + TAG + "】" + "终端蓝牙设备关闭/蓝牙打印机断开蓝牙连接异常"
					+ e.getMessage());
		}
		super.onDestroy();
	}
}
