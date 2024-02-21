package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Controller;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.FollowersModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.FriendsEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.Followerservices;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.FriendServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FriendFollowController {
    @Autowired
    public FriendServices friendServices;

    @Autowired
    public Followerservices followerservices;

    @PostMapping("/addFriend")
    public ResponseEntity<FriendsEntity> addNewFriend(@RequestBody FriendsEntity friendsEntity){
        return this.friendServices.addNewFriend(friendsEntity);
    }

    @GetMapping("/allfriends/{id}")
    public ResponseEntity<List<FriendsEntity>> getallfriends(@PathVariable("id") int id){
        return this.friendServices.getallfriendsByUserId(id);
    }

    @PostMapping("/addFollow")
    public ResponseEntity<FollowersModel> addNewFollow(@RequestBody FollowersModel followersModel){
        return this.followerservices.addNewFollows(followersModel);
    }

    @GetMapping("/allfollows/{id}")
    public ResponseEntity<List<FollowersModel>> getallfollowsbyuserid(@PathVariable("id") int id){
        return this.followerservices.getallfollowersbyuserid(id);
    }


}
