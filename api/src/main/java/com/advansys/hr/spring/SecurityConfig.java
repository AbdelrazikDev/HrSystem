package com.advansys.hr.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.advansys.hr.persistence.provider.CustomDaoAuthenticationProvider;
import com.advansys.hr.security.StatelessAuthenticationFilter;
import com.advansys.hr.security.StatelessTokenAuthenticationFilter;
import com.advansys.hr.security.UnauthorisedEntryPoint;
import com.advansys.hr.service.user.UserService;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = { "com.advansys.hr.service", "com.advansys.hr.security" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGOUT_URL = "/auth/logout";
    private static final String LOGOUT_SUCCESS_URL = "/auth/logout/validate?status=success";

    @Autowired
    private UnauthorisedEntryPoint unauthorisedEntryPoint;

    @Autowired
    private StatelessAuthenticationFilter statelessAuthenticationFilter;

    @Autowired
    private StatelessTokenAuthenticationFilter statelessTokenAuthenticationFilter;

    @Autowired
    private UserService userService;

    // ========= Overrides ===========

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .httpBasic()
                .authenticationEntryPoint(unauthorisedEntryPoint)
                .and()
            .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .logout()
                .logoutUrl(LOGOUT_URL)
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
            .addFilterBefore(statelessAuthenticationFilter, BasicAuthenticationFilter.class)
            .addFilterAfter(statelessTokenAuthenticationFilter, BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }

    // =========== Beans ============

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // =========== Helpers ============

    private AuthenticationProvider getDaoAuthenticationProvider() {
        CustomDaoAuthenticationProvider authenticationProvider = new CustomDaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return authenticationProvider;
    }
}
