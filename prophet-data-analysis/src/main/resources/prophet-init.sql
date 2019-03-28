create database  IF NOT EXISTS prophet;
use prophet;

---数据清洗表，外部表
create external table IF NOT EXISTS easr_clean_data
(project_code string,project_version string,lib string,ip string,user_id string,event string,url_path string,
device_id string,rt string,access_mode string,time_slot string,track_time string)
PARTITIONED BY(cleanday string)
row format delimited fields terminated by '\t' location '/easr/data/out/cleandata';

--指定每天清洗出的数据的分区位置
ALTER TABLE easr_clean_data ADD PARTITION (cleanday='2018-11-09') location '/easr/data/out/cleandata/2018-11-09';
----查询
select project_code,project_version,lib ,ip ,user_id ,event,access_mode,time_slot,track_time from easr_clean_data

--动态分区开起
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;


---基础数据表
create table IF NOT EXISTS easr_base_info
(project_code string ,project_version string,lib string,ip string,user_id string,event string,url_path string,
device_id string,rt string,access_mode string,track_time string)
PARTITIONED BY (pcode string,pday string,timeslot string) 
row format delimited fields terminated by '\t';


---每天将前一天的数据 存到base表中
insert overwrite table easr_base_info PARTITION (pcode,pday,timeslot)
select project_code,project_version,lib,ip,user_id,event,url_path,device_id,rt,track_time,
project_code as pcode ,cleanday as pday, time_slot as timeslot from easr_clean_data 
where to_date(track_time) = '2018-11-25'

----IP明细信息表
create table IF NOT EXISTS easr_ip_info
(pcode string,lib string,ip string)
PARTITIONED BY (pday string,timeslot string) 
row format delimited fields terminated by '\t';

insert overwrite table easr_ip_info PARTITION (pday,timeslot)  
select a.pcode,a.lib,a.ip,a.pday as pday,a.timeslot as timeslot from  
(select pday,timeslot,pcode,ip,lib from easr_base_info group by pcode,pday,timeslot,ip,lib) a 
where pday = '2018-12-05'


----活跃用户明细表
create table IF NOT EXISTS easr_uv_info
(pcode string,access_mode string,user_id string)
PARTITIONED BY (pday string,timeslot string) 
row format delimited fields terminated by '\t';


insert overwrite table easr_uv_info PARTITION (pday,timeslot)  
select pcode,access_mode,trim(user_id),pday,timeslot from easr_base_info where user_id != 'null' and pday='2018-12-05'  group by pcode,pday,timeslot,user_id,access_mode;


------新增用户表详细表
create table IF NOT EXISTS easr_user_new_info
(pcode string,user_id string,timeslot string)
PARTITIONED BY (pday string) 
row format delimited fields terminated by '\t';

insert overwrite table easr_user_new_info PARTITION (pday)  
select a.pcode,a.user_id,a.timeslot, '2018-12-05' as pday from 
(select pcode,user_id,min(timeslot) as timeslot from easr_uv_info where pday='2018-12-05' group by pcode,user_id ) a 
left  join easr_user_new_info  usernew  on usernew.user_id = a.user_id  and usernew.pcode = a.pcode
where  usernew.user_id is NULL ;



----api调用明细表
create table IF NOT EXISTS easr_api_info
(pcode string,access_mode string,url_path string,api_count BIGINT,avg_rt BIGINT)
PARTITIONED BY (pday string,timeslot string) 
row format delimited fields terminated by '\t';


insert overwrite table easr_api_info PARTITION (pday,timeslot)  
select pcode,access_mode,url_path,count(1) as api_count,floor(avg(rt)) as avg_rt ,pday,timeslot from easr_base_info 
where pday='2018-12-05' and event='ajax' 
group by pcode,pday,timeslot,access_mode,url_path;





----IP统计分析表
create table IF NOT EXISTS easr_statistics_result_ip
(pday string,pcode string,ip_count string)
row format delimited fields terminated by '\t';

insert into table easr_statistics_result_ip 
select a.pday,a.pcode,count(*) as ip_count  from (
select pday,pcode,count(*) as ip_count from easr_ip_info where pday = '2018-12-05'  group by pday,pcode,ip
) a group by a.pday,a.pcode;

----活跃用户数统计分析表
create table IF NOT EXISTS easr_statistics_result_uv
(pday string,pcode string,uv_count string)
row format delimited fields terminated by '\t';

insert into table easr_statistics_result_uv  
select a.pday,a.pcode,count(*) as uv_count  from (
select  pday,pcode,count(*)  from easr_uv_info where pday='2018-12-11'  group by pday,pcode,user_id
) a group by a.pday,a.pcode;

----api调用统计分析表
create table IF NOT EXISTS easr_statistics_result_api
(pday string,pcode string,api_count string)
row format delimited fields terminated by '\t';

insert into easr_statistics_result_api
select a.pday,a.pcode,sum(api_timeslot_count) as api_count  from (
select pday,pcode,sum(api_count) as api_timeslot_count from easr_api_info where pday='2018-12-05' group by pcode,pday,url_path
) a group by a.pday,a.pcode;

----新增用户统计分析表
create table IF NOT EXISTS easr_statistics_result_usernew
(pday string,pcode string,usernew_count string)
row format delimited fields terminated by '\t';

insert into easr_statistics_result_usernew
select pday,pcode,count(1) from easr_user_new_info where pday='2018-12-05' group by pcode,pday;


----统计分析表(时间段)
create table IF NOT EXISTS easr_statistics_result_baseall_timeslot
(pday string,pcode string,timeslot string,ip_count string,uv_count string,api_count string,usernew_count string)
row format delimited fields terminated by '\t';

insert into table easr_statistics_result_baseall_timeslot
select a.pday,a.pcode,a.timeslot,nvl(a.ip_count,0) ip_count,nvl(b.uv_count,0) uv_count,
nvl(c.api_count,0) api_count,nvl(d.user_new_count,0) usernew_count  from 
(select pday,pcode,timeslot,count(ip) ip_count from easr_ip_info where pday='2018-12-06' group by pday,pcode,timeslot ) a
left join 
( select pday,pcode,timeslot,count(user_id) uv_count from easr_uv_info where pday='2018-12-06' group by pday,pcode,timeslot ) b 
on a.pcode = b.pcode and  a.timeslot = b.timeslot
left join 
( select pday,pcode,timeslot,sum(api_count) api_count from easr_api_info where pday='2018-12-06' group by pday,pcode,timeslot ) c
on a.pcode = c.pcode and  a.timeslot = c.timeslot 
left join 
( select pday,pcode,timeslot,count(user_id) user_new_count from easr_user_new_info where pday='2018-12-06' group by pday,pcode,timeslot ) d
on a.pcode = d.pcode and  a.timeslot = d.timeslot; 




select a.pcode , a.access_mode,count(a.user_id) accessModeCount , '2018-12-12' as pday from 
(select  pcode , access_mode,user_id,count(user_id) from easr_uv_info where pday='2018-12-12' group by pcode,access_mode,user_id ) a 
group by a.pcode,a.access_mode;
