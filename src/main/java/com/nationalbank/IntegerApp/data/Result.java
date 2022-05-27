package com.nationalbank.IntegerApp.data;

import java.io.Serializable;

public class Result implements Serializable{

	private static final long serialVersionUID = 1L;
	private String error;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
