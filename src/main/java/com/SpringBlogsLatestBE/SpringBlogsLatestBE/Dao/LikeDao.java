package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.LikeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDao extends JpaRepository<LikeEntity,Integer> {

    @Transactional
    @Modifying
    @Query(value="insert into mycomputer.my_like_entity_model (like_id,like_flag,blog_id,user_id) values(:id,:like,:blogId,:userId)",nativeQuery = true)
    public void addNewLikeQuery(@Param("id") int id,@Param("blogId") int blogId,@Param("userId") int userdId,@Param("like") boolean like);
}
