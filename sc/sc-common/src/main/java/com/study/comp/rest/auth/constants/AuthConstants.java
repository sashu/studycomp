package com.study.comp.rest.auth.constants;

public class AuthConstants {

	public interface LoginParams {
		static final String DEVICE = "device";
		static final String USER = "user";
		static final String EXPIRY = "exp";
		static final String NOT_BEFORE = "nbf";
		static final String REQUEST_CLIENT = "request_client";
		static final String REQUEST_DEVICE = "request_device";
		static final String REQUEST_CLIENT_DB = "request_client_db";
	}

	public interface LoginDevice {
		static final String IOS = "ios";
		static final String WEB = "web";
		static final String ANDROID = "android";
		static final String WINDOWS = "windows";
	}

}
