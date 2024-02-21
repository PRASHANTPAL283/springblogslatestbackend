package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.FollowersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollwersDao extends JpaRepository<FollowersModel,Integer> {

    @Query(value="select count(user_id) from mycomputer.follower_tables where myuserid=:userid and user_id=:followid",nativeQuery = true)
    public int checkIffollowalready(@Param("userid") int userid, @Param("followid") int followid);
}
