package com.kj.WhatShouldIEatTodayBack.config.security;

import com.kj.WhatShouldIEatTodayBack.service.MemberDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final MemberDetailsServiceImpl memberDetailsService;


    /** 정적 자원에 대해서는 Security 설정을 적용하지 않는다. */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // API로만 통신 할 것이기 때문에 csrf는 비활성화
        http
                .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/api/reservation/**").authenticated()
                .and()
                    .cors()
                .and()
                    .authorizeRequests()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                    .requiresChannel() // HTTPS로 로그인 필요
                    .antMatchers("/api/auth/login").requiresSecure()
                .and()
                .formLogin()
                    .loginPage("/api/auth/login")
                    .permitAll()
                    .defaultSuccessUrl("/api/auth/loginSuccess", true)
                    .passwordParameter("memberPw")
                    .usernameParameter("memberEmail")
//                    .successHandler(new SimpleUrlAuthenticationSuccessHandler())
                    .failureHandler(new SimpleUrlAuthenticationFailureHandler()) // 로그인 실패시 401 http Status 반환
                .and()
                .logout()
                    .logoutUrl("/api/auth/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/api/auth/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(memberDetailsService);
        return provider;
    }

    // CORS 설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://whatishouldeat.com"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setMaxAge(3600l);
        configuration.setAllowedHeaders(Arrays.asList("Origin, X-Requested-With, Content-Type, Accept, Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
