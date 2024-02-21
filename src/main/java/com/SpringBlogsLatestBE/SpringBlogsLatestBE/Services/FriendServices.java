package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.FriendsDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.FriendsEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public Boolean CheckIfFriendAlreadyPresent(int userId, int friendId){

        List<FriendsEntity> list=this.getallfriends(userId);
        Optional<UserModel> user=Optional.ofNullable(list.stream().filter(e->e.getFriendadded().getUserId()==friendId).findFirst().get().getFriendadded());
        if(list.size()==0){
            return false;
        }
        else if(user.isPresent()){
            return true;
        }
        else{
            return false;
        }


    }

    public ResponseEntity<FriendsEntity> addNewFriend(FriendsEntity friendsEntity){
        if(CheckIfFriendAlreadyPresent(friendsEntity.getMyuserId(),friendsEntity.getFriendadded().getUserId())==true){
            throw new GlobalExceptionClass("Friend already added", "500");
        }
        else{
            try {
                FriendsEntity result = this.friendsDao.save(friendsEntity);
                return ResponseEntity.status(HttpStatus.OK).body(result);
            }
            catch (Exception ex){
                throw new GlobalExceptionClass(ex.getMessage(),ex.getCause(),"500");
            }
        }
    }


}
