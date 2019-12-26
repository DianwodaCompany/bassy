package com.dianwoda.test.bassy.service.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 */
@Configuration
@MapperScan(basePackages = BassyDataSourceConfiguration.PACKAGE, sqlSessionFactoryRef = BassyDataSourceConfiguration.SQL_SESSION_FACTORY)
public class BassyDataSourceConfiguration {
    static final String PACKAGE = "com.dianwoda.test.bassy.db.dao";
    static final String typeAliasesPackage = "com.dianwoda.test.bassy.db.entity";

    @Autowired
    private Environment env;

    public static final String DATASOURCE = "bassyDatasource";

    public static final String SQL_SESSION_FACTORY = "bassySessionFactory";

    public static final String SQL_SESSION_TEMPLATE = "bassySqlSessionTemplate";

    @Primary
    @Bean(name = BassyDataSourceConfiguration.DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.bassy")
    public DataSource initDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = BassyDataSourceConfiguration.SQL_SESSION_FACTORY)
    public SqlSessionFactory initSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(initDataSource());

        /**
         * mybatis 分页插件
         * @return
         */
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("reasonable","true");
        pageHelper.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        // 设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }


    @Primary
    @Bean(name = BassyDataSourceConfiguration.SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate initSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(initSqlSessionFactory());
        return template;
    }
}
