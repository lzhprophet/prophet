create database  IF NOT EXISTS easr;
use easr;


select project_code,project_version,lib ,ip ,user_id ,event,time_slot,track_time from easr_clean_data


--动态分区开起
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;


---基础数据表
create table IF NOT EXISTS easr_base_info
(project_code string ,project_version string,lib string,ip string,user_id string,event string,url_path string,device_id string,rt string,track_time string)
PARTITIONED BY (pcode string,pday string,timeslot string) 
row format delimited fields terminated by '\t';

--=======================================明细表==========================================================

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

--=======================================分析表(按时间段)========================================================
----统计分析表(时间段)
create table IF NOT EXISTS easr_statistics_result_baseall_timeslot
(pday string,pcode string,timeslot string,ip_count string,uv_count string,api_count string,usernew_count string)
row format delimited fields terminated by '\t';


insert into table easr_statistics_result_baseall_timeslot
select a.pday,a.pcode,a.timeslot,nvl(a.ip_count,0) ip_count,nvl(b.uv_count,0) uv_count,
nvl(c.api_count,0) api_count,nvl(d.user_new_count,0) usernew_count  from 
(select pday,pcode,timeslot,count(ip) ip_count from easr_ip_info where pday='2018-12-04' group by pday,pcode,timeslot ) a
left join 
( select pday,pcode,timeslot,count(user_id) uv_count from easr_uv_info where pday='2018-12-04' group by pday,pcode,timeslot ) b 
on a.pcode = b.pcode and  a.timeslot = b.timeslot
left join 
( select pday,pcode,timeslot,sum(api_count) api_count from easr_api_info where pday='2018-12-04' group by pday,pcode,timeslot ) c
on a.pcode = c.pcode and  a.timeslot = c.timeslot 
left join 
( select pday,pcode,timeslot,count(user_id) user_new_count from easr_user_new_info where pday='2018-12-04' group by pday,pcode,timeslot ) d
on a.pcode = d.pcode and  a.timeslot = d.timeslot; 


select a.* from easr_statistics_result_baseall_timeslot a

--=======================================分析表(按天)==========================================================

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

