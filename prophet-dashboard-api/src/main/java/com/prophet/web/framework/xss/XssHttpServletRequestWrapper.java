package com.prophet.web.framework.xss;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;




/**
 * <P>xss攻击和sql攻击处理</P>
 * @author lvzhh
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{
	
	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> rMap = super.getParameterMap();
		Map<String, String[]> gMap = new HashMap<>();
		String[] srcValues;
		String key;
		for (@SuppressWarnings("rawtypes") Map.Entry entry : rMap.entrySet()) {
			key = this.getStringValue(entry.getKey());

			srcValues = rMap.get(key);
			String[] dstValue = new String[srcValues.length];
			for (int i = 0; i < srcValues.length; i++) {
				dstValue[i] = this.cleanXSS(this
						.getStringValue(srcValues[i]));
			}

			gMap.put(key, dstValue);
		}
		return gMap;
	}

	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}
	
	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return new String[0];
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}


	private String cleanXSS(String value) {
		value = HtmlUtils.htmlEscape(value);
		value = this.javaScriptEscape(value);
		value = this.transSqlKey(value);
		return value;
	}
	private String transSqlKey(String str){//%| ;|
		String badStr = "*|--|+|'|and|exec|execute|chr|mid|master" +
	    		"|truncate|char|declare|sitename|net user|xp_cmdshell|like|table" +
	    		"|from|grant|group_concat|column_name|information_schema.columns|" +
	    		"table_schema|union|where|order|by| or |use";//过滤掉的sql关键字
		String cornStr = "*|--|+|/|'";
		String []injStrArray = badStr.split("\\|");
		for (int i=0 ; i<injStrArray.length; i++ ){
			if(str.toLowerCase().indexOf(injStrArray[i])>=0){
				str = this.dealDetail(badStr, cornStr, injStrArray, i);
			}
		}
		return str;
	}
	
	private String dealDetail(String str,String cornStr,String[] injStrArray,int i){
		if(cornStr.contains(injStrArray[i])){
			log.info("出现非法字符{}",injStrArray[i]);
			if("--".equals(injStrArray[i])){
				str = str.replaceAll("[--]", stringToAscii("-"));
			}else{						
				str = str.replaceAll("["+injStrArray[i]+"]", this.stringToAscii(injStrArray[i]));
			}
		}else{
			Pattern p = Pattern.compile("\\s(?i)("+injStrArray[i]+")\\s|^(?i)("+injStrArray[i]+")\\s|\\s(?i)("+injStrArray[i]+")$");
			Matcher m = p.matcher(str); 
			while (m.find()) {
				   log.info("出现非法字符{}",injStrArray[i]);
				   String cStr = m.group(0);
				   Pattern pp = Pattern.compile("(?i)("+injStrArray[i]+")");  
				   Matcher mm = pp.matcher(cStr); 
				   while(mm.find()){
					   str = str.replace(mm.group(0), this.stringToAscii(mm.group(0)));
				   }
		    }  
		}
		return str;
	}
	
	public  String stringToAscii(String value)
	 {
	  StringBuilder sbu = new StringBuilder();
	  char[] chars = value.toCharArray();
	  for (int i = 0; i < chars.length; i++) {
	    sbu.append("&#"+(int)chars[i]+";");
	  }
	  return sbu.toString();
	 }
	
	public  String javaScriptEscape(String input) {
		if (input == null) {
			return input;
		}

		StringBuilder filtered = new StringBuilder(input.length());
		char prevChar = '\u0000';
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			switch(c){
            case '"':
            	filtered.append("\\\"");
                break;
            case '\'':
            	filtered.append("\\'");
                break;
            case '\\':
            	filtered.append("\\\\");
                break;
            case '\t':
            	filtered.append("\t");
                break;
                
            case '\n':
            	if (prevChar != '\r') {
					filtered.append(this.stringToAscii("\n"));
				}
                break;
                
            case '\r':
            	filtered.append(this.stringToAscii("\n"));
                break;
                
            case '\f':
            	filtered.append(this.stringToAscii("\f"));
                break;
                
            case '\b':
            	filtered.append(this.stringToAscii("\b"));
                break;
                
            case '\013':
            	filtered.append(this.stringToAscii("\\v"));
                break;
            case '<':
            	filtered.append("\\u003C");
                break;
            case '>':
            	filtered.append("\\u003E");
                break;
            case '\u2028':
            	filtered.append("\\u2028");
                break;
            case '\u2029':
            	filtered.append("\\u2029");
                break;
            default:
            	filtered.append(c);
            break;
			}
			prevChar = c;

		}
		return filtered.toString();
	}
	
	/**
	 * 处理字符串空格
	 * dsfh 
	 * @param fieldvalue
	 * @return
	 */
	private String getStringValue(Object fieldvalue){
		
		if (fieldvalue == null) {
			return "";
		}
		return fieldvalue.toString().replaceAll("\\s*$", "").trim();
	}

}
	

