package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;



import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.FileEntity;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.FileResponse;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServices {

    @Autowired
    public FileDao attachRepo;
    @Value("${api_url}")
    private String apiValue;

    public FileResponse uploadafile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("invalid path sequence");
            }

            FileEntity attachment = new FileEntity(
                    0,

                    file.getName(),
                    file.getContentType(),
                    file.getBytes()

            );
            attachment = this.attachRepo.save(attachment);
            String downloadURL = "/prodImage/downloadfile/" + attachment.getFileId();

            FileResponse responseData = new FileResponse(
                    attachment.getFileId(),
                    file.getName(),
                    downloadURL,
                    file.getContentType(),

                    file.getSize()
            );

            return responseData;

        } catch (Exception e) {
            throw new Exception("file cant be uploaded");

        }
    }

    public FileResponse updateuploadedFile(MultipartFile file , int id) throws Exception {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new Exception("invalid path sequence");
            }

            FileEntity attachment=new FileEntity(
                    id,

                    file.getName(),
                    file.getContentType(),
                    file.getBytes()

            );
            attachment= this.attachRepo.save(attachment);
            String downloadURL="/prodImage/downloadfile/"+attachment.getFileId();

            FileResponse responseData=new FileResponse(
                    attachment.getFileId(),
                    file.getName(),
                    downloadURL,
                    file.getContentType(),

                    file.getSize()
            );

            return responseData;



        }
        catch (Exception e){
            throw new Exception("file cant be uploaded");

        }

    }
    public FileEntity getAttachmentById(int id){
        return this.attachRepo.findByFileId(id);
    }
    public void deleteFileById(String id){
        this.attachRepo.deleteById(id);
    }


}
