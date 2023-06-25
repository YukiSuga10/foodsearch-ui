package com.yuksuga.foodsearchui.configuration;

import com.yuksuga.foodsearchui.security.CustomAuthenticationSuccessHandler;
import com.yuksuga.foodsearchui.security.SecurityConfigAuthenticationFailureHandler;
import com.yuksuga.foodsearchui.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String TOP_PAGE_URL = "/food/search";

    public static final String LOGIN_URL = "/login";

    public static final String ERROR_URL = "/error/*+";

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/css/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(login -> login
                .loginProcessingUrl(LOGIN_URL)
                .loginPage("/login")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureHandler(new SecurityConfigAuthenticationFailureHandler())
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl(TOP_PAGE_URL)
                .deleteCookies("JSESSIONUD")
        ).authorizeHttpRequests(autz -> autz
                .requestMatchers("/webjars/**", "/food","/img/**", "/css/**", "/actuator/**").permitAll()
                .requestMatchers(TOP_PAGE_URL, ERROR_URL, "/user/add/**", "/user/completed/**", "/plan/detail", LOGIN_URL, "/cert/**").permitAll()
                .requestMatchers("/plan/**", "/home/expired").hasRole("USER")
                .requestMatchers("/user/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        );

        return http.build();
    }

    @Autowired
    @Lazy
    public void configureGlobal(
            AuthenticationManagerBuilder auth,
            UserDetailServiceImpl userDetailsService,
            PasswordEncoder passwordEncoder) throws Exception {
        auth.eraseCredentials(true).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
