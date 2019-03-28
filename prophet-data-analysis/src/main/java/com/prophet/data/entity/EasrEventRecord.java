package com.prophet.data.entity;

import java.util.Date;

public class EasrEventRecord {
    private Long id;

    private String distinctId;
    
    private String distinctName;

    private String type;

    private String event;

    private Date trackTime;

    private Date clientTrackTime;

    private String ip;

    private String userAgent;

    private Byte lib;

    private String libMethod;

    private String libVersion;

    private Byte projectTeancyCode;
    
    private String projectCode;

    private String projectModelCode;

    private String projectModelVersion;

    private String userDefinedProperties;

    private int screenHeight;

    private int screenWidth;

    private String os;

    private String osVersion;

    private String browser;

    private String browserVersion;

    private String deviceId;

    private String url;

    private String urlPath;

    private String pageTitle;
    
    private String sessionId;
    
    private String rt;

    private String referrer;

    private String referrerHost;

    private Byte isFirstDay;

    private Byte isFirstTime;

    private String latestReferrer;

    private String latestReferrerHost;

    private String utmSource;

    private String utmMedium;

    private String utmCampaign;

    private String utmTerm;

    private String latestUtmSource;

    private String latestUtmMedium;

    private String latestUtmCampaign;

    private String latestUtmTerm;

    private Date firstVisitTime;

    private String firstReferrer;

    private String firstReferrerHost;

    private String firstBrowserLanguage;

    private String firstBrowserCharset;

    private String firstSearchKeyword;

    private Date createTime;

    private String utmContent;

    private String latestUtmContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
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

    public Date getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }

    public Date getClientTrackTime() {
        return clientTrackTime;
    }

    public void setClientTrackTime(Date clientTrackTime) {
        this.clientTrackTime = clientTrackTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Byte getLib() {
        return lib;
    }

    public void setLib(Byte lib) {
        this.lib = lib;
    }
    
    public void setLib(String lib) {
    	if("JS".equals(lib)) {
    		 this.lib = 1;
    	}else {
    		 this.lib = -1;
    	}
       
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

    public Byte getProjectTeancyCode() {
        return projectTeancyCode;
    }

    public void setProjectTeancyCode(Byte projectTeancyCode) {
        this.projectTeancyCode = projectTeancyCode;
    }
    
    public void setProjectTeancyCode(String projectTeancyCode) {
        
   /*     if(AppConstant.TENANCY_JINGANG_CODE.equals(projectTeancyCode.toUpperCase())){
        	this.projectTeancyCode = 0;
        }else if(AppConstant.TENANCY_LCYP_CODE.equals(projectTeancyCode.toUpperCase())) {
        	this.projectTeancyCode = 1;
        }else if(AppConstant.TENANCY_PANHAI_CODE.equals(projectTeancyCode.toUpperCase())) {
        	this.projectTeancyCode = 2;
        }else if(AppConstant.TENANCY_365TRAS_CODE.equals(projectTeancyCode.toUpperCase())) {
        	this.projectTeancyCode = 3;
        }else if(AppConstant.TENANCY_JINZHOUPORT_CODE.equals(projectTeancyCode.toUpperCase())) {
        	this.projectTeancyCode = 4;
        }else if(AppConstant.TENANCY_LGTRADE_CODE.equals(projectTeancyCode.toUpperCase())) {
        	this.projectTeancyCode = 5;
        }else {
        	this.projectTeancyCode = -1;
        }*/
    }

    

    public String getProjectModelCode() {
        return projectModelCode;
    }

    public void setProjectModelCode(String projectModelCode) {
        this.projectModelCode = projectModelCode;
    }
    

    public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectModelVersion() {
        return projectModelVersion;
    }

    public void setProjectModelVersion(String projectModelVersion) {
        this.projectModelVersion = projectModelVersion;
    }

    public String getUserDefinedProperties() {
        return userDefinedProperties;
    }

    public void setUserDefinedProperties(String userDefinedProperties) {
        this.userDefinedProperties = userDefinedProperties;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    
    public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getReferrerHost() {
        return referrerHost;
    }

    public void setReferrerHost(String referrerHost) {
        this.referrerHost = referrerHost;
    }

    public Byte getIsFirstDay() {
        return isFirstDay;
    }

    public void setIsFirstDay(Byte isFirstDay) {
        this.isFirstDay = isFirstDay;
    }
    
    public void setIsFirstDay(String isFirstDay) {
    	if("true".equals(isFirstDay)) {
    		this.isFirstDay = 1;
    	}else {
    		this.isFirstDay = 0;
    	}
    }

    public Byte getIsFirstTime() {
        return isFirstTime;
    }

    public void setIsFirstTime(Byte isFirstTime) {
        this.isFirstTime = isFirstTime;
    }
    
    public void setIsFirstTime(String isFirstTime) {
    	if("true".equals(isFirstTime)) {
    		this.isFirstTime = 1;
    	}else {
    		this.isFirstTime = 0;
    	}    
    }

    public String getLatestReferrer() {
        return latestReferrer;
    }

    public void setLatestReferrer(String latestReferrer) {
        this.latestReferrer = latestReferrer;
    }

    public String getLatestReferrerHost() {
        return latestReferrerHost;
    }

    public void setLatestReferrerHost(String latestReferrerHost) {
        this.latestReferrerHost = latestReferrerHost;
    }

    public String getUtmSource() {
        return utmSource;
    }

    public void setUtmSource(String utmSource) {
        this.utmSource = utmSource;
    }

    public String getUtmMedium() {
        return utmMedium;
    }

    public void setUtmMedium(String utmMedium) {
        this.utmMedium = utmMedium;
    }

    public String getUtmCampaign() {
        return utmCampaign;
    }

    public void setUtmCampaign(String utmCampaign) {
        this.utmCampaign = utmCampaign;
    }

    public String getUtmTerm() {
        return utmTerm;
    }

    public void setUtmTerm(String utmTerm) {
        this.utmTerm = utmTerm;
    }

    public String getLatestUtmSource() {
        return latestUtmSource;
    }

    public void setLatestUtmSource(String latestUtmSource) {
        this.latestUtmSource = latestUtmSource;
    }

    public String getLatestUtmMedium() {
        return latestUtmMedium;
    }

    public void setLatestUtmMedium(String latestUtmMedium) {
        this.latestUtmMedium = latestUtmMedium;
    }

    public String getLatestUtmCampaign() {
        return latestUtmCampaign;
    }

    public void setLatestUtmCampaign(String latestUtmCampaign) {
        this.latestUtmCampaign = latestUtmCampaign;
    }

    public String getLatestUtmTerm() {
        return latestUtmTerm;
    }

    public void setLatestUtmTerm(String latestUtmTerm) {
        this.latestUtmTerm = latestUtmTerm;
    }

    public Date getFirstVisitTime() {
        return firstVisitTime;
    }

    public void setFirstVisitTime(Date firstVisitTime) {
        this.firstVisitTime = firstVisitTime;
    }

    public String getFirstReferrer() {
        return firstReferrer;
    }

    public void setFirstReferrer(String firstReferrer) {
        this.firstReferrer = firstReferrer;
    }

    public String getFirstReferrerHost() {
        return firstReferrerHost;
    }

    public void setFirstReferrerHost(String firstReferrerHost) {
        this.firstReferrerHost = firstReferrerHost;
    }

    public String getFirstBrowserLanguage() {
        return firstBrowserLanguage;
    }

    public void setFirstBrowserLanguage(String firstBrowserLanguage) {
        this.firstBrowserLanguage = firstBrowserLanguage;
    }

    public String getFirstBrowserCharset() {
        return firstBrowserCharset;
    }

    public void setFirstBrowserCharset(String firstBrowserCharset) {
        this.firstBrowserCharset = firstBrowserCharset;
    }

    public String getFirstSearchKeyword() {
        return firstSearchKeyword;
    }

    public void setFirstSearchKeyword(String firstSearchKeyword) {
        this.firstSearchKeyword = firstSearchKeyword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUtmContent() {
        return utmContent;
    }

    public void setUtmContent(String utmContent) {
        this.utmContent = utmContent;
    }

    public String getLatestUtmContent() {
        return latestUtmContent;
    }

    public void setLatestUtmContent(String latestUtmContent) {
        this.latestUtmContent = latestUtmContent;
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