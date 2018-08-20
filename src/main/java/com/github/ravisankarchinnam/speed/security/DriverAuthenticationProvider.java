package com.github.ravisankarchinnam.speed.security;

import java.util.ArrayList;
import java.util.List;

import com.github.ravisankarchinnam.speed.service.driver.DriverService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.github.ravisankarchinnam.speed.model.DriverDO;

/**
 * Implements AuthenticationProvider to manage authentication for Driver.
 * <p/>
 */
@Component
public class DriverAuthenticationProvider implements AuthenticationProvider
{

    private static final String WRONG_CREDENTIALS = "Invalid user/password combination.";
    private final DriverService driverService;


    /**
     * @param driverService
     */
    @Autowired
    public DriverAuthenticationProvider(final DriverService driverService)
    {
        this.driverService = driverService;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.authentication.AuthenticationProvider#
     * authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication authentication)
    {
        final String username = authentication.getName();
        final String pwd = (String) authentication.getCredentials();
        DriverDO driver = authenticate(username, pwd);
        final List<GrantedAuthority> authorities = getUserRoles(driver);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, pwd, authorities);
        token.setDetails(driver);
        return token;
    }


    /**
     * Assign default role for all driver.
     * @param driver
     * @return List<GrantedAuthority>
     */
    private List<GrantedAuthority> getUserRoles(DriverDO driver)
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new GrantedAuthority()
        {
            private static final long serialVersionUID = 1L;


            @Override
            public String getAuthority()
            {
                return "User";
            }
        };
        authorities.add(grantedAuthority);
        return authorities;
    }


    /**
     * fetch Driver by entered username and password.
     * @param username
     * @param pwd
     * @return DriverDO
     */
    private DriverDO authenticate(String username, String pwd)
    {
        checkICredentials(username, pwd);
        DriverDO driver = driverService.findByUsername(username);
        if (null == driver)
            throw new BadCredentialsException(WRONG_CREDENTIALS);
        if (!pwd.equals(driver.getPassword()))
            throw new BadCredentialsException(WRONG_CREDENTIALS);
        return driver;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.authentication.AuthenticationProvider#supports(
     * java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> authentication)
    {
        return true;
    }


    /**
     * validate entered username and password is not empty.
     * @param username
     * @param password
     */
    private void checkICredentials(String username, String password)
    {
        if (StringUtils.isBlank(username))
            throw new UsernameNotFoundException("Username is required.");
        if (StringUtils.isBlank(password))
            throw new BadCredentialsException("Password is required.");
    }

}
