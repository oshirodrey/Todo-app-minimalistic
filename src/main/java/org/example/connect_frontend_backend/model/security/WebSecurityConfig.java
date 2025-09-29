package org.example.connect_frontend_backend.model.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/registration").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());   // or .httpBasic(Customizer.withDefaults())

        return http.build();
    }
//    SecurityFilterChain securityFilterChain(HttpSecurity http)
//
//    csrf(csrf -> csrf.disable())
//    Disables CSRF protection. Fine for APIs / Postman testing or JWT setups; for classic HTML form apps you normally keep CSRF enabled.
//
//            authorizeHttpRequests(...)
//
//            .requestMatchers("/api/v*/registration/**").permitAll()
//    Anyone (even not logged in) can hit registration endpoints like /api/v1/registration/....
//
//            .anyRequest().authenticated()
//    Every other endpoint requires the user to be logged in.
//
//    formLogin(Customizer.withDefaults())
//    Enables Spring’s default login page and form-login flow. If an unauthenticated user hits a protected URL, they’ll be redirected to /login.
//
//return http.build();
//    Finalizes the filter chain Spring Security uses to protect routes.

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    // Only if you need to inject AuthenticationManager elsewhere
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }
}
