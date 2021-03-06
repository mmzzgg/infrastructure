package com.guns21.authorization.boot.config;

import com.guns21.authentication.boot.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * WebSecurity.IgnoredRequestConfigurer 直接就忽略，不对url追加spring security各种filter,不进行鉴权
 * http.authorizeRequests().antMatchers("/api/**").permitAll(); 会追加spring security各种filter，不进行鉴权
 */
@Configuration
@EnableWebSecurity
@Order(101)
public class AuthorizationPermitConfig extends WebSecurityConfigurerAdapter {
    @Value("${com.guns21.security.permit.authorize-any:false}")
    private boolean permitAny;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private SpringSessionBackedSessionRegistry springSessionBackedSessionRegistry;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Override
    public void init(WebSecurity web) throws Exception {
        if (permitAny) {
            super.init(web);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();


        //同一个账户多次登录限制，对url访问进行监控
        http
                .sessionManagement()
                .maximumSessions(securityConfig.getMaximumSessions())
//                .maxSessionsPreventsLogin(true) 为true是多次登录时抛出异常
                .sessionRegistry(springSessionBackedSessionRegistry)
                //被登录时，第一次返回的错误信息
                .expiredSessionStrategy(sessionInformationExpiredStrategy);
    }

}
