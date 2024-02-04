package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.BlogsDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.BlogsModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service

public class Blogservices {

    @Autowired
    public BlogsDao blogsDao;

    public ResponseEntity<BlogsModel> addNewBlog(BlogsModel blogsModel){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(this.blogsDao.save(blogsModel));

        }
        catch (Exception e){
            throw new GlobalExceptionClass("unable to save records",e.getCause(),"500");
        }
    }

    public ResponseEntity<List<BlogsModel>> getallBlogs(){
        List<BlogsModel> models= this.blogsDao.findAll();
        Collections.sort(models, Comparator.comparing(BlogsModel::getBlogId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(models);
    }

    public ResponseEntity<BlogsModel> deleteBlogById(int id){
        Optional<BlogsModel> blogsModelOptional=this.blogsDao.findById(id);
        if(blogsModelOptional.isPresent()){
            this.blogsDao.deleteById(id);
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


}