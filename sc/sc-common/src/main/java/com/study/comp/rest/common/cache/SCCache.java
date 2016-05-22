package com.study.comp.rest.common.cache;

import java.util.HashMap;
import java.util.Map;

public class SCCache {

	public static Map<String, Object> CACHE = new HashMap<String, Object>();

	public static void addToCache(String key, Object value) {
		CACHE.put(key, value);
	}

	public static Object getFromCache(String key) {
		return CACHE.get(key);
	}

	public static void clearCache() {
		CACHE.clear();
	}
}
