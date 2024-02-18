package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.CommentDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.UserDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.CommentModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class CommentServices {

    @Autowired
    public CommentDao commentDao;

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

    public ResponseEntity<CommentModel> addNewComment(CommentModel commentModel){
        UserModel userModel=this.getUserByUserName(commentModel.getUserModel().getUsername());
        try{
            commentModel.setUserModel(userModel);
            Date date=new Date(System.currentTimeMillis());
            commentModel.setDate(date);


            CommentModel result=this.commentDao.save(commentModel);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(result);


        }
        catch (Exception ex){
            throw new GlobalExceptionClass(ex.getMessage(),ex.getCause(),"500");

        }
    }

    public ResponseEntity<List<CommentModel>> getallcommentsByBlogId(int id){
        List<CommentModel> commentModelList=this.commentDao.findAll();
        List<CommentModel> result=new ArrayList<>();
        result=commentModelList.stream().filter(e->e.getBlogsModel().getBlogId()==id)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public ResponseEntity<CommentModel> deleteCommentById(int id){
        Optional<CommentModel> commentModel=this.commentDao.findById(id);
        if(commentModel.isPresent()){
            this.commentDao.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(commentModel.get());
        }
        else{
            throw new GlobalExceptionClass("Comment not found with id "+id,"404");
        }
    }


}
