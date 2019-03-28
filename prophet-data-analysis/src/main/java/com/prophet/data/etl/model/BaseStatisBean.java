package com.prophet.data.etl.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString
public class BaseStatisBean {
	
	private String pday;
	private String pcode;
	private String ipCount;
	private String uvCount;
	private String apiCount;
	private String newUserCount;
	private String timeslot;
	
}
