package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="followerTables")

public class FollowersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int followId;
    @ManyToOne
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private UserModel followadded;
    private int myuserid;
    @CreationTimestamp
    private Date date;
}
