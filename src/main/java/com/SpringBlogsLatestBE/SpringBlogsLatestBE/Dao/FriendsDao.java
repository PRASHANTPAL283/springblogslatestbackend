package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.FriendsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendsDao extends JpaRepository<FriendsEntity,Integer> {

    @Query(value="select count(myuser_id) from mycomputer.friends_model where myuser_id=:userid and user_id=:friendid",nativeQuery = true)
    public int checkiffriendpresent(@Param("userid") int userid, @Param("friendid") int friendid);
}
