package com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LastSeenUpdate {
    public int userId;
    public String status;

}
