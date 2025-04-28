/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.config;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Oscar Vides
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfig {
    
    @Autowired
    Environment env;
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("sv.global.colas.entities");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
	public PlatformTransactionManager transactionManager() {
            JpaTransactionManager txManager = new JpaTransactionManager();
            txManager.setEntityManagerFactory(entityManagerFactory());
            return txManager;
	}
    
    @Bean
    public javax.sql.DataSource dataSource() {
        return getDataSource();
    }

    final Properties hibernateProperties() {
        final String driver = env.getProperty("global.database.driver");
        return new Properties() {
            private static final long serialVersionUID = 1L;
            {                
                //org.hibernate.dialect.MySQLDialect
                //org.hibernate.dialect.Oracle10gDialect
                //org.hibernate.dialect.SQLServer2012Dialect
               
                if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(driver)){
                    setProperty("hibernate.dialect","org.hibernate.dialect.SQLServer2012Dialect");
                }else if("oracle.jdbc.driver.OracleDriver".equals(driver)){
                    setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
                }else if("com.mysql.cj.jdbc.Driver".equals(driver)){
                    setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                }
                setProperty("hibernate.cache.use_second_level_cache", "false");
                setProperty("hibernate.cache.use_query_cache", "false");
                setProperty("hibernate.cache.provider_class","org.hibernate.cache.EhCacheProvider");
                setProperty("hibernate.cglib.use_reflection_optimizer", "false");
                setProperty("hibernate.connection.release_mode","after_transaction"); // IMPORTANTE PARA QUE LOS TEST NO FALLEN
                setProperty("hibernate.show_sql", "true"); // DEJAR a FALSE, logs en produccion enormes O.o
                setProperty("hibernate.connection.autocommit", "false");
                setProperty("hibernate.hbm2ddl.auto", "none");
            }
        };
    }

    public javax.sql.DataSource getDataSource() {
        String dataSourceName = env.getProperty("global.database.datasource");
        //dataSourceName=null;
        if (dataSourceName != null) {
            System.out.println(dataSourceName);
            try {
                javax.sql.DataSource ds=dataSource_jndi(dataSourceName);
                System.out.println("SE OBTUVO EL DATASOURCE!!!!! " + ds.toString());
                return ds;
            } catch (Exception e) {
                return dataSource_local();
            }
        } else {
            System.out.println("nulo");
            return dataSource_local();
        }
    }
    
    @Bean
	JpaDialect someJpaDialect() {
		return new HibernateJpaDialect();
	}


    private javax.sql.DataSource dataSource_jndi(String dataSourceName) throws Exception {
        Context ctx = new InitialContext();
        javax.sql.DataSource datasource = null;
        try {
            datasource = (javax.sql.DataSource) ctx.lookup("java:/comp/env/" + dataSourceName);
        } catch (Exception e) {
        }
        if (datasource == null) {
            try {
                datasource = (javax.sql.DataSource) ctx.lookup("java:jboss/" + dataSourceName);
            } catch (Exception e) {
            }
        }
        if (datasource == null) {
            datasource = (javax.sql.DataSource) ctx.lookup(dataSourceName);
        }
        
        return datasource;
    }

    private javax.sql.DataSource dataSource_local() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("global.database.driver"));
        dataSource.setUrl(env.getProperty("global.database.url"));
        dataSource.setUsername(env.getProperty("global.database.user"));
        dataSource.setPassword(env.getProperty("global.database.password"));
        System.out.println("************************************************************************>>>>>>>>>>>>>>>>>>>>>>>>>"+dataSource.getUrl()+" "+dataSource.getUsername()+" "+dataSource.getPassword());
        return dataSource;
    }
    
}
