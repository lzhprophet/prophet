package com.prophet.data.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

/**
 * <P>
 * Mybatis配置
 * </P>
 * 
 * @author lvzhh
 */
@Configuration
@MapperScan(basePackages = "com.prophet.data.etl.*.mapper",sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlConfig {
	

	@Value("${mybatis.mapper-locations}")
	private String mapperLocation = "classpath:mapper/master/*.xml";

	
	@Value("${mysql.datasource.url}")
	private String url;

	@Value("${mysql.datasource.username}")
	private String user;

	@Value("${mysql.datasource.password}")
	private String password;

	@Value("${mysql.datasource.driver-class-name}")
	private String driverClass;

	@Bean(name = "mysqlDataSource")
	@Primary
	public DataSource mysqlDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean(name = "mysqlTransactionManager")
	@Primary
	public DataSourceTransactionManager mysqlTransactionManager() {
		return new DataSourceTransactionManager(mysqlDataSource());
	}

	@Bean(name = "mysqlSqlSessionFactory")
	@Primary
	public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(mysqlDataSource);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(this.mapperLocation));
		return sessionFactory.getObject();
	}

	@Bean
	public PageHelper pageHelper(DataSource dataSource) {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}

}
