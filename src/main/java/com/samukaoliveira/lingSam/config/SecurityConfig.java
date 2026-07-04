package com.samukaoliveira.lingSam.config;

import com.samukaoliveira.lingSam.models.Usuario;
import com.samukaoliveira.lingSam.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioRepository repository;

    @Value("${app.user.username}")
    private String username;

    @Value("${app.user.password}")
    private String password;

    @Bean
    CommandLineRunner init(){

        return args -> {

            if(repository.findByUsername(username).isEmpty()){

                Usuario usuario = Usuario.builder()
                        .username(username)
                        .password(passwordEncoder().encode(password))
                        .enabled(true)
                        .build();

                repository.save(usuario);

            }

        };

    }

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }

}
