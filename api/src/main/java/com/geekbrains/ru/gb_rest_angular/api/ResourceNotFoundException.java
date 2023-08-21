package com.geekbrains.ru.gb_rest_angular.api;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
