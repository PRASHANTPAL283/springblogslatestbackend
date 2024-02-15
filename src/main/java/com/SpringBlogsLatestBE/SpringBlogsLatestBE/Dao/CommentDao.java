package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<CommentModel,Integer> {
}
