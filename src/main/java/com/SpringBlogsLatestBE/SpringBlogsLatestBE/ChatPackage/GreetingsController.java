package com.SpringBlogsLatestBE.SpringBlogsLatestBE.ChatPackage;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.LastSeenUpdate;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.UserDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.MessageModel;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
public class GreetingsController {
    @Autowired
    public UserDao userDao;

    @MessageMapping("/hello/{roomid}")
    @SendTo("/topic/greetings/{roomid}")
    public MessageModel greeting(@DestinationVariable int roomid, @Payload MessageModel message) throws Exception {
        System.out.println(message);
        return message;
    }
    @MessageMapping("/user/heartbeat")
    @SendTo("/topic/users")
    @Transactional
    public UserModel setLastseenandstatus(@Payload LastSeenUpdate lastseenandstatus){
        Date date=new Date(System.currentTimeMillis());
        System.out.println(lastseenandstatus);
        this.userDao.updatelastseenandstatus(lastseenandstatus.getUserId(),date,lastseenandstatus.getStatus());
        return this.userDao.findByUserId(lastseenandstatus.getUserId());

    }

    @MessageMapping("/sendNotification/{receiver}")
    @SendTo("/topic/getNotified/{receiver}")
    public MessageModel getNotifiedbyMessage(@DestinationVariable int receiver, @Payload MessageModel model){
        System.out.println("the notification="+receiver);
        return model;
    }
}
