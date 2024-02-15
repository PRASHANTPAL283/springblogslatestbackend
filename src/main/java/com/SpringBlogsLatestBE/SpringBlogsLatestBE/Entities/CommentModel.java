package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="mycommentModel")
@Entity
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;
    private String description;
    @ManyToOne
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private UserModel userModel;
    @ManyToOne
    @JoinColumn(name="blogId",referencedColumnName = "blogId")
    private BlogsModel blogsModel;
    @CreationTimestamp
    private Date date;
}
