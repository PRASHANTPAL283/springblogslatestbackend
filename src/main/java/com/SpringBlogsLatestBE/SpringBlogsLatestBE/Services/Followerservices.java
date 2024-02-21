package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.FollwersDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.FollowersModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Followerservices {

    @Autowired
    public FollwersDao follwersDao;

    public ResponseEntity<List<FollowersModel>> getallfollowersbyuserid(int userid){
       List<FollowersModel> list=this.follwersDao.findAll();
       list=list.stream().filter(e->e.getMyuserid()==userid).collect(Collectors.toList());
       return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    public List<FollowersModel> getallfollowsbyuserid(int userid){
        List<FollowersModel> list=this.follwersDao.findAll();
        list=list.stream().filter(e->e.getMyuserid()==userid).collect(Collectors.toList());
        return list;
    }

    public Boolean checkIfFollowAlready(int userid, int followid){
        int x=this.follwersDao.checkIffollowalready(userid,followid);
        if(x>=1){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkIfalreadyFollows(int userid,int followid){
        List<FollowersModel> list=this.getallfollowsbyuserid(userid);
        if(list.size()==0){
            return false;
        }
        else if(this.checkIfFollowAlready(userid,followid)){
            return true;
        }
        else if(userid == followid){
            return true;
        }
        else{
            return false;
        }
    }

    public ResponseEntity<FollowersModel> addNewFollows(FollowersModel followersModel){
        if(this.checkIfalreadyFollows(followersModel.getMyuserid(),followersModel.getFollowadded().getUserId())){
            throw new GlobalExceptionClass("follow already added","500");
        }
        else{
            FollowersModel result=this.follwersDao.save(followersModel);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

    }



}
