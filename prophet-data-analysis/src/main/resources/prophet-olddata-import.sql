
---数据迁移
insert overwrite table easr_base_info PARTITION (pcode,pday)  select *,project_code as pcode ,to_date(track_time) as pday  from easr_clean_data;

