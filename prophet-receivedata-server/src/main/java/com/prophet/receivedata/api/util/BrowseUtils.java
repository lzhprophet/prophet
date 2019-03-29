package com.prophet.receivedata.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class BrowseUtils {

	private final static String IE9 = "MSIE 9.0";
	private final static String IE8 = "MSIE 8.0";
	private final static String IE7 = "MSIE 7.0";
	private final static String IE6 = "MSIE 6.0";
	private final static String MAXTHON = "Maxthon";
	private final static String QQ = "QQBrowser";
	private final static String GREEN = "GreenBrowser";
	private final static String SE360 = "360SE";
	private final static String FIREFOX = "Firefox";
	private final static String OPERA = "Opera";
	private final static String CHROME = "Chrome";
	private final static String SAFARI = "Safari";
	private final static String OTHER = "其它";

	public String checkBrowse(String userAgent) {
		if (regex(OPERA, userAgent))
			return OPERA;
		if (regex(CHROME, userAgent))
			return CHROME;
		if (regex(FIREFOX, userAgent))
			return FIREFOX;
		if (regex(SAFARI, userAgent))
			return SAFARI;
		if (regex(SE360, userAgent))
			return SE360;
		if (regex(GREEN, userAgent))
			return GREEN;
		if (regex(QQ, userAgent))
			return QQ;
		if (regex(MAXTHON, userAgent))
			return MAXTHON;
		if (regex(IE9, userAgent))
			return IE9;
		if (regex(IE8, userAgent))
			return IE8;
		if (regex(IE7, userAgent))
			return IE7;
		if (regex(IE6, userAgent))
			return IE6;
		return OTHER;
	}

	public boolean regex(String regex, String str) {
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static void main(String[] args) {
		String ie9 = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)";
		String ie8 = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322)";
		String ie7 = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; .NET CLR 1.1.4322)";
		String ie6 = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)";
		String aoyou = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; Maxthon 2.0)";
		String qq = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322) QQBrowser/6.8.10793.201";
		String green = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; GreenBrowser)";
		String se360 = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; 360SE)";

		String chrome = "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.11 (KHTML, like Gecko) Chrome/9.0.570.0 Safari/534.11";
		String safari = "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN) AppleWebKit/533.17.8 (KHTML, like Gecko) Version/5.0.1 Safari/533.17.8";
		String fireFox = "Mozilla/5.0 (Windows NT 5.2; rv:7.0.1) Gecko/20100101 Firefox/7.0.1";
		String opera = "Opera/9.80  (Windows NT 5.2; U; zh-cn) Presto/2.9.168 Version/11.51";
		String other = "(Windows NT 5.2; U; zh-cn) Presto/2.9.168 Version/11.51";
		BrowseUtils b = new BrowseUtils();
		System.out.println(b.checkBrowse(ie9));
		System.out.println(b.checkBrowse(ie8));
		System.out.println(b.checkBrowse(ie7));
		System.out.println(b.checkBrowse(ie6));
		System.out.println(b.checkBrowse(aoyou));
		System.out.println(b.checkBrowse(qq));
		System.out.println(b.checkBrowse(green));
		System.out.println(b.checkBrowse(se360));
		System.out.println(b.checkBrowse(chrome));
		System.out.println(b.checkBrowse(safari));
		System.out.println(b.checkBrowse(fireFox));
		System.out.println(b.checkBrowse(opera));
		System.out.println(b.checkBrowse(other));
	}

	/**
	 * 获取操作系统,浏览器及浏览器版本信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getOsInfo(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;
		String os = "";

		// =================OS Info=======================
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}

		return os;
	}

	/**
	 * 获取浏览器及浏览器版本信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowserInfo(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;
		String user = userAgent.toLowerCase();
		String browser = "";

		if (user.contains("edge")) {
			browser = (userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("msie")) {
			String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera")) {
				browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			} else if (user.contains("opr")) {
				browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
			}

		} else if (user.contains("chrome")) {
			browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
			browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
		} else {
			browser = "UnKnown, More-Info: " + userAgent;
		}

		return browser;
	}

}