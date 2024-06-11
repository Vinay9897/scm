package com.scm.scm.config;

import com.scm.scm.serviceimpl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private SecurityCustomUserDetailService userDetailsService;
    private OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    @Autowired
    public SecurityConfig(SecurityCustomUserDetailService userDetailsService, OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.oAuthAuthenticationSuccessHandler = oAuthAuthenticationSuccessHandler;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // URL Configuration
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);


        // Form login configuration
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/authenticate")
                    .successForwardUrl("/user/dashboard")
                    .failureForwardUrl("/login?error=true");
        });

        // Logout configuration
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        // oauth configuration

        httpSecurity.oauth2Login(oauth ->{
            oauth.loginPage("/login");
            oauth.successHandler(oAuthAuthenticationSuccessHandler);
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
