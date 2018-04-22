package com.yangst.cloud.demo.config.datasource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.yangst.cloud.demo.config.properties.datasource.DataSourceEnvConfig;
import com.yangst.cloud.demo.config.properties.datasource.DataSourceMasterConfig;
import com.yangst.cloud.demo.config.properties.datasource.DataSourceSlaveConfig;
import com.yangst.cloud.demo.common.shardingrule.ModuloDatabaseShardingAlgorithm;
import com.yangst.cloud.demo.common.shardingrule.ModuloTableShardingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;


/**
 * 我们没有在配置文件里指明数据库的DataSource，就需要在java代码里来配置DataSource。普通情况不分库时，
 * 只需要在getDataSource方法直接返回createDataSource方法就行了，里面指定了使用druidDataSource。 
 * 现在分库了，我们就要用Sharding JDBC封装的DataSource了，由它来接管数据库连接。 
 * 也就是DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule); 
 * 可以看到，Sharding JDBC封装的DataSource主要是需要构造一个shardingRule参数。 
 * 这个类也主要就是构造这个规则，注释里面写的比较清晰了。差不多流程就是创建个Map
 * @author YST
 *
 */
@Configuration
@EnableConfigurationProperties({DataSourceMasterConfig.class,DataSourceSlaveConfig.class,DataSourceEnvConfig.class})
public class DataSourceInfoConfig {
		@Autowired
		private DataSourceMasterConfig dataSourceMasterConfig;
		
		@Autowired
		private DataSourceSlaveConfig dataSourceSlaveConfig;
		
		@Autowired
		private DataSourceEnvConfig dataSourceEnvConfig;
		
		
		@Bean(name = "masterDataSource")
		@RefreshScope
		@Primary
//		@ConfigurationProperties(prefix = "master.spring.datasource")
		@Qualifier("masterDataSource")
	    public DataSource getMasterDataSource() {
	        return buildMasterDataSource();
	    }
	 	
	 	@Bean(name = "slaveDataSource")
	 	@RefreshScope
		@Qualifier("slaveDataSource")
	    public DataSource getSlaveDataSource() {
	        return buildSlaveDataSource();
	    }
	 	

	 	/**
	 	 * 主数据源创建
	 	 * @return
	 	 */
	    private DataSource buildMasterDataSource() {
	        //设置分库映射
	        Map<String, DataSource> dataMasterSourceMap = new HashMap<>(2);
	        //添加两个数据库ds_0,ds_1到map里
	        dataMasterSourceMap.put("ds_0", createMasterDataSource("ds_0"));
	        dataMasterSourceMap.put("ds_1", createMasterDataSource("ds_1"));
	        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
	        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
	        DataSourceRule dataMasterSourceRule = new DataSourceRule(dataMasterSourceMap, "ds_0");
	        //设置分表映射，将t_order_0和t_order_1两个实际的表映射到t_order逻辑表
	        //0和1两个表是真实的表，t_order是个虚拟不存在的表，只是供使用。如查询所有数据就是select * from t_order就能查完0和1表的
	        TableRule orderTableRule = TableRule.builder("t_order")
	                .actualTables(Arrays.asList("t_order_0", "t_order_1"))
	                .dataSourceRule(dataMasterSourceRule)
	                .build();

	        //具体分库分表策略，按什么规则来分
	        ShardingRule shardingRule = ShardingRule.builder()
	                .dataSourceRule(dataMasterSourceRule)
	                .tableRules(Arrays.asList(orderTableRule))
//	                //ModuloDatabaseShardingAlgorithm和ModuloTableShardingAlgorithm来分别指定库和表的分流策略。
	                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
	                .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm()))
	                .build();

	        DataSource dataMasterSource = null;
			try {
				dataMasterSource = ShardingDataSourceFactory.createDataSource(shardingRule);
//				dataMasterSource = new ShardingDataSource(shardingRule);//ShardingDataSourceFactory.createDataSource(shardingRule);
			} catch (SQLException e) {
				System.out.println("buildMasterDataSource,sharding创建master数据源异常:"+ e.getMessage());
				//GwsLogger.error("buildMasterDataSource,sharding创建master数据源异常", e.getMessage());
			}

	        return dataMasterSource;
	    }
	    
	    /**
	 	 * 读数据源创建
	 	 * @return
	 	 */
	    private DataSource buildSlaveDataSource() {
	        //设置分库映射
	        Map<String, DataSource> dataSlaveSourceMap = new HashMap<>(2);
	        //添加两个数据库ds_0,ds_1到map里
			dataSlaveSourceMap.put("s_ds_0", createSlaveDataSource("ds_0"));
			dataSlaveSourceMap.put("s_ds_1", createSlaveDataSource("ds_1"));
	        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
	        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
	        DataSourceRule dataSlaveSourceRule = new DataSourceRule(dataSlaveSourceMap, "s_ds_0");
	        //设置分表映射，将t_order_0和t_order_1两个实际的表映射到t_order逻辑表
	        //0和1两个表是真实的表，t_order是个虚拟不存在的表，只是供使用。如查询所有数据就是select * from t_order就能查完0和1表的
	        TableRule orderTableRule = TableRule.builder("t_order")
	                .actualTables(Arrays.asList("t_order_0", "t_order_1"))
	                .dataSourceRule(dataSlaveSourceRule)
	                .build();

	        //具体分库分表策略，按什么规则来分
	        ShardingRule shardingRule = ShardingRule.builder()
	                .dataSourceRule(dataSlaveSourceRule)
	                .tableRules(Arrays.asList(orderTableRule))
//	                //ModuloDatabaseShardingAlgorithm和ModuloTableShardingAlgorithm来分别指定库和表的分流策略。
	                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
	                .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm()))
	                .build();

	        DataSource dataSlaveSource = null;
			try {
				dataSlaveSource = ShardingDataSourceFactory.createDataSource(shardingRule);
//				dataSlaveSource = new ShardingDataSource(shardingRule);
			} catch (SQLException e) {
				System.out.println("buildSlaveDataSource,sharding创建slave数据源异常:"+ e.getMessage());
				//GwsLogger.error("buildSlaveDataSource,sharding创建slave数据源异常", e.getMessage());
			}

	        return dataSlaveSource;
	    }
	    
	    /**
	     * 主库
	     * @param dataSourceName
	     * @return
	     */
	    private DataSource createMasterDataSource(final String dataSourceName) {
	        //使用druid连接数据库
	    	// druid连接池
			DruidDataSource masterDruidDataSource = new DruidDataSource();
	    	 masterDruidDataSource.setUrl(String.format(dataSourceMasterConfig.getUrl(), dataSourceName));  
	         masterDruidDataSource.setUsername(dataSourceMasterConfig.getUsername());  
	         masterDruidDataSource.setPassword(dataSourceMasterConfig.getPassword());  
	         masterDruidDataSource.setDriverClassName(dataSourceEnvConfig.getDriverClassName()); 
	       //configuration  
	         masterDruidDataSource.setInitialSize(dataSourceEnvConfig.getInitialSize());  
	         masterDruidDataSource.setMinIdle(dataSourceEnvConfig.getMinIdle());  
	         masterDruidDataSource.setMaxActive(dataSourceEnvConfig.getMaxActive());  
	         masterDruidDataSource.setMaxWait(dataSourceEnvConfig.getMaxWait());  
	         masterDruidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceEnvConfig.getTimeBetweenEvictionRunsMillis());  
	         masterDruidDataSource.setMinEvictableIdleTimeMillis(dataSourceEnvConfig.getMinEvictableIdleTimeMillis());  
	         masterDruidDataSource.setValidationQuery(dataSourceEnvConfig.getValidationQuery());  
	         masterDruidDataSource.setTestWhileIdle(dataSourceEnvConfig.getTestWhileIdle());  
	         masterDruidDataSource.setTestOnBorrow(dataSourceEnvConfig.getTestOnBorrow());  
	         masterDruidDataSource.setTestOnReturn(dataSourceEnvConfig.getTestOnReturn());  
	         masterDruidDataSource.setPoolPreparedStatements(dataSourceEnvConfig.getPoolPreparedStatements());  
	         masterDruidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceEnvConfig.getMaxPoolPreparedStatementPerConnectionSize());  
	         try {  
	             masterDruidDataSource.setFilters(dataSourceEnvConfig.getFilters());  
	         } catch (SQLException e) {  
	         	//GwsLogger.error("createMasterDataSource,master druid configuration initialization filter", e);
	         }  
	         masterDruidDataSource.setConnectionProperties(dataSourceEnvConfig.getConnectionProperties());
	         
	         //GwsLogger.info("createMasterDataSource,master主库数据源初始化成功。");
	 		System.out.println("createMasterDataSource,master datasource init success.");
	        return masterDruidDataSource;
	    }
	    
	    /**
	     * 从库
	     * @param dataSourceName
	     * @return
	     */
	    private DataSource createSlaveDataSource(final String dataSourceName) {
	        //使用druid连接数据库
	    	// druid连接池
	    	DruidDataSource slaveDruidDataSource = new DruidDataSource();  
	    	
	    	 slaveDruidDataSource.setUrl(String.format(dataSourceSlaveConfig.getUrl(), dataSourceName));
	         slaveDruidDataSource.setUsername(dataSourceSlaveConfig.getUsername());
	         slaveDruidDataSource.setPassword(dataSourceSlaveConfig.getPassword());
	         slaveDruidDataSource.setDriverClassName(dataSourceEnvConfig.getDriverClassName()); 
	        
	      //configuration  
	         slaveDruidDataSource.setInitialSize(dataSourceEnvConfig.getInitialSize());  
	         slaveDruidDataSource.setMinIdle(dataSourceEnvConfig.getMinIdle());  
	         slaveDruidDataSource.setMaxActive(dataSourceEnvConfig.getMaxActive());  
	         slaveDruidDataSource.setMaxWait(dataSourceEnvConfig.getMaxWait());  
	         slaveDruidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceEnvConfig.getTimeBetweenEvictionRunsMillis());  
	         slaveDruidDataSource.setMinEvictableIdleTimeMillis(dataSourceEnvConfig.getMinEvictableIdleTimeMillis());  
	         slaveDruidDataSource.setValidationQuery(dataSourceEnvConfig.getValidationQuery());  
	         slaveDruidDataSource.setTestWhileIdle(dataSourceEnvConfig.getTestWhileIdle());  
	         slaveDruidDataSource.setTestOnBorrow(dataSourceEnvConfig.getTestOnBorrow());  
	         slaveDruidDataSource.setTestOnReturn(dataSourceEnvConfig.getTestOnReturn());  
	         slaveDruidDataSource.setPoolPreparedStatements(dataSourceEnvConfig.getPoolPreparedStatements());  
	         slaveDruidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceEnvConfig.getMaxPoolPreparedStatementPerConnectionSize());   
	         try {  
	             slaveDruidDataSource.setFilters(dataSourceEnvConfig.getFilters());  
	         } catch (SQLException e) {  
	         	//GwsLogger.error("createSlaveDataSource,slave druid configuration initialization filter", e);
	         }  
	         slaveDruidDataSource.setConnectionProperties(dataSourceEnvConfig.getConnectionProperties());
	        
			//GwsLogger.info("createSlaveDataSource,slave从主库数据源初始化成功。");
			System.out.println("createSlaveDataSource,slave datasource init success.");
	        
	        return slaveDruidDataSource;
	    }
}
