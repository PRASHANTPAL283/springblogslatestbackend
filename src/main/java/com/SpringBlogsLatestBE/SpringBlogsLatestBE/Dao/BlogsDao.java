package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.BlogsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogsDao extends JpaRepository<BlogsModel,Integer> {

}
