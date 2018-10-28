package com.wm.edu.utils.pay.common;

import com.wm.edu.utils.common.AttaUtils;

public class Configure {
	private static String key = AttaUtils.getConfigValueByKey("key");

	//小程序ID	
	private static String appID = AttaUtils.getConfigValueByKey("appid");
	//商户号
	private static String mch_id = AttaUtils.getConfigValueByKey("mch_id");
	//
	private static String secret = AttaUtils.getConfigValueByKey("secret");



	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		Configure.secret = secret;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static String getAppID() {
		return appID;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static String getMch_id() {
		return mch_id;
	}

	public static void setMch_id(String mch_id) {
		Configure.mch_id = mch_id;
	}

}
