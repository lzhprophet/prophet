package com.prophet.data.etl.load;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prophet.data.etl.model.BaseStatisBean;

public class HiveBaseStatisRowMapper implements RowMapper<BaseStatisBean> {

	@Override
	public BaseStatisBean mapRow(ResultSet resultSet, int i) throws SQLException {
		// 获取结果集中的数据
		String pday = resultSet.getString("pday");
		String pcode = resultSet.getString("pcode");
		String ipCount = this.isExistColumn(resultSet, "ip_count")?resultSet.getString("ip_count"):"0";
		String uvCount = this.isExistColumn(resultSet, "uv_count")?resultSet.getString("uv_count"):"0";
		String apiCount = this.isExistColumn(resultSet, "api_count")?resultSet.getString("api_count"):"0";
		String newUserCount = this.isExistColumn(resultSet, "usernew_count")?resultSet.getString("usernew_count"):"0";
		String timeslot = this.isExistColumn(resultSet, "timeslot")?resultSet.getString("timeslot"):"";
		// 把数据封装成User对象
		BaseStatisBean bsb = new BaseStatisBean();
		bsb.setPcode(pcode);
		bsb.setPday(pday);
		bsb.setIpCount(ipCount);
		bsb.setApiCount(apiCount);
		bsb.setUvCount(uvCount);
		bsb.setNewUserCount(newUserCount);
		bsb.setTimeslot(timeslot);
		return bsb;
	}
	
	/**
	 * 判断查询结果集中是否存在某列
	 * @param rs 查询结果集
	 * @param columnName 列名
	 * @return true 存在; false 不存咋
	 */
	public boolean isExistColumn(ResultSet rs, String columnName) {
		try {
			if ( rs.findColumn(columnName) > 0 && rs.getString(columnName)  != null) {
				return true;
			} 
		}
		catch (SQLException e) {
			return false;
		}
		
		return false;
	}

}