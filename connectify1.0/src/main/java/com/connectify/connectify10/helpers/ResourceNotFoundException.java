package com.connectify.connectify10.helpers;

public class ResourceNotFoundException extends RuntimeException 
{
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(){
        super("Resource Not Found");
    }


}
