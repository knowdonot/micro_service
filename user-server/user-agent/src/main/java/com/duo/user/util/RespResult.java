package com.duo.user.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RespResult<T> {

	private String code;
	
	private T result;
	
	private String message;

	private String data;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public RespResult(){}
	
	public RespResult(String code,String message,T obj){
		this.code = code;
		this.message = message;
		this.result = obj;

	}
	
	
	@Override
	public String toString() {
		return "{\"code\":\""+ code + "\",\"message\":\"" + message+ "\",\"data\":\"" + data+"\"}";
	}
	
	static class User{
		private String id ;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public User(){}
		public User(String id,String name){
			this.id = id ;
			this.name = name;
		}
	}
}
