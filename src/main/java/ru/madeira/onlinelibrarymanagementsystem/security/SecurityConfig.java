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
        http.csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/users/**", "/genres/**").hasAuthority("ADMIN")
                .antMatchers("/readers/**", "/books/{bookId}/addBookCopies", "/books/{bookId}/getAllCopies", "/books/{bookId}/addBookCopies").hasAnyAuthority("LIBRARIAN", "ADMIN")
                .antMatchers("/login", "/books/updateBooksInLibrary").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/books")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied");
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
