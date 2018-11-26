package com.lgcns.tutorial;

public class FruitInfo {
	private String index;
	private String name;
	private String message;
	
	// dummy constructor
	public FruitInfo(){
	}
	
	public FruitInfo(String index, String name, String message){
		this.index = index;
		this.name = name;
		this.message = message;
	}
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
