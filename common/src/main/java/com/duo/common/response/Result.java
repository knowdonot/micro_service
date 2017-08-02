package com.duo.common.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Result<T> implements Serializable {
	
	private boolean success;
	
	private T result;
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public Result(){}
	public Result(String message){this.message = message;}
	public Result(T result){
		this.success = true;
		this.result = result;
	}
	@Override
	public String toString() {
		return "{\"success\":\""+ success + "\",\"message\":\"" + message+"\"}";
	}
	public Result(boolean success,T result,String message){
		this.success = success;
		this.result = result;
		this.message = message;
	}

	
	public Result(boolean success){
		this.success = success;
	}
	public Result(boolean success,T result){
		this.success = success;
		this.result = result;
	}
	
}
