package com.prophet.data.etl.transform;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EasrDataHiveUtilsRepository {
	
	@Autowired
	@Qualifier("hiveDruidTemplate")
	private JdbcTemplate hiveDruidTemplate;
	
	
	public List<String> listAllTables() throws SQLException {
		String sql = "show tables";
		List<String> tablesList = hiveDruidTemplate.queryForList(sql, String.class);
		return tablesList;
	}

	public boolean setDynamicPartition() {
		boolean result = false;
		String sqlDynamicPartition ="set hive.exec.dynamic.partition=true";
		String sqlDynamicPartitionMode ="set hive.exec.dynamic.partition.mode=nonstrict";

		
		hiveDruidTemplate.execute(sqlDynamicPartition);
		hiveDruidTemplate.execute(sqlDynamicPartitionMode);
		
		return result;
	}
}
