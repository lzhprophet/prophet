
package com.prophet.receivedata.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;


/**
 * <P>filter过滤后的参数反解码</P>
 * 
 */
public final class StringFilter {
	
	private static final Pattern S_PATTERN= Pattern.compile("\\&#(\\d+)\\;");
	
	private StringFilter(){
		
	}
	
	public static String getStringValue(Object fieldvalue) {
		if (fieldvalue == null) {
			return "";
		}
		return fieldvalue.toString().replaceAll("\\s*$", "").trim();
	}

	/**
	 * 将字符串中的ascii转换为字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String asciiToString(String str) {
		String newStr = str;
		
		Matcher mm = S_PATTERN.matcher(newStr);
		while (mm.find()) {
			String group = mm.group(0);
			String ff = group.replace("&#", "");
			ff = ff.replace(";", "");
			newStr = str.replace(group, String.valueOf((char) Integer.parseInt(ff)));
		}
		return newStr;
	}

	/**
	 * 全局解码(解过滤器的转码)
	 */
	public static String decodingString(String str) {
		String newStr = str;
		if (newStr == null) {
			return null;
		}
		newStr = HtmlUtils.htmlUnescape(newStr);
		newStr = StringEscapeUtils.unescapeEcmaScript(newStr);
		newStr = asciiToString(newStr);
		return newStr;
	}
}
