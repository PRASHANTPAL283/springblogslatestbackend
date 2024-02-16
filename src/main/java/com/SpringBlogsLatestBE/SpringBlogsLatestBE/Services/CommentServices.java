package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.CommentDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.CommentModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class CommentServices {

    @Autowired
    public CommentDao commentDao;

    public ResponseEntity<CommentModel> addNewComment(CommentModel commentModel){
        try{

            CommentModel result=this.commentDao.save(commentModel);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(result);


        }
        catch (Exception ex){
            throw new GlobalExceptionClass(ex.getMessage(),ex.getCause(),"500");

        }
    }

    public ResponseEntity<List<CommentModel>> getallcommentsByBlogId(int id){
        List<CommentModel> commentModelList=this.commentDao.findAll();
        List<CommentModel> result=new ArrayList<>();
        result=commentModelList.stream().filter(e->e.getBlogsModel().getBlogId()==id)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
