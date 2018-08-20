package com.github.ravisankarchinnam.speed.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Class extends WebSecurityConfigurerAdapter to customize security.
 * <p/>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    private final DriverAuthenticationProvider driverAuthenticationProvider;


    /**
     * @param driverAuthenticationProvider
     */
    @Autowired
    public WebSecurityConfig(final DriverAuthenticationProvider driverAuthenticationProvider)
    {
        this.driverAuthenticationProvider = driverAuthenticationProvider;
    }


    /**
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(driverAuthenticationProvider);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configuration.
     * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
     * annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.headers().frameOptions().disable();
        http
            .httpBasic().and().csrf().disable().authorizeRequests()
            .antMatchers("/v1/**").authenticated()
            .anyRequest().permitAll();
    }

}
