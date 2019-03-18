package com.surging.entity;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面响应entity
 * 创建者 张志朋
 * 创建时间	2018年3月8日
 */
public class Result extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public Result() {
		put("code", 200);
	}

	public static String error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static String error(String msg) {
		return error(500, msg);
	}

	public static String error(int code, String msg) {
		Result r = new Result();
		r.put("code", code);
		r.put("msg", msg);
		return JSON.toJSONString(r);
	}

	public static String ok(Object msg) {
		Result r = new Result();
		r.put("msg", msg);
		return JSON.toJSONString(r);
	}


	public static String ok(Map<String, Object> map) {
		Result r = new Result();
		r.putAll(map);
		return JSON.toJSONString(r);
	}

	public static String ok() {
		return JSON.toJSONString(new Result());
	}

	@Override
	public String put(String key, Object value) {
		super.put(key, value);
		return JSON.toJSONString(this);
	}

}