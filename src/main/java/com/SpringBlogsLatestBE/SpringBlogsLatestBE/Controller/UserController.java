package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Controller;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.Userservices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    public Userservices userservices;

    @PostMapping("/addUser")
    public ResponseEntity<UserModel> addNewUser(@RequestBody @Valid UserModel userModel){
        return  this.userservices.addNewUser(userModel);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<UserModel>> getallusers(){
        return this.userservices.getallusers();
    }

    @GetMapping("/allusers/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") int id){
        return this.userservices.getUserById(id);
    }

    @GetMapping("/deleteUserById/{id}")
    public ResponseEntity<UserModel> deleteUserById(@PathVariable("id") int id){
        return this.userservices.deleteUserById(id);
    }
}