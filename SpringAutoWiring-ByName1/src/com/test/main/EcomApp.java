package com.test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.beans.Flipkart;

public class EcomApp {

	public static void main(String[] args) {

		//Spring IOC container
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		Flipkart fpk = context.getBean("flipkart", Flipkart.class);
		
		String accountInfo = fpk.getAccountInfo();
		System.out.println(accountInfo);

	}

}
