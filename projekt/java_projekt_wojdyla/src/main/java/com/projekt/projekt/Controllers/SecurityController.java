package com.projekt.projekt.Controllers;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityController extends WebSecurityConfigurerAdapter{
        
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/panel/**").hasAnyAuthority("Employee", "Administrator", "Boss", "Manager")
            .antMatchers("/panel/administrator/**").hasAnyAuthority("Administrator", "Boss")
            .antMatchers("/panel/manager/**").hasAnyAuthority("Administrator", "Boss", "Manager")
            .antMatchers("/klient/**").hasAnyAuthority("Employee", "Administrator", "Boss", "Manager", "User")
            .anyRequest().permitAll()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and()
            .csrf().disable();
    }
    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select login, hasło, 'true' from użytkownik where login = ? ")
            .authoritiesByUsernameQuery("select login, typ_konta, 'true' from użytkownik where login = ?");
    } 
}
