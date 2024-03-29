package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.UserDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Userservices {

    @Autowired
    public UserDao userDao;
    @Autowired
    public PasswordEncoder passwordEncoder;


 public Boolean checkpasswordregex(String password){
     Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$");
     Matcher matcher = pattern.matcher(password);
     boolean matchFound = matcher.find();
     if(matchFound) {
         return true;
     } else {
         throw new GlobalExceptionClass("password should be validate",null,"400");
     }
 }

 public Boolean checkIfUserExistOrNot(String username,int id){
     Optional<UserModel> userModel=Optional.ofNullable(this.userDao.findByUsername(username));
     if(userModel.isPresent() && (id==0)){
         return true;
     }
     else if(id>0){
         return false;
     }
     else{
         return false;
     }
 }
 public ResponseEntity<UserModel> addNewUser(UserModel userModel){
     if(checkIfUserExistOrNot(userModel.getUsername(),userModel.getUserId())==false){
         String message=null;
         checkpasswordregex(userModel.getPassword());
         try{

             String encryptpassword=this.passwordEncoder.encode(userModel.getPassword());
             userModel.setPassword(encryptpassword);
             UserModel u=this.userDao.save(userModel);

             return ResponseEntity.status(HttpStatus.OK)
                     .body(u);
         }
         catch (Exception ex){
             throw new GlobalExceptionClass("failed to save user",ex.getCause(),"500");

         }
     }
     else{
         throw new GlobalExceptionClass("username already taken","500");
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

    public ResponseEntity<UserModel> getUserByItsName(String username){
     Optional<UserModel> userModel= Optional.ofNullable(this.userDao.findByUsername(username));
     if(userModel.isPresent()){
         return ResponseEntity.status(HttpStatus.OK).body(userModel.get());
     }
     else{
         throw new GlobalExceptionClass("user_not_found","500");
     }
    }

    public ResponseEntity<UserModel> checkLoginUser(String username, String password){
     Optional<UserModel> userModel=Optional.ofNullable(this.userDao.findByUsername(username));
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
     if(userModel.isPresent() && encoder.matches(password,userModel.get().getPassword())){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userModel.get());

     }
     else{
         throw new GlobalExceptionClass("Login Failed",null,"403");
     }
    }


}
