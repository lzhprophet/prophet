create database  IF NOT EXISTS easr;
use easr;

create external table IF NOT EXISTS easr_clean_data
(project_code string ,project_version string,lib string,ip string,user_id string,event string,url_path string,device_id string,track_time string)
PARTITIONED BY(cleanday string)
row format delimited fields terminated by '\t' location '/easr/data/out/cleandata';

--指定每天清洗出的数据的分区位置
ALTER TABLE easr_clean_data ADD PARTITION (cleanday='2018-11-09') location '/easr/data/out/cleandata/2018-11-09';

---基础数据表
create table IF NOT EXISTS easr_base_info
(project_code string ,project_version string,lib string,ip string,user_id string,event string,url_path string,device_id string,track_time string)
PARTITIONED BY (pcode string,pday string) 
row format delimited fields terminated by '\t';

--动态分区开起
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;


---每天将前一天的数据 存到base表中
insert overwrite table easr_base_info PARTITION (pcode,pday)
select project_code,project_version,lib,ip,user_id,event,url_path,device_id,rt,track_time,
project_code as pcode ,cleanday as pday  from easr_clean_data 
where to_date(track_time) = '2018-11-25'

----IP明细信息表
create table IF NOT EXISTS easr_ip_info
(pcode string,lib string,ip string)
PARTITIONED BY (pday string) 
row format delimited fields terminated by '\t';

insert overwrite table easr_ip_info PARTITION (pday)  
select a.pcode,a.ip,a.lib,a.pday as pday from (select pday,pcode,ip,lib from easr_base_info group by pday,pcode,ip,lib) a
where pday = '2018-11-26'


----统计独立IP数
create table IF NOT EXISTS easr_statistics_result_ip
(pday string,pcode string,ip_count string)
row format delimited fields terminated by '\t';

insert into table easr_statistics_result_ip 
select pday,pcode,count(*) as ip_count from easr_ip_info where pday = '2018-11-26'  group by pday,pcode








----活跃用户明细表
create table IF NOT EXISTS easr_uv_info
(pcode string,lib string,user_id string)
PARTITIONED BY (pday string) 
row format delimited fields terminated by '\t';


insert overwrite table easr_uv_info PARTITION (pday)  
select pcode,lib,user_id,pday from easr_base_info where user_id != 'null' and pday='2018-12-02'  group by pcode,user_id,lib,pday;




select pcode,user_id,pday from easr_base_info where user_id != 'null' and pday='2018-11-30'  group by pcode,user_id,pday;

create table IF NOT EXISTS easr_statistics_result_uv
(pday string,pcode string,uv_count string)
row format delimited fields terminated by '\t';

----统计活跃用户数
insert into table easr_statistics_result_uv  
select  pday,pcode,count(*)  from easr_uv_info where pday='2018-11-25'  group by pday,pcode;


select ip.pday,ip.pcode,ip.ip_count,uv.uv_count,api.api_count ,usernew.usernew_count from easr_statistics_result_ip ip
LEFT JOIN easr_statistics_result_uv uv  on ip.pcode = uv.pcode and ip.pday = uv.pday 
LEFT JOIN easr_statistics_result_api api  on ip.pcode = api.pcode and ip.pday = api.pday 
LEFT JOIN easr_statistics_result_usernew usernew  on ip.pcode = usernew.pcode and ip.pday = usernew.pday 
where ip.pday='2018-12-04'



------新增用户表详细表
create table IF NOT EXISTS easr_user_new_info
(pcode string,user_id string,timeslot string)
PARTITIONED BY (pday string) 
row format delimited fields terminated by '\t';

select a.pcode,a.user_id,a.pday from (
select DISTINCT uv.pday ,uv.pcode,trim(uv.user_id) as user_id,usernew.user_id un_user_id  from easr_uv_info uv 
left  join easr_user_new_info  usernew  on usernew.user_id = uv.user_id  where uv.pday='2018-12-05'
) a where a.un_user_id is NULL ;


