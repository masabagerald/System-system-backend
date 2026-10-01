package com.example.jero.Student.System.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.jero.Student.System.repository.UserRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Password hashing.
     *
     * Passwords must never be stored as plain text.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Loads users from the database, using email as the username.
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> userRepository.findByEmail(email)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .disabled(!user.isEnabled())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Invalid email or password"
                ));
    }

    /**
     * Spring Security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            // Use the CorsConfig mappings so browser preflight requests aren't rejected
            .cors(Customizer.withDefaults())

            .authorizeHttpRequests(auth -> auth
                // Authentication endpoints will be public
                .requestMatchers("/api/auth/**").permitAll()

                // Let error responses through so real errors aren't masked as 403
                .requestMatchers("/error").permitAll()

                // Everything else requires authentication
                .anyRequest().authenticated()
            )

            // Clients authenticate with an "Authorization: Basic" header (email:password)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}