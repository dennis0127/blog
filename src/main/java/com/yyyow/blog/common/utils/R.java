package com.yyyow.blog.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author lxd
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	// 服务响应正确
	public static final int SUCCESS = 0;
	// 服务相应错误或异常
	public static final int FAILURE = -1;
	//需要登录
	public static final int NEEDLOGIN = -2;


	public R() {
		put("code", SUCCESS);
		put("msg", "success");
	}
	
	public static R error() {
		return error(FAILURE, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(FAILURE, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(int code, String msg, Object data) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data",data);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
