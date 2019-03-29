package com.prophet.receivedata.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectInfo {
	@JsonProperty("$teancy_code") 
	private String teancyCode;
	@JsonProperty("$project_code") 
	private String projectCode;
	@JsonProperty("$project_model_code") 
	private String projectModelCode;
	@JsonProperty("$project_model_version") 
	private String projectModelVersion;

	
	public String getTeancyCode() {
		return teancyCode;
	}
	public void setTeancyCode(String teancyCode) {
		this.teancyCode = teancyCode;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectModelCode() {
		return projectModelCode;
	}
	public void setProjectModelCode(String projectModelCode) {
		this.projectModelCode = projectModelCode;
	}
	public String getProjectModelVersion() {
		return projectModelVersion;
	}
	public void setProjectModelVersion(String projectModelVersion) {
		this.projectModelVersion = projectModelVersion;
	}
	
	
}
