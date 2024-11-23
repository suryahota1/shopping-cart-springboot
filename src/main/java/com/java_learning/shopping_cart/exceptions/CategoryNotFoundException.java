package com.java_learning.shopping_cart.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException( String message ) {
        super(message);
    }
}
