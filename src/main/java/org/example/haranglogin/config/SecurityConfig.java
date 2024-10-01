package org.example.haranglogin.config;

import org.example.haranglogin.jwt.JwtFilter;
import org.example.haranglogin.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.example.haranglogin.user.HanbatUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final HanbatUserRepository hanbatUserRepository;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf((csrf) -> csrf.disable());
//        http
//            .csrf(csrf -> csrf
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers(new AntPathRequestMatcher("/**")).permitAll());
        http.addFilterBefore(new JwtFilter(jwtUtil, hanbatUserRepository), ExceptionTranslationFilter.class);

        return http.build();
    }
}
