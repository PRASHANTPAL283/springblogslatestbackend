package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Controller;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.MessageModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    public MessageServices messageServices;

    @PostMapping("/sendNewMessage")
    public ResponseEntity<MessageModel> sendNewMessage(@RequestBody MessageModel messageModel){
        return this.messageServices.sendNewMessage(messageModel);
    }

    @GetMapping("/getallmessages/{sender}/{receiver}")
    public ResponseEntity<List<MessageModel>> getallmessages(@PathVariable("sender") String sender, @PathVariable("receiver") String receiver){
        return this.messageServices.getallmessages(sender, receiver);

    }
}
