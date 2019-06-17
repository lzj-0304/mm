package com.sys.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class Md5Util {
	
	public static String  encode(String msg){
		 try {
			MessageDigest messageDigest=MessageDigest.getInstance("md5");
			return Base64.getEncoder().encodeToString(messageDigest.digest(msg.getBytes())) ;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	
	public static void main(String[] args) {
		System.out.println(encode("123456"));
	}

}
