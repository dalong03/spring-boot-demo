package com.fb.springbootdemo.common.multidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	public static final String DATASOURCE1 = "datasource1";
	public static final String DATASOURCE2 = "datasource2";
	
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}

}
