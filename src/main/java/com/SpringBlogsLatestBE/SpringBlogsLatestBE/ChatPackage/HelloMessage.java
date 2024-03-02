package com.SpringBlogsLatestBE.SpringBlogsLatestBE.ChatPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HelloMessage {
    public String message;
    public String username;
}
