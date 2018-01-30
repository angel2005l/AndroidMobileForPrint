package com.icss.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Context;

import com.icss.entity.PrmtUser;
import com.icss.entity.Result;
import com.icss.util.ConnUtil;
import com.icss.util.JsonUtil;

public class UserService {

	public Result<PrmtUser> login(PrmtUser userInfo, Context context)
			throws URISyntaxException, ClientProtocolException, IOException {
		Result<PrmtUser> result = null;
		PrmtUser user = null;
		ArrayList<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("userName", userInfo.getUser_name()));
		qparams.add(new BasicNameValuePair("password", userInfo.getPassword()));
		HttpEntity entity = ConnUtil.getEntity(context, qparams, "login.json");
		JSONObject o = null;
		try {
			if (entity != null) {
				String contentString = EntityUtils.toString(entity);
				o = new JSONObject(contentString);
				// Log.v("o", o.toString());
				int status = o.getInt("status");
				// Log.v("login", status + "");
				if (status == 0) {
					// Log.v("loginLog", o.getString("data"));
					user = JsonUtil.parseJson(o.getString("data"),
							PrmtUser.class);
					// Log.v("loginLOG", user.toString());
				}
				result = new Result<PrmtUser>(status, o.getString("msg"), user);
			} else {
				result = new Result<PrmtUser>(999, "服务器端未响应");
			}
		} catch (Exception e) {
			// Log.v("except", e.getMessage());
			result = new Result<PrmtUser>(999, "服务器端异常");
		} finally {
			ConnUtil.closeConn();
		}
		return result;
	}
}
