package com.SpringBlogsLatestBE.SpringBlogsLatestBE.ChatPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Greetings {
    public String content;
    public String username;
}
