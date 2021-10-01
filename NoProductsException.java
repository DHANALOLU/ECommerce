package com.planon.onlineshop;

public class NoProductsException extends Exception {
	private static final long serialVersionUID = 1L;

	NoProductsException() {

	}

	NoProductsException(String str) {
		super(str);
	}
}