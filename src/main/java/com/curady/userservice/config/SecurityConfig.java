package com.curady.userservice.config;

import com.curady.userservice.config.accessDeniedHandler.AccessDeniedHandlerImpl;
import com.curady.userservice.config.authenticationEntryPoint.AuthenticationEntryPointImpl;
import com.curady.userservice.config.jwt.JwtAuthenticationFilter;
import com.curady.userservice.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/user/info").authenticated()
                .anyRequest().permitAll()

                .and()
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl())
                .and()
                .exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

