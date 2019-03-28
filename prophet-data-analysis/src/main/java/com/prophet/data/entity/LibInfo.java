package com.prophet.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibInfo {
	//{"distinct_id":"160bb0307524e3-0d744380d68662-61131b7e-1764000-160bb030753382",
		//"lib":{"$lib":"js","$lib_method":"code","$lib_version":"1.9.6"},
		//"project":{"$teancy_code":"panhaijingang","$project_code":"phmm","$project_model_code":"web"},
		//"properties":{"$screen_height":1050,"$screen_width":1680,"$lib":"js","$lib_version":"1.9.6",
		//"$device_id":"160bb0307524e3-0d744380d68662-61131b7e-1764000-160bb030753382","$referrer":"","$referrer_host":"",
		//"$url":"http://localhost:8888/","$url_path":"/","$title":"Hello Easr","platForm":"h5","sdfsdf":"sdfdsfs","$latest_referrer":"åå¼å¼å¸¸",
		//"$latest_referrer_host":"åå¼å¼å¸¸","$latest_search_keyword":"åå¼å¼å¸¸","$latest_traffic_source_type":"åå¼å¼å¸¸","$is_first_day":false,
		//"$is_first_time":false},"type":"track","event":"$pageview","_nocache":"014850926105800"}
	@JsonProperty("$lib") 
	private String lib;
	@JsonProperty("$lib_method") 
	private String libMethod;
	@JsonProperty("$lib_version") 
	private String libVersion;
	
	public String getLib() {
		return lib;
	}
	public void setLib(String lib) {
		this.lib = lib;
	}
	public String getLibMethod() {
		return libMethod;
	}
	public void setLibMethod(String libMethod) {
		this.libMethod = libMethod;
	}
	public String getLibVersion() {
		return libVersion;
	}
	public void setLibVersion(String libVersion) {
		this.libVersion = libVersion;
	}
	
	
	
	
}
