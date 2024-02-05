package com.SpringBlogsLatestBE.SpringBlogsLatestBE.SecurityPackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
                .authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/getadmin","/allusers","/deleteUserById/{id}").hasAuthority("admin")
                                .requestMatchers("/getuser").hasAuthority("user")
                                .requestMatchers("/addBlogs","/deleteBlogById/{id}","/addNewFile","/updateFile/{id}").authenticated()
                                .requestMatchers("/allblogs","/addUser","prodImage/downloadfile/{id}","/allblogs/{id}").permitAll()
                                .anyRequest().authenticated())


                .httpBasic(withDefaults());


        return httpSecurity.build();

    }


}
