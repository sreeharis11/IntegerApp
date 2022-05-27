package com.nationalbank.IntegerApp.data;

import java.util.List;

public class ListResult extends Result{
	private static final long serialVersionUID = 8138253454973633393L;
	private List<Integer> result;
	public List<Integer> getResult() {
		return result;
	}
	public void setResult(List<Integer> result) {
		this.result = result;
	}
	
}
