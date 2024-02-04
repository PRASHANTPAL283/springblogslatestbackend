package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Controller;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.BlogsModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.Blogservices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class BlogsController {

    @Autowired
    public Blogservices blogservices;

    public ResponseEntity<BlogsModel> addNewBlog(@RequestBody @Valid BlogsModel blogsModel){
       return this.blogservices.addNewBlog(blogsModel);
    }

    public ResponseEntity<BlogsModel> getBlogById(@PathVariable("id") int id){
        return this.blogservices.getBlogById(id);
    }

    public ResponseEntity<List<BlogsModel>> getallBlogs(){
        return this.blogservices.getallBlogs();
    }

    public ResponseEntity<BlogsModel> deleteBlogById(@PathVariable("id") int id){
        return this.blogservices.deleteBlogById(id);
    }

}