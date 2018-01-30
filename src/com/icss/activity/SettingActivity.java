package com.icss.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.icss.R;
import com.icss.config.SharedPreferenceFileConfiguration;
import com.icss.entity.SpinnerKeyValue;
import com.icss.util.SPUtil;

public class SettingActivity extends Activity implements OnClickListener {
	private SPUtil connProfile = null;
	private SPUtil printProfile = null;
	private EditText ipEt;
	private EditText portEt;
	private EditText programEt;
	private EditText widthEt;
	private EditText heightEt;
	private Spinner printSpeed;
	private ArrayAdapter<SpinnerKeyValue<String>> spinnerDatas;
	private String defaultIp = SharedPreferenceFileConfiguration.DEFAULTIP;
	private Integer defaultPort = SharedPreferenceFileConfiguration.DEFAULTPORT;
	private String defaultProgram = SharedPreferenceFileConfiguration.DEFAULTPROGRAM;
	private String defaultPageWidth = SharedPreferenceFileConfiguration.DEFAULTPAGEWIDTH;
	private String defaultPageHeight = SharedPreferenceFileConfiguration.DEFAULTPAGEHEIGHT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_layout);
		init();
	}

	private void init() {
		connProfile = new SPUtil(this,
				SharedPreferenceFileConfiguration.CONNFILENAME);
		printProfile = new SPUtil(this,
				SharedPreferenceFileConfiguration.PRINTFILENAME);
		ipEt = (EditText) findViewById(R.id.ip);
		portEt = (EditText) findViewById(R.id.port);
		programEt = (EditText) findViewById(R.id.program);
		widthEt = (EditText) findViewById(R.id.pageWidthEt);
		heightEt = (EditText) findViewById(R.id.pageHeightEt);
		printSpeed = (Spinner) findViewById(R.id.printSpeed);

		// 初始化 下拉框数据
		spinnerDatas = new ArrayAdapter<SpinnerKeyValue<String>>(this,
				android.R.layout.simple_spinner_item);
		List<SpinnerKeyValue<String>> datas = new ArrayList<SpinnerKeyValue<String>>();
		datas.add(new SpinnerKeyValue<String>("最慢", "0"));
		datas.add(new SpinnerKeyValue<String>("慢", "1"));
		datas.add(new SpinnerKeyValue<String>("一般", "2"));
		datas.add(new SpinnerKeyValue<String>("快", "3"));
		datas.add(new SpinnerKeyValue<String>("较快", "4"));
		datas.add(new SpinnerKeyValue<String>("最快", "5"));
		spinnerDatas.addAll(datas);
		printSpeed.setAdapter(spinnerDatas);
		//设置默认值
		setSpinnerDefault(printSpeed, printProfile.getString("printSpeed",
				SharedPreferenceFileConfiguration.DEFAULTPRINTSPEED));
		printSpeed.setOnItemSelectedListener(printSpeedSelListener);
		findViewById(R.id.saveBtn).setOnClickListener(this);
		findViewById(R.id.exitConfig).setOnClickListener(this);
		findViewById(R.id.settingView).setOnClickListener(this);
		setDefaultValuesForShow();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.saveBtn:
			saveNewValues();
		case R.id.exitConfig:
			Intent intent = new Intent(SettingActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.settingView:
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			break;
		default:
			break;
		}

	}

	/**
	 * 当跳转到配置页面后，赋值默认值
	 */
	private void setDefaultValuesForShow() {
		ipEt.setText(connProfile.getString("ip", defaultIp));
		portEt.setText(connProfile.getInt("port", defaultPort) + "");
		programEt.setText(connProfile.getString("program", defaultProgram));
		widthEt.setText(printProfile.getString("pageWidth", defaultPageWidth));
		heightEt.setText(printProfile
				.getString("pageHeight", defaultPageHeight));
	}

	private void saveNewValues() {
		String ip = ipEt.getText().toString();
		String port = portEt.getText().toString();
		String program = programEt.getText().toString();
		String pageWidth = widthEt.getText().toString();
		String pageHeight = heightEt.getText().toString();
		String printSpeed = printProfile.getString("printSpeed", SharedPreferenceFileConfiguration.DEFAULTPRINTSPEED);
		connProfile.clear();
		connProfile.putString("ip", TextUtils.isEmpty(ip) ? defaultIp : ip);
		connProfile.putInt("port", TextUtils.isEmpty(port) ? defaultPort
				: Integer.parseInt(port));
		connProfile.putString("program",
				TextUtils.isEmpty(program) ? defaultProgram : program);
		printProfile.clear();
		printProfile.putString("pageWidth",
				TextUtils.isEmpty(pageWidth) ? defaultPageWidth : pageWidth);
		printProfile.putString("pageHeight",
				TextUtils.isEmpty(pageHeight) ? defaultPageHeight : pageHeight);
		printProfile.putString("printSpeed", printSpeed);

	}

	@SuppressWarnings("unchecked")
	private void setSpinnerDefault(Spinner spinner, String value) {
		SpinnerAdapter apsAdapter = spinner.getAdapter(); // 得到SpinnerAdapter对象
		for (int i = 0; i < apsAdapter.getCount(); i++) {
			SpinnerKeyValue<String> skvObj = (SpinnerKeyValue<String>) apsAdapter
					.getItem(i);
			if (value.equals(skvObj.getValue())) {
				spinner.setSelection(i, true);
			}
		}
	}
	public OnItemSelectedListener printSpeedSelListener = new OnItemSelectedListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			SpinnerKeyValue<String> selSPKObj = (SpinnerKeyValue<String>) printSpeed.getSelectedItem();
			String value = selSPKObj.getValue();
			printProfile.putString("printSpeed", value);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			printProfile.putString("printSpeed", SharedPreferenceFileConfiguration.DEFAULTPRINTSPEED);
			
		}
		
		
	};
}
