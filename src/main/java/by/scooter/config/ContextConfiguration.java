package by.scooter.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@ComponentScan("by.scooter")
@EnableTransactionManagement
public class ContextConfiguration {

    private final DataSource dataSource;

    @Autowired
    public ContextConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("/changelog/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
