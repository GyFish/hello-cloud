package com.gy.cloud.security.config;

import com.gy.cloud.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.gy.cloud.security.handler.CustomAccessDeniedHandler;
import com.gy.cloud.security.service.CustomUserDetailsService;
import com.gy.cloud.security.vote.RoleBasedVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
            .accessDecisionManager(accessDecisionManager())
            .antMatchers("/").permitAll()
            .anyRequest().denyAll();

    http.formLogin();

    http.logout()
            .permitAll();

    http.csrf().ignoringAntMatchers("/**");

    http.exceptionHandling()
            .accessDeniedHandler(new CustomAccessDeniedHandler())
            .authenticationEntryPoint(new CustomAccessDeniedHandler());

    http.addFilterAt(
            new CustomUsernamePasswordAuthenticationFilter(authenticationManager()),
            UsernamePasswordAuthenticationFilter.class
    );
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    // 用户
    auth
            .inMemoryAuthentication()
              .passwordEncoder(new BCryptPasswordEncoder())
              .withUser("admin")
              .password(new BCryptPasswordEncoder().encode("123"))
              .roles("USER");

    // userDetailsService
    auth
            .userDetailsService(new CustomUserDetailsService());

    // Jdbc
    auth.jdbcAuthentication()
            .dataSource(dataSource())
            .usersByUsernameQuery("SELECT user_name, password, enabled FROM `sys_user` WHERE user_name = ?")
            .authoritiesByUsernameQuery("SELECT user_name, role_code FROM `sys_user_role` WHERE user_name = ?")
            .groupAuthoritiesByUsername("SELECT g.id, g.group_name, gr.role_code FROM sys_group g, sys_group_user gu, sys_group_role gr WHERE gu.user_name = ? AND g.id = gr.group_id AND g.id = gu.group_id");
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public AccessDecisionManager accessDecisionManager() {

    List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(
//            new WebExpressionVoter(),
            new RoleBasedVoter()
    );
    return new UnanimousBased(decisionVoters);
  }


}