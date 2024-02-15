package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.LikeDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.LikeEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Likeservices {

    @Autowired
    public LikeDao likeDao;

    public ResponseEntity<LikeEntity> doLike(LikeEntity likeEntity){
      try{
          if(likeEntity.getLikeFlag()==true){
              likeEntity.setLikeFlag(false);
          }
          else{
              likeEntity.setLikeFlag(true);
          }
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
        for(LikeEntity likeEntity:likeEntities){
            if(likeEntity.getBlogsModel().getBlogId()==id){
                result.add(likeEntity);
            }
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
