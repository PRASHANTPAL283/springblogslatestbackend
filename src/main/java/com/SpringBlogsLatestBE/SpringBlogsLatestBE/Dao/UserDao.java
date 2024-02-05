package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserModel,Integer> {

    public UserModel findByUserId(int id);

   public UserModel findByUsername(String name);
}
