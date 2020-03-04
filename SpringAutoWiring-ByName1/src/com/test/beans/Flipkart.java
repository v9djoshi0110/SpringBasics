package com.test.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Flipkart {

	@Autowired
	@Qualifier("cust")
	private Customer cust;

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public String getAccountInfo() {
		
		return cust.getDetails();
	}
}
