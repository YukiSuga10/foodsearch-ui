package com.yuksuga.foodsearchui.dao;

public class DuplicateKeyException extends RuntimeException{

    public DuplicateKeyException(String message) {super(message);}

    public DuplicateKeyException(String message, Throwable cause) {super(message, cause);}
}
