package com.duo.common.response;

public enum RespCodes {

	SUCCESS("200"),
	NORIGHT("401"),//401   （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。 
	OUTTIME("408"),//408   （请求超时）  服务器等候请求时发生超时。 
	BUSY("503"),//503   （服务不可用） 服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态。 
	FAIL("404");

	
	public final String value;

	private RespCodes(String value) {
		this.value = value;
	}

}
