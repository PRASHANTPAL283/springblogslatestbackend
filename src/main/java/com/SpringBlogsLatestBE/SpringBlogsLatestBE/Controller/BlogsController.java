package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Controller;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.FlightSearchDTO;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Employee;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.BlogsModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.EmployeeEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.Blogservices;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.EmployeeServices;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.Serpapiservices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin

public class BlogsController {
  
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

    @GetMapping("/getBlogsByUser")
    public ResponseEntity<List<BlogsModel>> getallblogsUser(){
        return this.blogservices.getallBlogsByUserId();
    }

    @GetMapping("/checkdebug")
    public ResponseEntity<String> checkDebug(){
        return this.blogservices.checkDebugPoint();
    }
    @Autowired
    public EmployeeServices employeeServices;

    @GetMapping("/postallemployees")
    public List<EmployeeEntity> saveAllEmployees() throws IOException {
        return this.employeeServices.addAllEmployees();
    }
   

    @GetMapping("/alljsondata")
    public List<Object> getalljsonData(){
        return this.serpapiservices.getalljsonData();
    }




}
