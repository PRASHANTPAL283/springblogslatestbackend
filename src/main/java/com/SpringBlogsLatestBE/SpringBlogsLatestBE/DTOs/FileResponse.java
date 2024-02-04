package com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileResponse {
    public int ImageId;
    public String fileName;
    public String downloadURL;
    public String fileType;
    public long fileSize;




}
