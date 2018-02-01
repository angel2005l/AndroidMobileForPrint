package com.icss.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.icss.config.SharedPreferenceFileConfiguration;
import com.icss.entity.Result;
import com.icss.entity.SysPrintTempletDetail;
import com.icss.util.ConnUtil;
import com.icss.util.JsonUtil;
import com.icss.util.SPUtil;

public class PrintService {
	/**
	 * 与服务器进行交互 根据输入框的运单号 与 面单类型设置数据 来获得订单信息 ，打印模板信息
	 * 
	 * @param orderNo
	 * @param context
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Result<Map<String, Object>> getDdoAndSptds(String orderNo,
			Context context) throws URISyntaxException,
			ClientProtocolException, IOException {
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		ArrayList<NameValuePair> qparams = new ArrayList<NameValuePair>();
		SPUtil spUtil = new SPUtil(context,
				SharedPreferenceFileConfiguration.PRINTFILENAME);

		qparams.add(new BasicNameValuePair("logiNo", orderNo));
		qparams.add(new BasicNameValuePair("pageWidth", spUtil
				.getString("pageWidth",
						SharedPreferenceFileConfiguration.DEFAULTPAGEWIDTH)));
		qparams.add(new BasicNameValuePair("pageHeight", spUtil.getString(
				"pageHeight",
				SharedPreferenceFileConfiguration.DEFAULTPAGEHEIGHT)));
		HttpEntity entity = ConnUtil.getEntity(context, qparams, "print.json");
		JSONObject o = null;
		if (null != entity) {
			// Log.v("PrintEWaybill", entity.toString());
			String contentString = EntityUtils.toString(entity);
			try {
				o = new JSONObject(contentString);
				int status = o.getInt("status");
				String msg = o.getString("msg");
				result.setStatus(status);
				// Log.v("printLogStatus", status + "");
				result.setMsg(msg);
				// Log.v("printLogMsg", msg);
				if (status == 0) {
					Map<String, Object> data = new HashMap<String, Object>();
					JSONObject jsonObject = o.getJSONObject("data");

					String ddoJson = jsonObject.getString("orderData");
					// Log.v("newfORoRDERdATAJSON", ddoJson);
					Map<String, Object> orderData = JsonUtil.json2Map(ddoJson);
					// Log.v("newfORoRDERdATA", orderData.toString());
					String sptdsJson = jsonObject.getString("sptds");
					List<SysPrintTempletDetail> sptds = JsonUtil.json2List(
							sptdsJson, SysPrintTempletDetail.class);
					// Log.v("newFoeList", sptds.toString());
					data.put("orderData", orderData);
					data.put("sptds", sptds);
					result.setData(data);
				}
			} catch (JSONException e) {
				return null;
			}
		}
		return result;
	}

}
