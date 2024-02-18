package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.LikeCount;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.LikeDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.UserDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.LikeEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class Likeservices {

    @Autowired
    public LikeDao likeDao;
    @Autowired
    public UserDao userDao;

    public UserModel getUserByUserName(String username){
        Optional<UserModel> userModel= Optional.ofNullable(this.userDao.findByUsername(username));
        if(userModel.isPresent()){
            return userModel.get();
        }
        else{
            throw new GlobalExceptionClass("User not found ","500");
        }
    }
    public LikeEntity getByUsernameandblogId(String username, int blogdId){
        List<LikeEntity> likeEntities=this.likeDao.findAll();
        try {
            Optional<LikeEntity> likeEntity = Optional.ofNullable(
                    likeEntities.stream().filter(e -> e.getUserModel().getUsername().equals(username) && e.getBlogsModel().getBlogId() == blogdId).findFirst().get()
            );
            if (likeEntity.isPresent()) {
                return likeEntity.get();
            } else {
                return null;
            }
        }
        catch (Exception ex){
            System.out.println(ex.getCause());
        }
        return null;
    }



    @Transactional
    public ResponseEntity<LikeEntity> doLike(LikeEntity likeEntity){
        LikeEntity response=this.getByUsernameandblogId(likeEntity.getUserModel().getUsername(),likeEntity.getBlogsModel().getBlogId());
        if(response==null){

        }
        else{
            likeEntity.setLikeId(response.getLikeId());
            likeEntity.setLikeFlag(response.getLikeFlag());
        }

        Random random=new Random();
        int id= random.nextInt();
        System.out.println("the new random id="+id);
      try{
          if(likeEntity.getLikeFlag()==true && likeEntity.getLikeFlag()!=null){
              likeEntity.setLikeFlag(false);
          }
          else{
              likeEntity.setLikeFlag(true);
          }
          System.out.println(likeEntity);
          UserModel userModel=this.getUserByUserName(likeEntity.getUserModel().getUsername());
          likeEntity.setUserModel(userModel);
          LikeEntity result=this.likeDao.save(likeEntity);
          return ResponseEntity.status(HttpStatus.OK)
                  .body(result);

      }
      catch (Exception ex){

          throw new GlobalExceptionClass(ex.getMessage(),ex.getCause(),"500");

      }
    }

    public ResponseEntity<List<LikeEntity>> getalllikesbyblogId(int id){
        List<LikeEntity> likeEntities=new ArrayList<>();
        likeEntities=this.likeDao.findAll();
        List<LikeEntity>result=new ArrayList<>();
       result=likeEntities.stream().filter(e->e.getBlogsModel().getBlogId()==id)
               .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    public ResponseEntity<LikeCount> getallLikesCount(int blogId){
        List<LikeEntity> likeEntities=this.likeDao.findAll();
        List<LikeEntity> result=likeEntities.stream().filter(e->e.getBlogsModel().getBlogId()==blogId && e.getLikeFlag()==true).collect(Collectors.toList());
        LikeCount likeCount=new LikeCount();
        likeCount.setCount(result.size());
        return ResponseEntity.status(HttpStatus.OK)
                .body(likeCount);
    }
}
