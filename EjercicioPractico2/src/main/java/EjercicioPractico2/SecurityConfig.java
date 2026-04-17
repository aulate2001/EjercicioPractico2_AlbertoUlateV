/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author alber
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.authorizeHttpRequests(auth -> auth
                // públicas
                .requestMatchers(
                        "/",
                        "/login",
                        "/registro/**",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**"
                ).permitAll()
                // ADMIN 
                .requestMatchers(
                        "/usuario/**",
                        "/rol/**"
                ).hasRole("ADMIN")
                // ADMIN + ORGANIZADOR
                .requestMatchers(
                        "/evento/guardar",
                        "/evento/eliminar/**",
                        "/evento/modificar/**",
                        "/evento/nuevo"
                ).hasAnyRole("ADMIN", "ORGANIZADOR")
                // Todos autenticados
                .requestMatchers("/evento/**")
                .authenticated()
                .anyRequest()
                .authenticated()
        );

        http.formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        http.exceptionHandling(ex -> ex
                .accessDeniedPage("/acceso_denegado")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configurerGlobal(
            AuthenticationManagerBuilder auth,
            @Lazy UserDetailsService userDetailsService,
            @Lazy PasswordEncoder passwordEncoder
    ) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
