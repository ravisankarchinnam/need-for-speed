package com.github.ravisankarchinnam.speed;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public abstract class MyAppBaseTest
{

    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    protected MockMvc mockMvc;
    protected Authentication auth;


    /**
     * setup
     */
    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
        GrantedAuthority grantedAuthority = new GrantedAuthority()
        {
            private static final long serialVersionUID = -1L;


            @Override
            public String getAuthority()
            {
                return "User";
            }
        };
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(grantedAuthority);
        auth = new UsernamePasswordAuthenticationToken("driver01", "driver01pw", authorities);
    }
}
