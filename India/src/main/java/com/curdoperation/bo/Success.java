package com.curdoperation.bo;

public class Success {
	private String mesage;
	private int statuscode;
	public Success(String mesage,int statuscode){
		this.mesage=mesage;
		this.statuscode=statuscode;
	}
	public String getMesage() {
		return mesage;
	}
	public int getStatuscode() {
		return statuscode;
	}

}
