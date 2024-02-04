package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;



import com.SpringBlogsLatestBE.SpringBlogsLatestBE.DTOs.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FileDao extends JpaRepository<FileEntity,String> {

    public FileEntity findByFileId(int id);
}
