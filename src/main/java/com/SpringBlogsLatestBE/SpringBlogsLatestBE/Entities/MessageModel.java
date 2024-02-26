package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="MessageModel")
@Entity
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int messageId;
    public String description;
    public String sender;
    public String receiver;
    public Date date;

}
