create database  IF NOT EXISTS prophet;
use prophet;

---数据清洗表，外部表
create external table IF NOT EXISTS easr_clean_data
(project_code string ,project_version string,lib string,ip string,user_id string,event string,url_path string,
device_id string,rt string,time_slot string,track_time string)
PARTITIONED BY(cleanday string)
row format delimited fields terminated by '\t' location '/easr/data/out/cleandata';


---基础数据表
create table IF NOT EXISTS easr_base_info
(project_code string ,project_version string,lib string,ip string,user_id string,event string,url_path string,device_id string,rt string,track_time string)
PARTITIONED BY (pcode string,pday string,timeslot string) 
row format delimited fields terminated by '\t';


----IP明细信息表
create table IF NOT EXISTS easr_ip_info
(pcode string,lib string,ip string)
PARTITIONED BY (pday string,timeslot string) 
row format delimited fields terminated by '\t';


----活跃用户明细表
create table IF NOT EXISTS easr_uv_info
(pcode string,lib string,user_id string)
PARTITIONED BY (pday string,timeslot string) 
row format delimited fields terminated by '\t';

------新增用户表详细表
create table IF NOT EXISTS easr_user_new_info
(pcode string,user_id string,timeslot string)
PARTITIONED BY (pday string) 
row format delimited fields terminated by '\t';

----api调用明细表
create table IF NOT EXISTS easr_api_info
(pcode string,lib string,url_path string,api_count BIGINT,avg_rt BIGINT)
PARTITIONED BY (pday string,timeslot string) 
row format delimited fields terminated by '\t';

----IP统计分析表
create table IF NOT EXISTS easr_statistics_result_ip
(pday string,pcode string,ip_count string)
row format delimited fields terminated by '\t';

----活跃用户数统计分析表
create table IF NOT EXISTS easr_statistics_result_uv
(pday string,pcode string,uv_count string)
row format delimited fields terminated by '\t';

----api调用统计分析表
create table IF NOT EXISTS easr_statistics_result_api
(pday string,pcode string,api_count string)
row format delimited fields terminated by '\t';

----新增用户统计分析表
create table IF NOT EXISTS easr_statistics_result_usernew
(pday string,pcode string,usernew_count string)
row format delimited fields terminated by '\t';

----统计分析表(时间段)
create table IF NOT EXISTS easr_statistics_result_baseall_timeslot
(pday string,pcode string,timeslot string,ip_count string,uv_count string,api_count string,usernew_count string)
row format delimited fields terminated by '\t';