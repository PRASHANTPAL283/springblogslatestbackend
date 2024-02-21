package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.FileEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.BlogsDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.FileDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.UserDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.BlogsModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class Blogservices {

    @Autowired
    public BlogsDao blogsDao;

    @Autowired
    public UserDao userDao;

    public ResponseEntity<BlogsModel> addNewBlog(BlogsModel blogsModel){
        try{
            blogsModel.setUserModel(this.getCurrentActiveUser());


            return ResponseEntity.status(HttpStatus.OK).body(this.blogsDao.save(blogsModel));

        }
        catch (Exception e){
            throw new GlobalExceptionClass("unable to save records",e.getCause(),"500");
        }
    }

    public UserModel getCurrentActiveUser(){
        Authentication authentication= SecurityContextHolder
                .getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUserName=authentication.getName();
            UserModel model=this.userDao.findByUsername(currentUserName);
            return model;
        }
        return null;
    }

    public ResponseEntity<List<BlogsModel>> getallBlogs(){
        List<BlogsModel> models= this.blogsDao.findAll();
        Collections.sort(models, Comparator.comparing(BlogsModel::getBlogId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(models);
    }
    @Autowired
    public FileDao fileDao;



    public ResponseEntity<BlogsModel> deleteBlogById(int id){
        Optional<BlogsModel> blogsModelOptional=this.blogsDao.findById(id);
        if(blogsModelOptional.isPresent()){

            this.blogsDao.deleteFilebyId(blogsModelOptional.get().getImageId());
            this.blogsDao.deleteallcomments(id);
            this.blogsDao.deleteallLikes(id);
            this.blogsDao.deleteBlogById(blogsModelOptional.get().getBlogId());

            return ResponseEntity.status(HttpStatus.OK).body(blogsModelOptional.get());
        }
        else{
            throw new GlobalExceptionClass("Blog not found with id","500");
        }
    }

    public ResponseEntity<BlogsModel> getBlogById(int id){
        Optional<BlogsModel> blogsModelOptional=this.blogsDao.findById(id);
        if(blogsModelOptional.isPresent()){

            return ResponseEntity.status(HttpStatus.OK).body(blogsModelOptional.get());
        }
        else{
            throw new GlobalExceptionClass("Blog not found with id","500");
        }
    }

    public ResponseEntity<List<BlogsModel>> getallBlogsByUserId(){
        UserModel userModel=this.getCurrentActiveUser();
        List<BlogsModel> blogsModels=this.blogsDao.findAll();
        List<BlogsModel> result=new ArrayList<>();
        blogsModels.forEach(e->{
            if(e.getUserModel().getUserId()==userModel.getUserId()){
                result.add(e);
            }
        });

        Collections.sort(result,Comparator.comparing(BlogsModel::getBlogId));

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);


    }

    public ResponseEntity<String> checkDebugPoint(){
        int x=34;
        int y=67;
        int z=67;
        int a=87;
        int s=90;
        int r=x+y+z+a+s;
        System.out.println(r);
        if(x%2==0){
            System.out.println("even");
        }
        else{
            System.out.println("odd");
        }
        for(int i=0;i<=3;i++){
            System.out.println(i);
        }
        this.checkMethodExecution();
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    public void checkMethodExecution(){
        int r=2;
        int j=3;
        int p=r+j;
        System.out.println(p);
    }


}
