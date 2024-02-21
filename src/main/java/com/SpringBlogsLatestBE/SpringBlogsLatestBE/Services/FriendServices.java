package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.FriendsDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.FriendsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendServices {
    @Autowired
    public FriendsDao friendsDao;

    public ResponseEntity<List<FriendsEntity>> getallfriendsByUserId(int id){
        List<FriendsEntity> friendsEntities=this.friendsDao.findAll();
        List<FriendsEntity> result=new ArrayList<>();
        result=friendsEntities.stream().filter(e->e.getMyuserId()==id).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public List<FriendsEntity> getallfriends(int userid){
        List<FriendsEntity> friendsEntities=this.friendsDao.findAll();
        List<FriendsEntity> result=new ArrayList<>();
        result=friendsEntities.stream().filter(e->e.getMyuserId()==userid).collect(Collectors.toList());
        return result;

    }

    public 
}
