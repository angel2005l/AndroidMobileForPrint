package com.icss.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import HPRTAndroidSDKA300.HPRTPrinterHelper;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.icss.entity.Result;

public class BluetoothUtil {
	private BluetoothSocket socket = null;
	// 蓝牙对象
	private BluetoothAdapter bluetoothObj;

	public BluetoothAdapter getBluetoothObj() {
		return bluetoothObj;
	}

	// 获得单例的蓝牙
	private BluetoothUtil() {
		this.bluetoothObj = BluetoothAdapter.getDefaultAdapter();
	}

	// 单例模式
	public static synchronized BluetoothUtil getInstance() {
		return SingletonHolder.instance;
	}

	private static final class SingletonHolder {
		private static BluetoothUtil instance = new BluetoothUtil();
	}

	// 打开蓝牙，注册扫扫描蓝牙广播
	public boolean onCreat(Activity activity) {
		if (null != bluetoothObj) {
			if (!bluetoothObj.isEnabled()) {// 判断蓝牙是否被打开
				Intent enableIntent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				activity.startActivityForResult(enableIntent,
						Activity.RESULT_FIRST_USER);
				Intent displayIntent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
				displayIntent.putExtra(
						BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
				activity.startActivity(displayIntent);
				bluetoothObj.enable();
				// 蓝牙开启成功
			} else {
				// 蓝牙已经打开
			}
			return true;
		} else {
			return false;
		}
	}

	// 获得扫描设备集合
	public Result<Object> scanDevice() {
		// Log.v("isScan", "loading");
		Result<Object> result = new Result<Object>();
		if (bluetoothObj == null || !bluetoothObj.isEnabled()) {
			// 蓝牙状态异常
			result.setStatus(-1);
			result.setMsg("蓝牙状态异常");
			return result;
		}
		if (bluetoothObj.isDiscovering()) {// 正在扫描
			// 停止扫描
			bluetoothObj.cancelDiscovery();
		}
		// 开始搜索
		int intStartCount = 0;
		while (!bluetoothObj.startDiscovery() && intStartCount < 5) {
			// Log.e("BlueTooth", "扫描尝试失败");
			intStartCount++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (intStartCount > 4) {
			result.setStatus(-1);
			result.setMsg("扫描尝试失败");
		} else {
			result.setStatus(0);
			result.setMsg("扫描已开启");
		}
		return result;
	}

	// 获得已匹配数据
	public List<String> getPaireDevice() {
		List<String> data = new ArrayList<String>();
		// Log.v("btnPaireDevice", bluetoothObj.isEnabled() + "");
		if (!bluetoothObj.isEnabled()) {
			bluetoothObj = BluetoothAdapter.getDefaultAdapter();
			bluetoothObj.enable();
		}
		// Log.v("btnPaireDeviceafter", bluetoothObj.isEnabled() + "");
		Set<BluetoothDevice> paireDevices = bluetoothObj.getBondedDevices();
		// Log.v("paireDevices", paireDevices.toString());
		if (paireDevices.size() > 0) {
			for (BluetoothDevice device : paireDevices) {
				// Log.v("paireDevice", device.toString());
				data.add(device.getName() + "\n" + device.getAddress());
				// 记录已匹配过的设备信息
			}
		}
		return data;
	}

	private int status;

	/**
	 * 获得蓝牙连接
	 * 
	 * @param toothAddress
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public synchronized int getBluetoothConn(final String toothAddress,
			final Context context) throws Exception {
		new Thread(new Runnable() {

			@Override
			public void run() {
				status = -1;
				if (bluetoothObj.isDiscovering()) {// 正在扫描
					// 停止扫描
					bluetoothObj.cancelDiscovery();
				}
				new HPRTPrinterHelper(context,
						HPRTPrinterHelper.PRINT_NAME_A300);

				try {
					if (HPRTPrinterHelper.IsOpened()) {
						HPRTPrinterHelper.PortClose();
					}
					status = HPRTPrinterHelper.PortOpen("Bluetooth,"
							+ toothAddress);

					// HPRTPrinterHelper.logcat("portOpen:" + status);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}

		}).start();

		return status;

	}

	// 广播反注册，连接页面调用
	public void unregisterReceiver(Activity activity) {
		if (receiver != null && receiver.getAbortBroadcast()) {
			activity.unregisterReceiver(receiver);
		}
	}

	// 关闭蓝牙 当app退出时
	public void onExit() {
		if (bluetoothObj != null) {
			bluetoothObj.cancelDiscovery();
			// 关闭蓝牙
			bluetoothObj.disable();
		}
		closeCloseable(writer, socket);
	}

	private OnFoundUnBondDeviceListener onFoundUnBondDeviceListener;
	private BufferedWriter writer = null;

	public void setOnFoundUnBondDeviceListener(
			OnFoundUnBondDeviceListener onFoundUnBondDeviceListener) {
		this.onFoundUnBondDeviceListener = onFoundUnBondDeviceListener;
	}

	public interface OnFoundUnBondDeviceListener {
		void foundUnBondDevice(BluetoothDevice unBondDevice);
	}

	// 关闭所有连接
	private void closeCloseable(Closeable... closeable) {
		if (null != closeable && closeable.length > 0) {
			// 当关闭对象存在时
			for (int i = 0; i < closeable.length; i++) {
				if (closeable[i] != null) {
					try {
						closeable[i].close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						closeable[i] = null;
					}
				}
			}
		}
	}

	// 注册receiver广播
	// ACTION_FOUND
	// ACTION_DISCOVERY_STARTED
	// ACTION_DISCOVERY_FINISHED
	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {// 正在搜索
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
					if (null != onFoundUnBondDeviceListener) {
						// 没有匹配过的设备
						onFoundUnBondDeviceListener.foundUnBondDevice(device);
					}

				} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
						.equals(action)) {
					// 没有发现设备
				}
			}

		}
	};
}
