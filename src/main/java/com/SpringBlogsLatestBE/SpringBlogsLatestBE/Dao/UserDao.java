package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserDao extends JpaRepository<UserModel,Integer> {

    public UserModel findByUserId(int id);

   public UserModel findByUsername(String name);

   @Transactional
    @Modifying
    @Query(value="update mycomputer.myusertable set status=:status,lastseen=:date where user_id=:userid ",nativeQuery = true)
    public void updatelastseenandstatus(@Param("userid") int userid, @Param("date")Date date, @Param("status") String status);



}
