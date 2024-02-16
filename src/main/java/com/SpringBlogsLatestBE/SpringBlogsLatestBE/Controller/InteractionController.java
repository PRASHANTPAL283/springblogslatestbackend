package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Controller;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.CommentModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.LikeEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.CommentServices;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.Likeservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InteractionController {
    @Autowired
    public Likeservices likeservices;

    @Autowired
    public CommentServices commentServices;
    @PostMapping("/doLike")
    public ResponseEntity<LikeEntity> doLike(@RequestBody LikeEntity likeEntity){
        return this.likeservices.doLike(likeEntity);
    }

    @GetMapping("/getLikes/{id}")
    public ResponseEntity<List<LikeEntity>> getallLikes(@PathVariable("id") int id){
        return this.likeservices.getalllikesbyblogId(id);
    }

    @PostMapping("/doComment")
    public ResponseEntity<CommentModel> doComment(@RequestBody CommentModel commentModel){
        return this.commentServices.addNewComment(commentModel);

    }
    @GetMapping("/allcomments/{id}")
    public ResponseEntity<List<CommentModel>> getallComments(@PathVariable("id") int id){
        return this.commentServices.getallcommentsByBlogId(id);
    }


}
