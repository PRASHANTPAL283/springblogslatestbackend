package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageDao extends JpaRepository<MessageModel,Integer> {

    @Query(value = "select * from mycomputer.message_model where sender=:sender and receiver=:receiver",nativeQuery = true)
   public List<MessageModel> getallmessages(@Param("sender") String sender,@Param("receiver") String receiver);


}
