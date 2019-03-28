package com.prophet.data.etl.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 基础统计信息(IP,UV,PV,新增客户,活跃用户数等)
 * @author lvzhh
 */

@Repository
public class BaseStatisticsRepository {

	@Autowired
	@Qualifier("hiveDruidTemplate")
	private JdbcTemplate hiveDruidTemplate;
	
	
	public void ipStatisticsByday(String day) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into table easr_statistics_result_ip ");
		sql.append("select a.pday,a.pcode,count(*) as ip_count  from ( ");
		sql.append("select pday,pcode,count(*) as ip_count from easr_ip_info where pday = '");
		sql.append(day).append("'");
		sql.append(" group by pday,pcode,ip");
		sql.append(") a group by a.pday,a.pcode");

		
		hiveDruidTemplate.update(sql.toString());
	}
	
	
	public void uvStatisticsByday(String day) {
		
		StringBuilder sql = new StringBuilder();		
				
		sql.append("insert into table easr_statistics_result_uv  ");
		sql.append("select a.pday,a.pcode,count(*) as uv_count  from (");
		sql.append("select  pday,pcode,count(*)  from easr_uv_info where pday='");
		sql.append(day);
		sql.append("' group by pday,pcode,user_id");
		sql.append(") a group by a.pday,a.pcode");
		
		hiveDruidTemplate.update(sql.toString());
	}
	

	public void apiStatisticsByday(String day) {
		
		StringBuilder sql = new StringBuilder();		

		sql.append("insert into table easr_statistics_result_api  ");
		sql.append("select a.pday,a.pcode,sum(api_timeslot_count) as api_count  from (");
		sql.append("select pday,pcode,sum(api_count) as api_timeslot_count from easr_api_info where pday='");
		sql.append(day);
		sql.append("' group by pcode,pday,url_path");
		sql.append(") a group by a.pday,a.pcode");
		
		hiveDruidTemplate.update(sql.toString());
	}


	public void newUserStatisticsByday(String day) {
		
		StringBuilder sql = new StringBuilder();		
		
		sql.append("insert into easr_statistics_result_usernew ");
		sql.append("select pday,pcode,count(1) from easr_user_new_info where pday='");
		sql.append(day);
		sql.append("' group by pcode,pday");
		
		hiveDruidTemplate.update(sql.toString());
	}
	
	public void baseAllStatisticsByTimeslot(String day) {
		
		StringBuilder sql = new StringBuilder();	
		
		sql.append("insert into table easr_statistics_result_baseall_timeslot ");
		sql.append("select a.pday,a.pcode,a.timeslot,nvl(a.ip_count,0) ip_count,nvl(b.uv_count,0) uv_count,");
		sql.append("nvl(c.api_count,0) api_count,nvl(d.user_new_count,0) user_new_count  from ");
		sql.append("(select pday,pcode,timeslot,count(ip) ip_count from easr_ip_info where pday='")
		.append(day).append("' group by pday,pcode,timeslot ) a ");
		sql.append("left join ");
		sql.append("( select pday,pcode,timeslot,count(user_id) uv_count from easr_uv_info where pday='")
		.append(day).append("' group by pday,pcode,timeslot ) b "); 
		sql.append("on a.pcode = b.pcode and  a.timeslot = b.timeslot ");
		sql.append("left join ");
		sql.append("( select pday,pcode,timeslot,sum(api_count) api_count from easr_api_info where pday='")
		.append(day).append("' group by pday,pcode,timeslot ) c ");
		sql.append("on a.pcode = c.pcode and  a.timeslot = c.timeslot ");
		sql.append("left join ");
		sql.append("( select pday,pcode,timeslot,count(user_id) user_new_count from easr_user_new_info where pday='")
		.append(day).append("' group by pday,pcode,timeslot ) d ");
		sql.append("on a.pcode = d.pcode and  a.timeslot = d.timeslot ");
		
		hiveDruidTemplate.update(sql.toString());
	}
		
	 
}
