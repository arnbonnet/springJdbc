package main.database;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
public class JdbcConfiguration {

	@Bean(destroyMethod = "close")
	public static DataSource dataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass("org.postgresql.Driver");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/spring");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		dataSource.setIdleConnectionTestPeriodInMinutes(60);
		dataSource.setIdleMaxAgeInMinutes(1);
		dataSource.setMinConnectionsPerPartition(5);
		dataSource.setMaxConnectionsPerPartition(10);
		dataSource.setPartitionCount(1);
		dataSource.setAcquireIncrement(5);
		dataSource.setStatementsCacheSize(1000);
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
