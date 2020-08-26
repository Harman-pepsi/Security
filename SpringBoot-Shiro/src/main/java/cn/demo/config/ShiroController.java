package cn.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Classname ShiroController
 * @Description TODO
 * @Date 2020/8/25 23:54
 * @Created by XJC·AW
 */
@Configuration
public class ShiroController {
    //ShiroFiliterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /*
            anon:无需认证就可以访问
            authc:必须认证了才能访问
            user: 必须拥有 记住我 功能才能访问
            perms:拥有对某个资源的权限才能访问
            role:拥有某个角色权限才能访问
        */
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        //filterMap.put("/user/*","authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        factoryBean.setLoginUrl("/login");
        factoryBean.setUnauthorizedUrl("/noauth");
        return factoryBean;
    }

    //DefaultWebSecurityManager
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建Relam对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //ShiroDialect:用来整合shiro thymeleaf
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
