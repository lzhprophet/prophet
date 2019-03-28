package com.prophet.data.etl.transform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EasrDataTransformRepository {
	
	@Autowired
	@Qualifier("hiveDruidTemplate")
	private JdbcTemplate hiveDruidTemplate;
	
	
	public void extractCleanData(String day) {
		
		String sqlDropPartition = "ALTER TABLE easr_clean_data DROP IF EXISTS PARTITION (cleanday='"+day+"')";
		
		StringBuilder sql =new StringBuilder("ALTER TABLE easr_clean_data ADD PARTITION (cleanday='") ;
		sql.append(day);
		sql.append("') ");
		sql.append("location '/easr/data/out/cleandata/");
		sql.append(day).append("'");
	
		hiveDruidTemplate.execute(sqlDropPartition);
		hiveDruidTemplate.execute(sql.toString());
		
	}

	
	
	/**
	 * 
	 * @return 抽取基础数据
	 */
	public int daysBaseDataTransform(String day) {
		StringBuilder  sql = new StringBuilder();
		sql.append("insert overwrite table easr_base_info PARTITION (pcode,pday,timeslot) ");
		sql.append("select project_code,project_version,lib,ip,user_id,event,url_path,device_id,rt,access_mode,track_time,");
		sql.append("project_code as pcode ,cleanday as pday,time_slot as timeslot  from easr_clean_data ");
		sql.append("where to_date(track_time) = '");
		sql.append(day);
		sql.append("'");
		
		int rows = hiveDruidTemplate.update(sql.toString());
		return rows;
	}


	/**
	 * 
	 * @return 抽取IP详细数据
	 */
	public void daysIpInfoTransform(String day) {
		StringBuilder  sql = new StringBuilder();
		sql.append("insert overwrite table easr_ip_info PARTITION (pday,timeslot) ");  
		sql.append("select a.pcode,a.lib,a.ip,a.pday as pday,a.timeslot as timeslot from ");
		sql.append("(select pday,timeslot,pcode,ip,lib from easr_base_info group by pcode,pday,timeslot,ip,lib) a ");  
		sql.append("where pday = '");
		sql.append(day);
		sql.append("'");
		
		hiveDruidTemplate.update(sql.toString());
	}
	
	/**
	 * 
	 * @return 抽取Uv详细数据
	 */
	public void daysUvInfoTransform(String day) {
		
		StringBuilder  sql = new StringBuilder();
		sql.append("insert overwrite table easr_uv_info PARTITION (pday,timeslot) ");  
		sql.append("select pcode,access_mode,trim(user_id),pday,timeslot from easr_base_info where user_id != 'null' and ");
		sql.append(" pday = '");
		sql.append(day);
		sql.append("'");
		sql.append("group by pcode,pday,timeslot,user_id,access_mode");
	
		
		hiveDruidTemplate.update(sql.toString());
	}
	
	/**
	 * 
	 * @return 抽取Uv详细数据
	 */
	public void daysApiInfoTransform(String day) {

		StringBuilder  sql = new StringBuilder();
		sql.append("insert overwrite table easr_api_info PARTITION (pday,timeslot) ");  
		sql.append("select pcode,access_mode,url_path,count(1) as api_count,floor(avg(rt)) as avg_rt ,pday,timeslot from easr_base_info where pday='");
		sql.append(day);
		sql.append("' and event='ajax'");
		sql.append("group by pcode,pday,timeslot,access_mode,url_path");
	
		
		hiveDruidTemplate.update(sql.toString());
	}
	/**
	 * 
	 * @return 抽取新增客户详细数据
	 */
	public void daysNewUserInfoTransform(String day) {
		
		
		StringBuilder  sql = new StringBuilder();
		sql.append("insert overwrite table easr_user_new_info PARTITION (pday)   ");  
		sql.append("select a.pcode,a.user_id,a.timeslot, '");
		sql.append(day).append("' as pday from ");
		sql.append("(select pcode,user_id,min(timeslot) as timeslot from easr_uv_info where pday='");
		sql.append(day).append("' group by pcode,user_id ) a ");
		sql.append("left  join easr_user_new_info  usernew  on usernew.user_id = a.user_id  and usernew.pcode = a.pcode "); 
		sql.append("where  usernew.user_id is NULL");
			
		
		hiveDruidTemplate.update(sql.toString());
	}
	
	
	

	
	

}
