package com.prophet.data.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertiesInfo {
	
	
	//存储未知属性
	private Map<String, String> userDefinedProperties = new HashMap<String, String>();  
	//未知属性都为自定义属性
    @JsonAnySetter  
    public void set(String name,String value) {  
    	userDefinedProperties.put(name,value);  
    }
    
   
	
	@JsonProperty("$screen_height")
	private String screenHeight;
	
	@JsonProperty("$screen_width")
	private String screenWidth;
	
	@JsonProperty("$is_first_day")
	private String is_first_day;
	
	@JsonProperty("$is_first_time")
	private String is_first_time;
	
	@JsonProperty("$url")
	private String url;
	
	@JsonProperty("$url_path")
	private String url_path;
	
	@JsonProperty("$title")
	private String title;
	

	
	@JsonProperty("$RT")
	private String rt;
	
	
	
	public String getRt() {
		return rt;
	}
	public void setRt(String rt) {
		this.rt = rt;
	}

	
	@JsonProperty("$referrer")
	private String referrer;
	
	@JsonProperty("$referrer_host")
	private String referrer_host;
	
	
	@JsonProperty("$latest_referrer")
	private String latest_referrer;

	@JsonProperty("$latest_referrer_host")
	private String latest_referrer_host;
	
	@JsonProperty("$latest_utm_source")
	private String latest_utm_source;
	
	@JsonProperty("$latest_utm_medium")
	private String latest_utm_medium;
	
	@JsonProperty("$latest_utm_term")
	private String latest_utm_term;
	
	@JsonProperty("$latest_utm_content")
	private String latest_utm_content;
	
	@JsonProperty("$latest_utm_campaign")
	private String latest_utm_campaign;
	

	@JsonProperty("$utm_source")
	private String utm_source;
	
	@JsonProperty("$utm_medium")
	private String utm_medium;
	
	@JsonProperty("$utm_term")
	private String utm_term;
	
	@JsonProperty("$utm_content")
	private String utm_content;
	
	@JsonProperty("$utm_campaign")
	private String utm_campaign;
	
	@JsonProperty("$first_visit_time")
	private String first_visit_time;	
	
	@JsonProperty("$first_referrer")
	private String first_referrer;	
	
	@JsonProperty("$first_referrer_host")
	private String first_referrer_host;	
	
	@JsonProperty("$first_browser_language")
	private String first_browser_language;	
	
	@JsonProperty("$first_browser_charset")
	private String first_browser_charset;	
	

	@JsonProperty("$first_search_keyword")
	private String first_search_keyword;		    	
	
	@JsonProperty("$latest_search_keyword")
	private String latest_search_keyword;
	
	//第一次流量来源类型  付费广告流量、自然搜索流量、社交网站流量、引荐流量、直接流量
	//TODO 暂时没用
	@JsonProperty("$first_traffic_source_type")
	private String first_traffic_source_type;
	
	//最近一次流量来源类型  付费广告流量、自然搜索流量、社交网站流量、引荐流量、直接流量。
	//TODO 暂时没用
	@JsonProperty("$latest_traffic_source_type")
	private String latest_traffic_source_type;
	
	
	@JsonProperty("$device_id")
	private String device_id;
	
	

	public String getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(String screenHeight) {
		this.screenHeight = screenHeight;
	}
	public String getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}
	public Map<String, String> getUserDefinedProperties() {
		return userDefinedProperties;
	}
	public void setUserDefinedProperties(Map<String, String> userDefinedProperties) {
		this.userDefinedProperties = userDefinedProperties;
	}

	public String getIs_first_day() {
		return is_first_day;
	}

	public void setIs_first_day(String is_first_day) {
		this.is_first_day = is_first_day;
	}

	public String getLatest_referrer() {
		return latest_referrer;
	}

	public void setLatest_referrer(String latest_referrer) {
		this.latest_referrer = latest_referrer;
	}

	public String getLatest_referrer_host() {
		return latest_referrer_host;
	}

	public void setLatest_referrer_host(String latest_referrer_host) {
		this.latest_referrer_host = latest_referrer_host;
	}

	public String getLatest_utm_source() {
		return latest_utm_source;
	}

	public void setLatest_utm_source(String latest_utm_source) {
		this.latest_utm_source = latest_utm_source;
	}

	public String getLatest_utm_medium() {
		return latest_utm_medium;
	}

	public void setLatest_utm_medium(String latest_utm_medium) {
		this.latest_utm_medium = latest_utm_medium;
	}

	public String getLatest_utm_term() {
		return latest_utm_term;
	}

	public void setLatest_utm_term(String latest_utm_term) {
		this.latest_utm_term = latest_utm_term;
	}

	public String getLatest_utm_content() {
		return latest_utm_content;
	}

	public void setLatest_utm_content(String latest_utm_content) {
		this.latest_utm_content = latest_utm_content;
	}

	public String getLatest_utm_campaign() {
		return latest_utm_campaign;
	}

	public void setLatest_utm_campaign(String latest_utm_campaign) {
		this.latest_utm_campaign = latest_utm_campaign;
	}

	public String getLatest_search_keyword() {
		return latest_search_keyword;
	}

	public void setLatest_search_keyword(String latest_search_keyword) {
		this.latest_search_keyword = latest_search_keyword;
	}

	public String getLatest_traffic_source_type() {
		return latest_traffic_source_type;
	}

	public void setLatest_traffic_source_type(String latest_traffic_source_type) {
		this.latest_traffic_source_type = latest_traffic_source_type;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getReferrer_host() {
		return referrer_host;
	}

	public void setReferrer_host(String referrer_host) {
		this.referrer_host = referrer_host;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl_path() {
		return url_path;
	}

	public void setUrl_path(String url_path) {
		this.url_path = url_path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIs_first_time() {
		return is_first_time;
	}

	public void setIs_first_time(String is_first_time) {
		this.is_first_time = is_first_time;
	}

	public String getUtm_source() {
		return utm_source;
	}

	public void setUtm_source(String utm_source) {
		this.utm_source = utm_source;
	}

	public String getUtm_medium() {
		return utm_medium;
	}

	public void setUtm_medium(String utm_medium) {
		this.utm_medium = utm_medium;
	}

	public String getUtm_term() {
		return utm_term;
	}

	public void setUtm_term(String utm_term) {
		this.utm_term = utm_term;
	}

	public String getUtm_content() {
		return utm_content;
	}

	public void setUtm_content(String utm_content) {
		this.utm_content = utm_content;
	}

	public String getUtm_campaign() {
		return utm_campaign;
	}

	public void setUtm_campaign(String utm_campaign) {
		this.utm_campaign = utm_campaign;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getFirst_visit_time() {
		return first_visit_time;
	}
	public void setFirst_visit_time(String first_visit_time) {
		this.first_visit_time = first_visit_time;
	}
	public String getFirst_referrer() {
		return first_referrer;
	}
	public void setFirst_referrer(String first_referrer) {
		this.first_referrer = first_referrer;
	}
	public String getFirst_referrer_host() {
		return first_referrer_host;
	}
	public void setFirst_referrer_host(String first_referrer_host) {
		this.first_referrer_host = first_referrer_host;
	}
	public String getFirst_browser_language() {
		return first_browser_language;
	}
	public void setFirst_browser_language(String first_browser_language) {
		this.first_browser_language = first_browser_language;
	}
	public String getFirst_browser_charset() {
		return first_browser_charset;
	}
	public void setFirst_browser_charset(String first_browser_charset) {
		this.first_browser_charset = first_browser_charset;
	}
	public String getFirst_search_keyword() {
		return first_search_keyword;
	}
	public void setFirst_search_keyword(String first_search_keyword) {
		this.first_search_keyword = first_search_keyword;
	}
	public String getFirst_traffic_source_type() {
		return first_traffic_source_type;
	}
	public void setFirst_traffic_source_type(String first_traffic_source_type) {
		this.first_traffic_source_type = first_traffic_source_type;
	}

}
