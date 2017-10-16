package forester.jv.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by FORESTER
 */
@Configuration
@ComponentScan(basePackages = "forester.jv.data")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "forester.jv.data.repository")
@PropertySource("classpath:jdbc.properties")
public class AppConfig {

    @Value("${db.driver}")
    private String dbDriverProperty;
    @Value("${db.url}")
    private String dbUrlProperty;
    @Value("${db.username}")
    private String dbUsernameProperty;
    @Value("${db.password}")
    private String dbPasswordProperty;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriverProperty);
        dataSource.setUrl(dbUrlProperty);
        dataSource.setUsername(dbUsernameProperty);
        dataSource.setPassword(dbPasswordProperty);
        return dataSource;
    }


    @Bean
    public HibernateJpaDialect hibernateJpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(false);
        return vendorAdapter;
    }

    @Bean
    protected LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("forester.jv.data.entity");
        factory.setDataSource(dataSource());
        factory.setJpaDialect(hibernateJpaDialect());
        factory.setPersistenceUnitName("JV");
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        txManager.setPersistenceUnitName(entityManagerFactory().getPersistenceUnitName());
        return txManager;
    }


}
