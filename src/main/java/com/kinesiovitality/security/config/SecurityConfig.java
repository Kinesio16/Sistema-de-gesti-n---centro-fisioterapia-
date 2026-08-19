package com.kinesiovitality.security.config;

import org.springframework.context.annotation.Bean;  

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import com.kinesiovitality.security.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Value("${FRONTEND_URL:http://localhost:5173}")
	private String frontendUrl;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                	    .requestMatchers("/api/auth/login").permitAll()

                	    .requestMatchers("/swagger-ui/**",
                	                     "/v3/api-docs/**",
                	                     "/swagger-ui.html")
                	        .permitAll()

                	    .requestMatchers("/api/dashboard/**")
                	        .hasRole("ADMIN")

                	    .requestMatchers("/api/usuarios/**")
                	        .hasRole("ADMIN")
                	        
                	        .requestMatchers(HttpMethod.GET, "/api/servicios/activos")
                	        .hasAnyRole("ADMIN", "FISIOTERAPEUTA")

	                	    .requestMatchers(HttpMethod.GET, "/api/fisioterapeutas/activos")
	                	        .hasAnyRole("ADMIN", "FISIOTERAPEUTA")
	
	                	    .requestMatchers(HttpMethod.GET, "/api/sucursales/activas")
	                	        .hasAnyRole("ADMIN", "FISIOTERAPEUTA")

                	    .requestMatchers("/api/servicios/**")
                	        .hasRole("ADMIN")
                	    
                	    .requestMatchers("/api/fisioterapeutas/**")
                	        .hasRole("ADMIN")

                	    .requestMatchers("/api/sucursales/**")
                	        .hasRole("ADMIN")

                	    .requestMatchers("/api/ventas/**")
                	        .hasAnyRole("ADMIN","FISIOTERAPEUTA")

                	    .requestMatchers("/api/pacientes/**")
                	        .hasAnyRole("ADMIN","FISIOTERAPEUTA")

                	    .requestMatchers("/api/citas/**")
                	        .hasAnyRole("ADMIN","FISIOTERAPEUTA")

                	    .requestMatchers("/api/evaluaciones/**")
                	        .hasAnyRole("ADMIN","FISIOTERAPEUTA")

                	    .requestMatchers("/api/tratamientos/**")
                	        .hasAnyRole("ADMIN","FISIOTERAPEUTA")

                	    .requestMatchers("/api/sesiones/**")
                	        .hasAnyRole("ADMIN","FISIOTERAPEUTA")

                	    .anyRequest().authenticated()
                	)
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
        	    frontendUrl,
        	    "http://localhost:5173"
        	));

        configuration.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "PATCH",
                "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}