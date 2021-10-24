package by.scooter.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@ComponentScan("by.scooter")
@EnableTransactionManagement
@RequiredArgsConstructor
public class ContextConfiguration {

    private final DataSource dataSource;

//    @Bean
//    public SpringLiquibase liquibase() { //FIXME
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("/changelog/db.changelog-master.xml");
//        liquibase.setDataSource(dataSource);
//        return liquibase;
//    }
}
