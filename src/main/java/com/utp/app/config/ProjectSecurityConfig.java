package com.utp.app.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {
	
	@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf( (csrf) -> csrf.disable())
        	.cors( (cors) -> cors.configurationSource( corsConfigurationSource() ))
        	.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/signup", "/user/signup", "/icon/**", "/images/**", "/bootstrap5/**", "/sweetalert/**", "/fontawesome6/**","/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**", "/appointment/toList","/doctor/**", "/receptionist/**", "/patient/**", "/user/**", "/recipe/**", "/diagnosis/**").hasAnyRole("ADMIN")
                        .requestMatchers("/reservation/**", "/appointment/**","/doctor/toList", "/patient/toList").hasAnyRole("RECEPTIONIST", "ADMIN")
                        .requestMatchers("/appointment/toList", "/medicine/**", "/medicalRecord/**").hasAnyRole("DOCTOR")
                        .requestMatchers("/appointment/toList", "/medicalRecord/get/**").hasAnyRole("PATIENT")
                        .anyRequest().authenticated()
                ).formLogin(form -> form.loginPage("/login").permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
	
	@Bean
	UserDetailsManager usersCustom(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?");
		users.setAuthoritiesByUsernameQuery(
				"SELECT a.username, b.role_name FROM user_role c " +
				"INNER JOIN users a ON a.user_id = c.user_id " +
				"INNER JOIN roles b ON b.role_id = c.role_id " + 
				"WHERE a.username = ?" 
				);
		return users;
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8082"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


}
