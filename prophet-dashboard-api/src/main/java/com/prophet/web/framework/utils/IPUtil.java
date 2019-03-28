package com.prophet.web.framework.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <P>获取oip工具类</P>
 * @author  lvzhh
 */
public class IPUtil {
	
	private static String DEFAULT = "";

	public static final  String getIpAdd(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					return DEFAULT;
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 如果通过代理访问,可能获取2个IP,这时候去第二个(代理服务端IP)
		if (ipAddress.split(",").length > 1) {
			ipAddress = ipAddress.split(",")[1].trim();
		}
		return ipAddress;
	}
	

	/**
	 * 获取服务器IP
	 * @return
	 */
	public static final String getServerip(){
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			
		}
		return ip;
	}
	
}
