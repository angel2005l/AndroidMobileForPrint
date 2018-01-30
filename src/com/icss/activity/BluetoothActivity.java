package com.icss.activity;

import java.text.DecimalFormat;
import java.util.List;

import HPRTAndroidSDKA300.HPRTPrinterHelper;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.icss.R;
import com.icss.entity.Result;
import com.icss.util.BluetoothUtil;

@SuppressLint("HandlerLeak")
public class BluetoothActivity extends Activity {
	// 返回 Intent的extra
	public static String EXTRA_DEVICE_ADDRESS = "device_address";
	public List<String> pairedDeviceList = null;
	public List<String> newDeviceList = null;
	public ArrayAdapter<String> mPairedDevicesArrayAdapter;
	public ArrayAdapter<String> mNewDevicesArrayAdapter;
	public static String toothAddress = null;
	public static String toothName = null;
	private ListView pairedListView = null;
	private ListView newDevicesListView = null;
	private Context thisCon = null;
	private Button scanButton = null;
	private TextView pairedDevices = null;
	private TextView newDevices = null;
	private String strAddressList = "";
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			progress.setVisibility(View.GONE);
//			Log.v("handler", msg.toString());
			Intent intentForResult = new Intent();
			intentForResult.putExtra("is_connected", msg.what);
			intentForResult.putExtra("BTAddress", toothAddress);
//			Log.v("toothAddresshandler", intentForResult+"");
			setResult(HPRTPrinterHelper.ACTIVITY_CONNECT_BT, intentForResult);
			finish();
		};
	};
	private ProgressBar progress;
	private BluetoothUtil instance = BluetoothUtil.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.bluetooth_layout);
		setResult(Activity.RESULT_CANCELED);
		init();
	}

	// 初始化
	public void init() {
		setTitle("设备列表");
		pairedDevices = (TextView) findViewById(R.id.title_paired_devices);
		newDevices = (TextView) findViewById(R.id.title_new_devices);
		progress = (ProgressBar) findViewById(R.id.progress);
		scanButton = (Button) findViewById(R.id.button_scan);
		scanButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				strAddressList = "";
				doDiscovery();
				v.setVisibility(View.GONE);
			}

		});
		thisCon = this.getApplicationContext();
		pairedListView = (ListView) findViewById(R.id.paired_devices);
		newDevicesListView = (ListView) findViewById(R.id.new_devices);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// 初始化 arryadapter 已经配对的设备和新扫描到得设备
		mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		mNewDevicesArrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		pairedListView.setAdapter(mPairedDevicesArrayAdapter);
		newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
		pairedData();
		String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";
		IntentFilter intent = new IntentFilter();
		intent.addAction(BluetoothDevice.ACTION_FOUND);// 用BroadcastReceiver来取得搜索结果
		intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
		intent.addAction(ACTION_PAIRING_REQUEST);
		intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
		intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		intent.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(mReceiver, intent);
		try {
			pairedListView.setOnItemClickListener(mDeviceClickListener);
			newDevicesListView.setOnItemClickListener(mDeviceClickListener);
		} catch (Exception e) {
			Toast.makeText(this, "获取设备失败:" + e, Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		BluetoothAdapter mBtAdapter = instance.getBluetoothObj();
		// 确认是否还需要做扫描
		if (mBtAdapter != null)
			mBtAdapter.cancelDiscovery();
		if (thread != null) {
			Thread dummy = thread;
			thread = null;
			dummy.interrupt();
		}
		unregisterReceiver(mReceiver);
	}

	// 点击扫描
	private void doDiscovery() {
		// 在标题中注明扫描
		setProgressBarIndeterminateVisibility(true);
		setTitle("正在扫描中");
		// 打开子标题的新设备
		newDevices.setVisibility(View.VISIBLE);
		Result<Object> result = instance.scanDevice();
		if (result.getStatus() == -1) {
			Toast.makeText(BluetoothActivity.this, result.getMsg(),
					Toast.LENGTH_SHORT).show();
		}
	}

	// 获得已配对的设备
	public void pairedData() {
		pairedDevices.setVisibility(View.VISIBLE);
		List<String> paireDevice = instance.getPaireDevice();
		mPairedDevicesArrayAdapter.clear();
		if (!paireDevice.isEmpty()) {
			mPairedDevicesArrayAdapter.addAll(paireDevice);
		} else {
			mPairedDevicesArrayAdapter.add("未发现设备");
		}
	}

	// 扫描完成时候，改变按钮text
	public final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			BluetoothDevice device = null;
			// 搜索设备时，取得设备的MAC地址
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				pairedData();
				device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (device.getBondState() == BluetoothDevice.BOND_NONE) {
					if (device.getBluetoothClass().getMajorDeviceClass() == 1536) {
						if (!strAddressList.contains(device.getAddress())) {
							Bundle b = intent.getExtras();
							String object = String
									.valueOf(b
											.get("android.bluetooth.device.extra.RSSI"));
							int valueOf = Integer.valueOf(object);
							float power = (float) ((Math.abs(valueOf) - 59) / (10 * 2.0));
							float pow = (float) Math.pow(10, power);
							strAddressList += device.getAddress() + ",";
							DecimalFormat decimalFormat = new DecimalFormat(
									"0.00");
							mNewDevicesArrayAdapter.add(device.getName() + "  "
									+ decimalFormat.format(pow) + " m" + "\n"
									+ device.getAddress());
						}
					}
				}
			} else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
				device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				switch (device.getBondState()) {
				case BluetoothDevice.BOND_BONDING:
//					 Log.d("BlueToothTestActivity", "正在配对......");
					break;
				case BluetoothDevice.BOND_BONDED:
//					 Log.d("BlueToothTestActivity", "完成配对");
					break;
				case BluetoothDevice.BOND_NONE:
//					 Log.d("BlueToothTestActivity", "取消配对");
				default:
					break;
				}
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
					.equals(action)) {
				setProgressBarIndeterminateVisibility(false);
				setTitle("设备列表");
				scanButton.setVisibility(View.VISIBLE);
				if (mNewDevicesArrayAdapter.getCount() == 0) {
					mNewDevicesArrayAdapter.add("无可用设备");
				}

			}
		}
	};

	private Thread thread;
	Message message = null;
	// 给列表的中的蓝牙设备创建监听事件
	public OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
			message = new Message();
			progress.setVisibility(View.VISIBLE);			
			ListView listView =  (ListView) av;
			String info = (String) listView.getItemAtPosition(arg2);
//			 Log.v("ipAddress", info+"----"+info.length());
			if (info.length() < 17) {
				message.what = 1;
			} else {
				toothAddress = info.substring(info.length() - 17);
//				 Log.v("bltAddressListener", toothAddress);
				if (!toothAddress.contains(":")) {
					message.what = -1;
				}else{	
					try {
						message.what = instance.getBluetoothConn(toothAddress, thisCon);
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}	
				}
			}
			 handler.sendMessage(message);
//			thread = new Thread(new Runnable() {
//				public void run() {
//					try {
//						if (message.what == 0) {
//							Log.v("isGo", "go");
//							int whatId = instance.getBluetoothConn(
//									toothAddress, thisCon); 
//							message.what = whatId;
//							Log.v("isGowhat", whatId+"go");
//						}
//						handler.sendMessage(message);
//					} catch (Exception e) {
//						Log.e("btnPrint", e.getMessage()+"iserr");
//						e.printStackTrace();
//					}
//				}
//			});
//			thread.start();
		}
	};

	protected void onStop() {
		super.onStop();
		if (thread != null) {
			Thread dummy = thread;
			thread = null;
			dummy.interrupt();
		}
	};
}
