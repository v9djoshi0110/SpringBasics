package com.test.beans;

public class Customer {

	//primitive types
	private int id;
	private String name;
	
	

	public void setId(int id) {
		this.id = id;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDetails() {

		return "This is " + name + " and id is:" + id;
	}

}
