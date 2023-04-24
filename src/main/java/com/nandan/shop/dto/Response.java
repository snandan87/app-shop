package com.nandan.shop.dto;

public class Response {
private String msg;

public Response(String string) {
	this.msg=string;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

}
