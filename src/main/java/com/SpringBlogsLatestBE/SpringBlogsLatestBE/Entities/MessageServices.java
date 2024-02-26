package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.MessageDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler.GlobalExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServices {
    @Autowired
    public MessageDao messageDao;

    public ResponseEntity<MessageModel> sendNewMessage(MessageModel messageModel){
        try{

            MessageModel result=this.messageDao.save(messageModel);
            return ResponseEntity.status(HttpStatus.OK).body(result);

        }
        catch (Exception ex){
            throw new GlobalExceptionClass(ex.getMessage(),ex.getCause(),"500");

        }
    }

    public ResponseEntity<List<MessageModel>> getallmessages(String sender, String receiver){
        return ResponseEntity.status(HttpStatus.OK).body(this.messageDao.getallmessages(sender,receiver));
    }


}
