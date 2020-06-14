package com.tascigorkem.restaurantservice.domain.exception;

import org.springframework.util.StringUtils;

public class DuplicateFoundException extends RuntimeException{

    public DuplicateFoundException(String resourceType, String keyName, String keyValue) {
        super(StringUtils.capitalize(resourceType) + " with " + keyName + "[" + keyValue + "] already exist.");
    }
}
