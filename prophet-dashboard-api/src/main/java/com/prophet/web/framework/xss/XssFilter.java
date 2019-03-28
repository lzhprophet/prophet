package com.prophet.web.framework.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <P>过滤器</P>
 * @author lvzhh
 */
public class XssFilter implements Filter{
	
	 	
	 	@Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        //nothing to do
	    }
	 	
	 	@Override
	    public void destroy() {
	 		//nothing to do
	    }
	 	
	 	@Override
	    public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {
	    	
	        chain.doFilter(new XssHttpServletRequestWrapper(
	                (HttpServletRequest) request), response);
	    }
	

}
