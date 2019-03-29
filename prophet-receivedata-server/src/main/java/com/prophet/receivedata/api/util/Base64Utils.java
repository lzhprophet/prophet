package com.prophet.receivedata.api.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {
	
	private String b64 ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	
	
	public String decoder(String str)  {
		String result = "";
		try {			
			byte[] asBytes = Base64.getDecoder().decode(str);  
			result = new String(asBytes, "utf-8");		
		} catch (UnsupportedEncodingException e) {
			
		}
		return result;
	}
}
