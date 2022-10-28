package br.com.training.util;

import br.com.training.service.exception.ResourceNotFoundException;

public class Validation {
    public static void assertNotEmpty(Object obj){
        if(obj instanceof String){
            String str = (String)obj;
            if(StringUtils.isEmpty(str)){
                throw new ResourceNotFoundException("Objeto não pode estar vazio ou nulo");
            }
        }
        if(obj == null){
            throw new ResourceNotFoundException("Objeto não pode estar vazio ou nulo");
        }
    }


}
