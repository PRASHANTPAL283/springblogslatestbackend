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

                .authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/getadmin","/deleteUserById/{id}").hasAuthority("admin")
                                .requestMatchers("/allusers/{id}","/addBlogs","/deleteBlogById/{id}","/updateFile/{id}","/getBlogsByUser","/doLike","/getLikes/{id}","/doComment","/allcomments/{id}","/getallLikesCount/{id}","/deleteCommentById/{id}","/allusers"
                                ,"/addFriend","/allfriends/{id}","/addFollow","/allfollows/{id}","/deleteFriendbyId/{id}","/deleteFollowById/{id}","/getUserbyUsername/{username}"
                                ,"/sendNewMessage","/getallmessages/{sender}/{receiver}").hasAnyAuthority("user","admin")

                                .requestMatchers("/allBlogs","/addUser","prodImage/downloadfile/{id}","/allblogs/{id}","/doLogin","/addNewFile","/checkdebug").permitAll()
                                .anyRequest().authenticated())


                .httpBasic(withDefaults());


        return httpSecurity.build();

    }


}
