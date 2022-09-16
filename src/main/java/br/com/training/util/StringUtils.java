package br.com.training.util;

public class StringUtils {
    public static boolean isEmpty(String str){
        if(str == null){
            return true;
        }
        return str.trim().length() == 0 ;
    }
}
