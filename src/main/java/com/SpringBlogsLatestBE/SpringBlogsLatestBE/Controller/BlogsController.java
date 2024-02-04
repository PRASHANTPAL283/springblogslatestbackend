package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Controller;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.BlogsModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.Blogservices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin

public class BlogsController {
    //testing completed


    @Autowired
    public Blogservices blogservices;

    @PostMapping("/addBlogs")
    public ResponseEntity<BlogsModel> addNewBlog(@RequestBody @Valid BlogsModel blogsModel){
       return this.blogservices.addNewBlog(blogsModel);
    }

    @GetMapping ("/allblogs/{id}")
    public ResponseEntity<BlogsModel> getBlogById(@PathVariable("id") int id){
        return this.blogservices.getBlogById(id);
    }

    @GetMapping("/allblogs")

    public ResponseEntity<List<BlogsModel>> getallBlogs(){
        return this.blogservices.getallBlogs();
    }

    @GetMapping("/deleteBlogById/{id}")
    public ResponseEntity<BlogsModel> deleteBlogById(@PathVariable("id") int id){
        return this.blogservices.deleteBlogById(id);
    }

}
