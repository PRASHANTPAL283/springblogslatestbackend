package com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageDTO {
    public int messageId;
    public String description;
    public UserModel sender;
    public UserModel receiver;
    public Date date;
}
