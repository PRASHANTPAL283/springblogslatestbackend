package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.BlogsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlogsDao extends JpaRepository<BlogsModel,Integer> {

    @Modifying
    @Transactional
    @Query(value="delete from mycomputer.myfile_entity_model where file_id=:id",nativeQuery = true)
    public void deleteFilebyId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("delete from BlogsModel m where m.blogId=:id")
    public void deleteBlogById(@Param("id") int id);

}
