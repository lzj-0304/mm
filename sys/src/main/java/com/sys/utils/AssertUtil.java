package com.sys.utils;


import com.sys.exceptions.ParamException;

public class AssertUtil {

    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamException(msg);
        }
    }

    public static  void isTrue(Boolean flag,String msg,Integer code){
        if(flag){
            throw new ParamException(code,msg);
        }
    }

}
