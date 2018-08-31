package com.sino.newasia.neworder.Configuration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootConfiguration
public class DruidDataSourceConfig {//数据源设置
//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")//是一个合格者标识--表明那个类才是我们需要调用的类
//    @ConfigurationProperties(prefix="spring.datasource.primary")
//    public DataSource primaryDataSource() {
//
//        return DataSourceBuilder.create().build();
//    }

//    @Bean(name = "secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.secondary")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

// way one by setting up in conf file or app.conf file:
//    https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
//                "/druid/*");
//        // IP白名单
//        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
//        // IP黑名单(共同存在时，deny优先于allow)
//        servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
//        // 控制台管理用户
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "admin");
//        // 是否能够重置数据 禁用HTML页面上的“Reset All”功能
//        servletRegistrationBean.addInitParameter("resetEnable", "false");
//        return servletRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return filterRegistrationBean;
//    }
}
