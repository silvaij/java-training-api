package br.com.training.service.exception;

public class ResourceNotFoundException extends RuntimeException {
    
	public ResourceNotFoundException(String message){
        super(message);
    }
}
