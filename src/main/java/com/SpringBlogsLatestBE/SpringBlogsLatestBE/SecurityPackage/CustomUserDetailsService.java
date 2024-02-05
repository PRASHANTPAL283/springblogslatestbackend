package com.SpringBlogsLatestBE.SpringBlogsLatestBE.SecurityPackage;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.UserDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    public UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userModel=Optional.ofNullable(this.userDao.findByUsername(username));

        if(userModel.isPresent()){
            return new CustomUserDetails(userModel.get());

        }
        else{
            throw new UsernameNotFoundException("user not found with username "+username);

        }

    }
}
