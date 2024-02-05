package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.UserDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Userservices {

    @Autowired
    public UserDao userDao;
    @Autowired
    public PasswordEncoder passwordEncoder;

    public ResponseEntity<UserModel> addNewUser(UserModel userModel){
        String encryptpassword=this.passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encryptpassword);
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.userDao.save(userModel));

        }
        catch (Exception ex){
            throw new GlobalExceptionClass("failed to save user",ex.getCause(),"500");

        }


    }

    public ResponseEntity<UserModel> getUserById(int id){
        Optional<UserModel> userModel=this.userDao.findById(id);
        if(userModel.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userModel.get());
        }
        else{
            throw new GlobalExceptionClass("User Not Found","404");
        }
    }

    public ResponseEntity<UserModel> deleteUserById(int id){
        Optional<UserModel> userModel=this.userDao.findById(id);
        if(userModel.isPresent()){
            this.userDao.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userModel.get());
        }
        else{
            throw new GlobalExceptionClass("User Not Found","404");
        }
    }

    public ResponseEntity<List<UserModel>> getallusers(){
        List<UserModel> userModelList=new ArrayList<>();
        userModelList=this.userDao.findAll();
        Collections.sort(userModelList, Comparator.comparing(UserModel::getUserId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(userModelList);
    }


}
