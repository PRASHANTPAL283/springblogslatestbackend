package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Controller;



import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.FileEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.FileResponse;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.FileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin

public class FileController {
    //file
    @Value("${api_url}")
    private String apiValue;

    @PostMapping("/addNewFile")
    public FileResponse addNewFile(@Param("file")MultipartFile file) throws Exception {
        return this.fileservices.uploadafile(file);

    }

    @PostMapping("/updateFile/{id}")
    public FileResponse updateFileById(@PathVariable("id") int id, @Param("file") MultipartFile file) throws Exception {
        Optional<FileEntity> fileEntity= Optional.ofNullable(this.fileservices.getAttachmentById(id));
        if(fileEntity.isPresent()){
            return this.fileservices.updateuploadedFile(file,id);
        }
        else{
            return this.fileservices.uploadafile(file);

        }


    }

    @Autowired
    public FileServices fileservices;

    @GetMapping("prodImage/downloadfile/{id}")
    public ResponseEntity<Resource> downLoadFile(@PathVariable int id) throws Exception {
        FileEntity attachment=null;
        attachment=this.fileservices.getAttachmentById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\""
                                +attachment.getFileName()+"\"")
                .body(new ByteArrayResource(attachment.getData()));


    }
}
