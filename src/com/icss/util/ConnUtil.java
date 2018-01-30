package com.icss.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import com.icss.config.SharedPreferenceFileConfiguration;

import android.content.Context;

public class ConnUtil {

//	private static SPUtil connProfile;
	private static HttpClient httpClient;
	private static String fileName = SharedPreferenceFileConfiguration.CONNFILENAME;

	public static HttpEntity getEntity(Context context,
			ArrayList<NameValuePair> qparams,String reqFun) throws URISyntaxException,
			ClientProtocolException, IOException {
		httpClient = new DefaultHttpClient();
		SPUtil connProfile = new SPUtil(context, fileName);
		URI uri = URIUtils.createURI("http",
				connProfile.getString("ip", SharedPreferenceFileConfiguration.DEFAULTIP),
				connProfile.getInt("port", SharedPreferenceFileConfiguration.DEFAULTPORT),
				connProfile.getString("program", SharedPreferenceFileConfiguration.DEFAULTPROGRAM) + "/"+reqFun,
				URLEncodedUtils.format(qparams, "UTF-8"), null);
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new UrlEncodedFormEntity(qparams, "UTF-8"));
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity entity = httpResponse.getEntity();
		return (null == entity ? null : entity);
	}

	public static void closeConn() {
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
		}
	}
}
