package com.prophet.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventInfo {
	@JsonProperty("distinct_id")
	private String distinctId ;
	
	@JsonProperty("distinct_name")
	private String distinctName ;

	@JsonProperty("session_id")
	private String sessionId;

	private String ip;
	private String type;
	private String event;
	
	private String userAgent;
	
	@JsonProperty("time")
	private long useClientTrackTime;

	
	private PropertiesInfo properties;
	
	private LibInfo lib;
	
	private ProjectInfo project;
	
	private long trackTime=System.currentTimeMillis();  
	
	

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public PropertiesInfo getProperties() {
		return properties;
	}
	public void setProperties(PropertiesInfo properties) {
		this.properties = properties;
	}
	public LibInfo getLib() {
		return lib;
	}
	public void setLib(LibInfo lib) {
		this.lib = lib;
	}
	public ProjectInfo getProject() {
		return project;
	}
	public void setProject(ProjectInfo project) {
		this.project = project;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getDistinctId() {
		return distinctId;
	}
	public void setDistinctId(String distinctId) {
		this.distinctId = distinctId;
	}
	
	public long getTrackTime() {
		return trackTime;
	}
	public void setTrackTime(long trackTime) {
		this.trackTime = trackTime;
	}
	public long getUseClientTrackTime() {
		return useClientTrackTime;
	}
	public void setUseClientTrackTime(long useClientTrackTime) {
		this.useClientTrackTime = useClientTrackTime;
	}
	public String getDistinctName() {
		return distinctName;
	}
	public void setDistinctName(String distinctName) {
		this.distinctName = distinctName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
