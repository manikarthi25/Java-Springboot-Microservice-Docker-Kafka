package com.manimart.springsecurity.config;

import com.manimart.springsecurity.filter.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // tell spring container, this is config class like that
@EnableWebSecurity // don't go with default FilterChain, use customize FilterChain
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
                .csrf(csrfCustomizer -> csrfCustomizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/users/login", "/users/add").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

       /*
       HTTP (Hypertext Transfer Protocol) is a stateless protocol,
       meaning it does not retain any information about previous interactions between the client and server.
       Each request from a client to a server is treated as an independent transaction,
       unrelated to any previous request.

       //httpSecurity.formLogin(Customizer.withDefaults()); - If not remove in above, again and again request go to login form

        //disable CSRF token if not disable, new CSRF token will create for every request
        httpSecurity.csrf(csrfCustomizer -> csrfCustomizer.disable());
        //All request should be authenticated
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        //Need to show default login form in Browser - localhost:8080/login
        httpSecurity.formLogin(Customizer.withDefaults());
        //Don't send source code of login form in postman when we hit localhost:8080
        httpSecurity.httpBasic(Customizer.withDefaults());
        //Make a request as stateless and create new token for every request
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //return SecurityFilterChain object
        return httpSecurity.build();
     */
    }

    //AuthenticationManager will talk with AuthenticationProvider
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    //use customize provider, won't use existing provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));//same technic using when we verify the password,  when we encode password before ingestion
        provider.setUserDetailsService(userDetailsService); // use customize userDetailsService, not existing one
        return provider;
    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        //Configure username, password and roles here with help of UserDetailsService, won't use username and password which is in application.properties
        UserDetails userDetails1 = User.withDefaultPasswordEncoder()
                .username("kutti")
                .password("mani")
                .roles("USER")
                .build();

        UserDetails userDetails2 = User.withDefaultPasswordEncoder()
                .username("mahi")
                .password("mohi")
                .roles("ADMIN")
                .build();
         Directly we can't create object for UserDetailsService, because this is interface
           We create object for this class InMemoryUserDetailsManager,
           this class InMemoryUserDetailsManager implements interface UserDetailsService indirectly

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }
     */
}
