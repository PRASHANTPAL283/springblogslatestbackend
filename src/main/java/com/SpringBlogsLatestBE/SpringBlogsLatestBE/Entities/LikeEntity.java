package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="myLikeEntityModel")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int likeId;
    @ManyToOne
    @JoinColumn(name="blogId", referencedColumnName = "blogId")
    private BlogsModel blogsModel;

    @ManyToOne
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private UserModel userModel;
    private Boolean likeFlag;

}
