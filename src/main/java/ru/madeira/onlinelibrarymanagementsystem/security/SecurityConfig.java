package ru.madeira.onlinelibrarymanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.madeira.onlinelibrarymanagementsystem.service.UserAuthenticationService;

@Configuration
public class SecurityConfig {

    private UserAuthenticationService authenticationService;

    @Autowired
    public void setUserService(UserAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").authenticated()
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/readers/**").hasAnyRole("LIBRARIAN", "ADMIN")
                .and()
                .formLogin()
                .defaultSuccessUrl("/books")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied");
//                .logout().logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(authenticationService);
        return authenticationProvider;
    }
}
