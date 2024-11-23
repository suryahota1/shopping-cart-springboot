package com.java_learning.shopping_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class ShoppingCartApplication {

	public static void main(String[] args) {
		RunnableImpl w = new RunnableImpl();
		Thread t1 = new Thread(w);
		t1.start();
		for ( ;;) {
			System.out.println(Thread.currentThread().getName() + " running");
		}

	}

}
