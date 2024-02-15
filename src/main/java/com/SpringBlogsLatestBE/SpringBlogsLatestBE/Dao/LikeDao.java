package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDao extends JpaRepository<LikeEntity,Integer> {
}
