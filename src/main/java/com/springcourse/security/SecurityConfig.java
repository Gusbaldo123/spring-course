package com.springcourse.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springcourse.domain.enums.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    PasswordEncoder passwordEncoder() {
	    return new CustomPasswordEncoder();
	}
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(csrf -> csrf.disable())
		        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

		        .authorizeHttpRequests(
		                auth -> auth.requestMatchers("/users/login").permitAll().requestMatchers("/users/register")
		                        .permitAll()

		                        .requestMatchers("/users/**")
		                        .hasAnyRole(Role.ADMINISTRATOR.toString(), Role.SIMPLE.toString())
		                        .requestMatchers("/requests/**")
		                        .hasAnyRole(Role.ADMINISTRATOR.toString(), Role.SIMPLE.toString())
		                        .requestMatchers("/request-stages/**")
		                        .hasAnyRole(Role.ADMINISTRATOR.toString(), Role.SIMPLE.toString())

		                        .anyRequest().denyAll()
		        );

		return http.build();
	}
    
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
