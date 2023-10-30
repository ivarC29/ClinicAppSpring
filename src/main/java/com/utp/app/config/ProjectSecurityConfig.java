package com.utp.app.config;

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

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {
	
	@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/signup", "/icon/**", "/images/**", "/bootstrap5/**", "/sweetalert/**", "/fontawesome6/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/reservation/**").hasAnyRole("RECEPTIONIST", "ADMIN")
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

}
